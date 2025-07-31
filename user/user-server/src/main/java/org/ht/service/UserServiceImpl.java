package org.ht.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.ht.mapper.UserMapper;
import org.ht.model.entity.User;
import org.ht.model.request.RegisterRequest;
import org.ht.model.request.LoginRequest;
import org.ht.model.request.UpdateUserRequest;
import org.ht.model.response.RegisterResponse;
import org.ht.model.response.LoginResponse;
import org.ht.model.response.UserInfoResponse;
import org.ht.model.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        // 检查用户名是否已存在
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("username", request.getUsername());
        if (userMapper.selectCount(query) > 0) {
            throw new RuntimeException("用户名已存在");
        }
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword()) // 实际项目应加密
                .nickname(request.getNickname())
                .state(0)
                .gender(2)
                .createDatetime(LocalDateTime.now())
                .build();
        userMapper.insert(user);
        RegisterResponse resp = new RegisterResponse();
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        resp.setUserVo(userVo);
        return resp;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("username", request.getUsername());
        User user = userMapper.selectOne(query);
        if (user == null || !Objects.equals(user.getPassword(), request.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }
        // 生成token（此处简化为uid字符串）
        String token = String.valueOf(user.getUid());
        LoginResponse resp = new LoginResponse();
        resp.setToken(token);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        resp.setUserVo(userVo);
        return resp;
    }

    @Override
    public UserInfoResponse getUserInfo(Integer uid) {
        User user = userMapper.selectById(uid);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        UserInfoResponse resp = new UserInfoResponse();
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        resp.setUserVo(userVo);
        return resp;
    }

    @Override
    public UserInfoResponse updateUserInfo(Integer uid, UpdateUserRequest request) {
        User user = userMapper.selectById(uid);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        if (request.getNickname() != null) user.setNickname(request.getNickname());
        if (request.getAvatar() != null) user.setAvatar(request.getAvatar());
        if (request.getBackground() != null) user.setBackground(request.getBackground());
        if (request.getGender() != null) user.setGender(request.getGender());
        if (request.getDescription() != null) user.setDescription(request.getDescription());
        user.setUpdateDatetime(LocalDateTime.now());
        userMapper.updateById(user);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        return UserInfoResponse.builder().userVo(userVo).build();
    }
}
