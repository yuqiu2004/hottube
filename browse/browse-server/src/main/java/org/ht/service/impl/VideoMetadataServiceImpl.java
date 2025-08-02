package org.ht.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ht.model.entity.VideoMetadata;
import org.ht.service.VideoMetadataService;
import org.ht.mapper.VideoMetadataMapper;
import org.springframework.stereotype.Service;

/**
 * 视频元数据Service实现类
 * 
 * @author HotTube
 * @since 2024-01-01
 */
@Service
public class VideoMetadataServiceImpl extends ServiceImpl<VideoMetadataMapper, VideoMetadata>
implements VideoMetadataService {

}
