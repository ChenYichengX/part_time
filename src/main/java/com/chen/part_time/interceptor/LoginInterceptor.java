package com.chen.part_time.interceptor;

import com.chen.part_time.entity.Admin;
import com.chen.part_time.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 验证用户是否登录的拦截器
 * 未登录，不让访问哪些需要登录的资源
 * @author 陈奕成
 * @create 2020 11 18 22:10
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String uri = request.getRequestURI();
        if (uri.endsWith("js")||uri.endsWith("css")||uri.endsWith("jpg")||uri.endsWith("svg")){
            return true ;
        }
        Object user = request.getSession().getAttribute("user");
        if(user instanceof Admin){
            response.sendRedirect("/merchant");
            return false;
        }
        if(user == null){
            response.sendRedirect("/merchant");
            return false;
        }
        User u = (User) user;
        if(u.getType() == 0){
            response.sendRedirect("/");
            return false;
        }
        return true;
    }

}
