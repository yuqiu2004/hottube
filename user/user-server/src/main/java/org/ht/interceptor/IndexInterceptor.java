package org.ht.interceptor;

import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ht.constant.Constant;
import org.ht.model.context.ContextData;
import org.ht.model.properties.JwtProperties;
import org.ht.util.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class IndexInterceptor implements HandlerInterceptor {

    @Resource
    private JwtProperties jwtProperties;

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }
        //1、从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getTokenName());
        //2、校验令牌
        try {
            Claims claims = jwtUtil.parseJWT(jwtProperties.getSecretKey(), token);
            int empId = Integer.valueOf(claims.get(Constant.JWT_CLAIMS).toString());
            ContextData.setUserId(empId);
            //3、通过，放行
            return true;
        } catch (Exception ex) {
            //4、不通过，响应401状态码
            response.setStatus(401);
            return false;
        }
    }

}
