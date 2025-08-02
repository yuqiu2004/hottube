package org.ht.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ht.entity.UserVideoLike;
import org.ht.service.UserVideoLikeService;
import org.ht.mapper.UserVideoLikeMapper;
import org.springframework.stereotype.Service;

/**
 * 用户视频点赞Service实现类
 * 
 * @author HotTube
 * @since 2024-01-01
 */
@Service
public class UserVideoLikeServiceImpl extends ServiceImpl<UserVideoLikeMapper, UserVideoLike>
implements UserVideoLikeService{

}
