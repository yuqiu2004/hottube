package org.ht.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ht.entity.UserVideoComment;
import org.ht.service.UserVideoCommentService;
import org.ht.mapper.UserVideoCommentMapper;
import org.springframework.stereotype.Service;

/**
 * 用户视频评论Service实现类
 * 
 * @author HotTube
 * @since 2024-01-01
 */
@Service
public class UserVideoCommentServiceImpl extends ServiceImpl<UserVideoCommentMapper, UserVideoComment>
implements UserVideoCommentService{

}
