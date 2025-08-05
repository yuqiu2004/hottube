package org.ht.service;

import org.ht.model.entity.UserVideoComment;
import com.baomidou.mybatisplus.extension.service.IService;
import org.ht.model.response.CommentResponse;

/**
 * 用户视频评论Service接口
 * 
 * @author HotTube
 * @since 2024-01-01
 */
public interface UserVideoCommentService {

    CommentResponse getComments(Long videoId, int current, int size);

    void addComment(UserVideoComment comment);
}
