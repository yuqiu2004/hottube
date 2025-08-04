package org.ht.service.rpc;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.ht.mapper.VideoMetadataMapper;
import org.ht.model.dto.VideoMetadataDTO;
import org.ht.model.entity.VideoMetadata;
import org.ht.model.mongo.VideoRecord;
import org.ht.model.response.VideoPublishResponse;
import org.ht.repository.VideoRecordRepository;
import org.ht.service.VideoPublishRpcService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@DubboService
public class VideoPublishRpcServiceImpl implements VideoPublishRpcService {

    @Resource
    private VideoMetadataMapper videoMetadataMapper;

    @Resource
    private VideoRecordRepository videoRecordRepository;

    @Override
    public VideoPublishResponse publish(VideoMetadataDTO videoMetadataDTO) {
        VideoMetadata videoMetadata = new VideoMetadata();
        BeanUtil.copyProperties(videoMetadataDTO, videoMetadata);
        videoMetadata.setCreateTime(LocalDateTimeUtil.now());
        videoMetadata.setUpdateTime(LocalDateTimeUtil.now());
        videoMetadata.setPlayCount(0L);
        videoMetadata.setStatus(1); // default temporary publish
        videoMetadataMapper.insert(videoMetadata);
        // insert into mongodb
        VideoRecord videoRecord = new VideoRecord();
        videoRecord.setVideoId(videoMetadata.getId());
        videoRecord.setCreatorId(videoMetadata.getCreatorId());
        videoRecordRepository.save(videoRecord);
        // insert into es
        return VideoPublishResponse.builder().success(true).build();
    }

}
