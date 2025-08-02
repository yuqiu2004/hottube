package org.ht.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ht.entity.UserRelation;
import org.ht.service.UserRelationService;
import org.ht.mapper.UserRelationMapper;
import org.springframework.stereotype.Service;

/**
 * 用户关系Service实现类
 * 
 * @author HotTube
 * @since 2024-01-01
 */
@Service
public class UserRelationServiceImpl extends ServiceImpl<UserRelationMapper, UserRelation>
implements UserRelationService{

}
