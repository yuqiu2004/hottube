package org.ht.service;

import org.ht.model.dto.VideoMetadataDTO;
import org.ht.model.dto.VideoTransDTO;
import org.ht.util.LocalPathUtil;
import org.ht.util.MinioUtil;
import org.ht.util.VideoUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

@Service
public class VideoConvertServiceImpl implements VideoConvertService {

    @Resource
    private LocalPathUtil localPathUtil;

    @Resource
    private MinioUtil minioUtil;

    @Override
    public void handle(VideoTransDTO transDTO) {
        // 分片合并
        int uid = transDTO.getMetadata().getCreatorId();
        String filename = transDTO.getFilename();
        String chunkDir = localPathUtil.getChunkDir(uid, filename);
        String targetFilePath = localPathUtil.getOriginalFilePath(uid, filename);
        File chunkDirectory = new File(chunkDir);
        File[] files = chunkDirectory.listFiles();
        if (files == null || files.length == 0) throw new RuntimeException("分片文件不存在");
        Arrays.sort(files, Comparator.comparing(File::getName));
        try (FileOutputStream targetFileOutputStream = new FileOutputStream(targetFilePath)) {
            for (File shardFile : files) {
                try (FileInputStream shardFileInputStream = new FileInputStream(shardFile)) {
                    byte[] buffer = new byte[10240]; // 缓冲区大小
                    int bytesRead;
                    while ((bytesRead = shardFileInputStream.read(buffer)) != -1) {
                        targetFileOutputStream.write(buffer, 0, bytesRead);
                    }
                } catch (IOException e) {
                    System.out.println("读取分片文件时出错：" + shardFile.getName());
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("写入目标文件时出错！");
            e.printStackTrace();
        }
        // 截取封面
        String imgPath = localPathUtil.getImgPath(uid, filename);
        try {
            VideoUtil.captureFrame(targetFilePath, imgPath, 0.1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 根据原画 转成不同的画质视频hls
        VideoMetadataDTO metadata = transDTO.getMetadata();
        // 原画
        metadata.setOriginalExists(1);
        String oriM3u8Path = localPathUtil.getOriM3u8Path(uid, filename);
        String oriTsDir = localPathUtil.getOriTsDir(uid, filename);
        try {
            VideoUtil.convertToHLS(targetFilePath, oriM3u8Path, oriTsDir);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            // 720p
            String p720M3u8Path = localPathUtil.get720pM3u8Path(uid, filename);
            String p720TsDir = localPathUtil.get720pTsDir(uid, filename);
            if (VideoUtil.transAndConvertTo720p(targetFilePath, p720M3u8Path, p720TsDir)) metadata.setP720Exists(1);
            else metadata.setP720Exists(0);

            // 360p
            String p360M3u8Path = localPathUtil.get360pM3u8Path(uid, filename);
            String p360TsDir = localPathUtil.get360pTsDir(uid, filename);
            if (VideoUtil.transAndConvertTo360p(targetFilePath, p360M3u8Path, p360TsDir)) metadata.setP360Exists(1);
            else metadata.setP360Exists(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 上传minio 注入的路径都要是完整的路径 todo

    }
}
