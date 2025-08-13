package org.ht.service;

import cn.hutool.json.JSONUtil;
import org.ht.constant.MQConst;
import org.ht.constant.RedisKey;
import org.ht.model.context.ContextData;
import org.ht.model.dto.VideoMetadataDTO;
import org.ht.model.dto.VideoTransDTO;
import org.ht.model.properties.VideoProperties;
import org.ht.model.response.InitUploadResponse;
import org.ht.model.response.UploadChunkResponse;
import org.ht.model.response.UploadMetadataResponse;
import org.ht.util.LocalPathUtil;
import org.ht.util.RedisUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VideoServiceImpl implements VideoService {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private VideoProperties videoProperties;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private LocalPathUtil localPathUtil;

    @Override
    public InitUploadResponse initUpload(String filename, double size) {
        int uid = ContextData.getUserInfo().getUid();
        // 先查找是否存在续传的状态
        Set<Integer> status = redisUtil.getSet(RedisKey.getUploadStatusKey(uid, filename)).stream()
                .map(o -> (Integer)o).collect(Collectors.toSet());
        if (!status.isEmpty()) {
            int total = (int) Math.ceil(size / videoProperties.getChunkSize());
            List<Integer> toSend = new ArrayList<>();
            for (int i = 0; i < total; i++) {
                if (status.contains(i)) continue;
                toSend.add(i);
            }
            return InitUploadResponse.builder()
                    .initSuccess(true)
                    .resumeTrans(true)
                    .chunkSize(videoProperties.getChunkSize())
                    .toSendTrunks(toSend)
                    .build();
        }
        // 文件夹清空准备
        String dir = localPathUtil.getVideoBaseDir(uid, filename);
        File file = new File(dir);
        createEmptyDir(file);
        // 准备响应
        return InitUploadResponse.builder()
                .initSuccess(true)
                .resumeTrans(false)
                .chunkSize(videoProperties.getChunkSize())
                .toSendTrunks(null)
                .build();
    }

    @Override
    public UploadChunkResponse uploadChunk(MultipartFile file, String filename, int chunkIndex, int totalChunks) {
        int uid = ContextData.getUserInfo().getUid();
        String path = localPathUtil.getChunkDir(uid, filename) + "/chunk" + chunkIndex;
        File chunkFile = new File(path);
        try {
            chunkFile.createNewFile();
            file.transferTo(chunkFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String key = RedisKey.getUploadStatusKey(uid, filename);
        redisUtil.addSetValue(key, chunkIndex);
        redisUtil.expire(key, 60*60*24);
        return UploadChunkResponse.builder().build();
    }

    @Override
    public boolean mergeChunks(String filename, int totalChunks) {
        // 检查是否完成
        int uid = ContextData.getUserInfo().getUid();
        long size = redisUtil.getSetSize(RedisKey.getUploadStatusKey(uid, filename));
        String dir = localPathUtil.getChunkDir(uid, filename);
        File d = new File(dir);
        // 这里只检查是否完整 合并留到转码的时候一起完成
        return size == totalChunks && d.list().length == totalChunks;
    }

    @Override
    public UploadMetadataResponse uploadMetadata(String filename, VideoMetadataDTO metadataDTO) {
        // 将转码任务加入消息队列
        VideoTransDTO dto = VideoTransDTO.builder().filename(filename).metadata(metadataDTO).build();
        String jsonStr = JSONUtil.toJsonStr(dto);
        rabbitTemplate.convertAndSend(MQConst.VIDEO_TRANS_EXCHANGE_NAME, MQConst.VIDEO_TRANS_ROUTING_KEY, jsonStr);
        return UploadMetadataResponse.builder()
                .build();
    }

    private void createEmptyDir(File file) {
        if (!file.exists()) {
            file.mkdirs();
            return;
        }
        if (file.isFile()) {
            file.delete();
            file.mkdirs();
            return;
        }
        clearDir(file);
    }

    private void clearDir(File file) {
        if (!file.exists() || file.isFile()) return;
        File[] files = file.listFiles();
        if (null == files) return;
        for (File f : files) {
            if (f.isFile()) f.delete();
            else {
                clearDir(f);
                f.delete();
            }
        }
    }
}
