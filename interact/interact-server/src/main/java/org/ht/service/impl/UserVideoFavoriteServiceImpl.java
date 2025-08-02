package org.ht.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ht.entity.UserVideoFavorite;
import org.ht.service.UserVideoFavoriteService;
import org.ht.mapper.UserVideoFavoriteMapper;
import org.springframework.stereotype.Service;

/**
 * 用户视频收藏Service实现类
 * 
 * @author HotTube
 * @since 2024-01-01
 */
@Service
public class UserVideoFavoriteServiceImpl extends ServiceImpl<UserVideoFavoriteMapper, UserVideoFavorite>
implements UserVideoFavoriteService{

}
