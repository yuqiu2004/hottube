package org.ht.interceptor;

import org.apache.dubbo.config.annotation.DubboReference;
import org.ht.constant.Constant;
import org.ht.model.context.ContextData;
import org.ht.model.dto.UserInfo;
import org.ht.model.response.AuthResponse;
import org.ht.model.service.UserAuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @DubboReference
    private UserAuthService userAuthService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String token = request.getHeader(Constant.TOKEN_NAME);
            AuthResponse auth = userAuthService.auth(token);
            if (auth.isPass()) {
                UserInfo userInfo = new UserInfo();
                BeanUtils.copyProperties(auth.getUserAuthDTO(), userInfo);
                ContextData.setUserInfo(userInfo);
                return true;
            }
        } catch (Exception e) {
            ContextData.clearUserInfo();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        ContextData.clearUserInfo();
    }
}
