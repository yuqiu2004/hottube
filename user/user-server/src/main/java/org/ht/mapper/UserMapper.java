package org.ht.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.ht.model.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
