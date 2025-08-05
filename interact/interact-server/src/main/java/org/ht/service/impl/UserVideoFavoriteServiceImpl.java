package org.ht.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.ht.model.context.ContextData;
import org.ht.model.entity.UserVideoFavorite;
import org.ht.service.UserVideoFavoriteService;
import org.ht.mapper.UserVideoFavoriteMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户视频收藏Service实现类
 * 
 * @author HotTube
 * @since 2024-01-01
 */
@Service
public class UserVideoFavoriteServiceImpl implements UserVideoFavoriteService{

    @Resource
    private UserVideoFavoriteMapper userVideoFavoriteMapper;

    @Override
    public void favoriteVideo(Long videoId) {
        int uid = ContextData.getUserInfo().getUid();
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", uid);
        map.put("video_id", videoId);
        UserVideoFavorite favorite = userVideoFavoriteMapper.selectOne(new QueryWrapper<UserVideoFavorite>().allEq(map));
        if (null != favorite) {
            favorite.setStatus(1);
            userVideoFavoriteMapper.updateById(favorite);
            return;
        }
        favorite = UserVideoFavorite.builder()
                .userId(uid)
                .videoId(videoId)
                .status(1)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        userVideoFavoriteMapper.insert(favorite);
    }

    @Override
    public void unfavoriteVideo(Long videoId) {
        int uid = ContextData.getUserInfo().getUid();
        UpdateWrapper update = new UpdateWrapper<UserVideoFavorite>()
                .eq("user_id", uid)
                .eq("video_id", videoId)
                .set("status", 1)
                .set("update_time", LocalDateTime.now());
        userVideoFavoriteMapper.update(null, update);
    }
}
