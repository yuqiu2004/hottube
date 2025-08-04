package org.ht.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.ht.model.entity.VideoMetadata;

@Mapper
public interface VideoMetadataMapper extends BaseMapper<VideoMetadata> {

    @Update("UPDATE video_metadata SET play_count = play_count + 1 WHERE id = #{videoId}")
    void increasePlayCount(Long videoId);

}
