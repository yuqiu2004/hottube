package org.ht.service;

import org.ht.model.entity.UserVideoLike;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户视频点赞Service接口
 * 
 * @author HotTube
 * @since 2024-01-01
 */
public interface UserVideoLikeService {

    void likeVideo(Long videoId);

    void unlikeVideo(Long videoId);
}
