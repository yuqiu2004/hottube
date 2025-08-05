package org.ht.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ht.model.context.ContextData;
import org.ht.model.entity.UserVideoLike;
import org.ht.service.UserVideoLikeService;
import org.ht.mapper.UserVideoLikeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户视频点赞Service实现类
 * 
 * @author HotTube
 * @since 2024-01-01
 */
@Service
public class UserVideoLikeServiceImpl implements UserVideoLikeService{

    @Resource
    private UserVideoLikeMapper userVideoLikeMapper;

    @Override
    public void likeVideo(Long videoId) {
        // 加锁 先查找 再更改 。。。
        int uid = ContextData.getUserInfo().getUid();
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", uid);
        map.put("video_id", videoId);
        UserVideoLike userVideoLike = userVideoLikeMapper.selectOne(new QueryWrapper<UserVideoLike>().allEq(map));
        if (null != userVideoLike) {
            userVideoLike.setStatus(1);
            userVideoLikeMapper.updateById(userVideoLike);
            return;
        }
        UserVideoLike videoLike = UserVideoLike.builder()
                .userId(uid)
                .videoId(videoId)
                .status(1)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        userVideoLikeMapper.insert(videoLike);
    }

    @Override
    public void unlikeVideo(Long videoId) {
        int uid = ContextData.getUserInfo().getUid();
        UpdateWrapper<UserVideoLike> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id", uid)
                .eq("video_id", videoId)
                .set("status", 0)
                .set("update_time", LocalDateTime.now());
        userVideoLikeMapper.update(null, updateWrapper);
    }
}
