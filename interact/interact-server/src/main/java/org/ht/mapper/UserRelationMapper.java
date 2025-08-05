package org.ht.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.ht.model.entity.UserRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 用户关系Mapper接口
 * 
 * @author HotTube
 * @since 2024-01-01
 */
@Mapper
public interface UserRelationMapper extends BaseMapper<UserRelation> {

}
