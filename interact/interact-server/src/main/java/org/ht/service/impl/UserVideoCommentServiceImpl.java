package org.ht.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ht.model.entity.UserVideoComment;
import org.ht.model.response.CommentResponse;
import org.ht.service.UserVideoCommentService;
import org.ht.mapper.UserVideoCommentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户视频评论Service实现类
 * 
 * @author HotTube
 * @since 2024-01-01
 */
@Service
public class UserVideoCommentServiceImpl implements UserVideoCommentService{

    @Resource
    private UserVideoCommentMapper userVideoCommentMapper;

    @Override
    public CommentResponse getComments(Long videoId, int current, int size) {
        Page p = new Page<UserVideoComment>(current-1, size);
        QueryWrapper queryWrapper = new QueryWrapper<UserVideoComment>().eq("video_id", videoId);
        Page<UserVideoComment> page = userVideoCommentMapper.selectPage(p, queryWrapper);
        return CommentResponse.builder()
                .total((int) page.getTotal())
                .commentList(page.getRecords())
                .build();
    }

    @Override
    public void addComment(UserVideoComment comment) {
        userVideoCommentMapper.insert(comment);
    }
}
