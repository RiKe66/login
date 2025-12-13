package com.project.bigevent.config;

import com.project.bigevent.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 登录和注册接口以及前端相关资源不拦截
        registry.addInterceptor(loginInterceptor).excludePathPatterns(
                // 1. 业务接口（/user 前缀已确认）
                "/user/login",
                "/user/register",

                // 2. 核心修正：前端页面访问路径
                "/",                // 放行根路径，Spring Boot 默认会映射到 index.html
                "/index.html",      // 放行 index.html 文件名

                // 3. 静态资源（以防万一）
                "/css/**",
                "/js/**",
                "/**/*.html",       // 排除所有 HTML 文件
                "/**/*.css",
                "/**/*.js"
        );
    }
}