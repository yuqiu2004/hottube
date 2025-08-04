package org.ht.service.rpc;

import io.jsonwebtoken.Claims;
import org.apache.dubbo.config.annotation.DubboService;
import org.ht.constant.Constant;
import org.ht.mapper.UserMapper;
import org.ht.model.dto.UserAuthDTO;
import org.ht.model.entity.User;
import org.ht.model.response.AuthResponse;
import org.ht.model.response.UserInfoResponse;
import org.ht.model.vo.UserVo;
import org.ht.service.UserRpcService;
import org.ht.util.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@DubboService
public class UserRpcServiceImpl implements UserRpcService {

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private UserMapper userMapper;

    @Override
    public AuthResponse auth(String token) {
        try {
            Claims claims = jwtUtil.parseJWT(token);
            Integer uid = Integer.valueOf(claims.get(Constant.JWT_CLAIMS).toString());
            User user = userMapper.selectById(uid);
            if (user != null) {
                UserAuthDTO userAuthDTO = new UserAuthDTO();
                BeanUtils.copyProperties(user, userAuthDTO);
                return AuthResponse.pass(userAuthDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AuthResponse.reject();
    }

    @Override
    public UserInfoResponse getUserInfo(Integer uid) {
        User user = userMapper.selectById(uid);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        return UserInfoResponse.builder().userVo(userVo).build();
    }

}
