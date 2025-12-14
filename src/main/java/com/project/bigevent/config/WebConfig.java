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

        // 确保你的拦截器也处理了 OPTIONS 请求的放行，
        // 否则即使下面的路径正确，跨域请求也会失败。

        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**") // 拦截所有请求
                .excludePathPatterns(
                        // 1. 登录和注册 API (必须放行)
                        "/user/login",
                        "/user/register",

                        // 2. 网站图标 (必须放行，你之前问的问题)
                        "/favicon.ico",

                        // 3. 首页和主页 HTML 文件 (必须放行，因为它们加载时没有 Token)
                        // 注意：只放行文件，不放行整个 /user/userInfo 接口！
                        "/",
                        "/index.html",
                        "/home.html"        // 确保主页能被访问

                        // 4. 其他静态资源（如果你的资源不在 static/ 或 public/ 目录下，才需要添加）
                        // 通常情况下，以下不需要，除非你看到 401 错误
                        // "/css/**",
                        // "/js/**"
                );
    }
}