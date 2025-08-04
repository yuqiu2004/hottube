package org.ht.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.ht.entity.UserVideoFavorite;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 用户视频收藏Mapper接口
 * 
 * @author HotTube
 * @since 2024-01-01
 */
@Mapper
public interface UserVideoFavoriteMapper extends BaseMapper<UserVideoFavorite> {

}
