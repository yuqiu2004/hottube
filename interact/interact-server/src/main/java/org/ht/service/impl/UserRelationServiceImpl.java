package org.ht.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dubbo.config.annotation.DubboReference;
import org.ht.model.entity.UserRelation;
import org.ht.model.context.ContextData;
import org.ht.model.response.UserInfoResponse;
import org.ht.model.response.UserListResponse;
import org.ht.service.UserRelationService;
import org.ht.mapper.UserRelationMapper;
import org.ht.service.UserRpcService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.stream.Collectors;

/**
 * 用户关系Service实现类
 * 
 * @author HotTube
 * @since 2024-01-01
 */
@Service
public class UserRelationServiceImpl implements UserRelationService{

    @Resource
    private UserRelationMapper userRelationMapper;

    @DubboReference
    private UserRpcService userRpcService;

    @Override
    public UserListResponse followings(int page, int pageSize) {
        int uid = ContextData.getUserInfo().getUid();
        QueryWrapper queryWrapper = new QueryWrapper<UserRelation>()
                .eq("follower_id", uid);
        Page<UserRelation> p = new Page<>(page-1, pageSize);
        Page<UserRelation> result = userRelationMapper.selectPage(p, queryWrapper);
        return UserListResponse.builder()
                .total((int) result.getTotal())
                .userList(result.getRecords().parallelStream().map(
                    ur -> {
                        UserInfoResponse info = userRpcService.getUserInfo(ur.getFolloweeId().intValue());
                        return info.getUserVo();
                    }
                ).collect(Collectors.toList()))
                .build();
    }

    @Override
    public UserListResponse followers(int page, int pageSize) {
        int uid = ContextData.getUserInfo().getUid();
        QueryWrapper queryWrapper = new QueryWrapper<UserRelation>()
                .eq("followee_id", uid);
        Page<UserRelation> p = new Page<>(page-1, pageSize);
        Page<UserRelation> result = userRelationMapper.selectPage(p, queryWrapper);
        return UserListResponse.builder()
                .total((int) result.getTotal())
                .userList(result.getRecords().parallelStream().map(
                        ur -> {
                            UserInfoResponse info = userRpcService.getUserInfo(ur.getFollowerId().intValue());
                            return info.getUserVo();
                        }
                ).collect(Collectors.toList()))
                .build();
    }
}
