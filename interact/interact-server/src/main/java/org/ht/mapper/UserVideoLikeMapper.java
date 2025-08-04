package org.ht.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.ht.entity.UserVideoLike;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 用户视频点赞Mapper接口
 * 
 * @author HotTube
 * @since 2024-01-01
 */
@Mapper
public interface UserVideoLikeMapper extends BaseMapper<UserVideoLike> {

}
