package org.ht.config;

import jakarta.annotation.Resource;
import org.ht.interceptor.IndexInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Component
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Resource
    private IndexInterceptor indexInterceptor;

    /**
     * 注册自定义拦截器
     */
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(indexInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/register")
                .excludePathPatterns("/user/login");
    }

}
