package com.project.bigevent.interceptors;

import com.project.bigevent.pojo.Result;
import com.project.bigevent.utils.JwtUtil;
import com.project.bigevent.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String authHeader = request.getHeader("Authorization");

        // 1. 判断是否存在 & 是否 Bearer
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 2. 去掉 "Bearer "
        String token = authHeader.substring(7);

        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);

            // 3. 存入 ThreadLocal
            ThreadLocalUtil.set(claims);

            return true;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ThreadLocalUtil.remove();
    }
}

