package org.ht.config;

import org.ht.interceptor.AuthInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

@Component
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Resource
    private AuthInterceptor indexInterceptor;

    /**
     * 注册自定义拦截器
     */
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(indexInterceptor)
                .addPathPatterns("/**");
    }

}
