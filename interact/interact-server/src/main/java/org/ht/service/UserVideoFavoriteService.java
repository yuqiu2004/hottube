package org.ht.service;

import org.ht.model.entity.UserVideoFavorite;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户视频收藏Service接口
 * 
 * @author HotTube
 * @since 2024-01-01
 */
public interface UserVideoFavoriteService {

    void favoriteVideo(Long videoId);

    void unfavoriteVideo(Long videoId);
}
