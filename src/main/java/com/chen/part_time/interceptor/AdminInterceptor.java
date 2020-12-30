package com.chen.part_time.interceptor;

import com.chen.part_time.entity.Admin;
import com.chen.part_time.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 管理员的拦截器
 * @author 陈奕成
 * @create 2020 11 19 22:12
 */
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String uri = request.getRequestURI();
        if (uri.endsWith("js")||uri.endsWith("css")||uri.endsWith("jpg")||uri.endsWith("svg")){
            return true ;
        }
        Object user = request.getSession().getAttribute("user");
        if(user instanceof User){
            response.sendRedirect("/admin");
            return false;
        }
        if(user == null){
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
