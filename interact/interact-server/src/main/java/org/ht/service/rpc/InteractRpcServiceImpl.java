package org.ht.service.rpc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.ht.mapper.UserRelationMapper;
import org.ht.mapper.UserVideoFavoriteMapper;
import org.ht.mapper.UserVideoLikeMapper;
import org.ht.model.entity.UserRelation;
import org.ht.model.entity.UserVideoFavorite;
import org.ht.model.entity.UserVideoLike;
import org.ht.model.response.FollowQueryResponse;
import org.ht.model.response.VideoInteractDataResponse;
import org.ht.service.InteractRpcService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@DubboService
public class InteractRpcServiceImpl implements InteractRpcService {

    @Resource
    private UserRelationMapper userRelationMapper;

    @Resource
    private UserVideoLikeMapper userVideoLikeMapper;

    @Resource
    private UserVideoFavoriteMapper userVideoFavoriteMapper;

    @Override
    public FollowQueryResponse flowQuery(int uid) {
        Long followings = userRelationMapper.selectCount(new QueryWrapper<UserRelation>().eq("follower_id", uid));
        Long followers = userRelationMapper.selectCount(new QueryWrapper<UserRelation>().eq("followee_id", uid));
        return FollowQueryResponse.builder()
                .followings(followings)
                .followers(followers)
                .build();
    }

    @Override
    public VideoInteractDataResponse videoInteractData(int videoId) {
        Long like = userVideoLikeMapper.selectCount(
                new QueryWrapper<UserVideoLike>().eq("video_id", videoId).eq("status", 1));
        Long favorite = userVideoFavoriteMapper.selectCount(
                new QueryWrapper<UserVideoFavorite>().eq("video_id", videoId).eq("status", 1));
        return VideoInteractDataResponse.builder()
                .like(like)
                .favorite(favorite)
                .build();
    }
}
