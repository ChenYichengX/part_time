package com.chen.part_time.aspect;

import com.chen.part_time.entity.Admin;
import com.chen.part_time.entity.BrowsingHistory;
import com.chen.part_time.entity.User;
import com.chen.part_time.service.IBrowsingHistoryService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;

/**
 * @author 陈奕成
 * @create 2020 05 09 14:18
 */
@Aspect
@Component
public class LogAspect {

//    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IBrowsingHistoryService browsingHistoryService;

    @Pointcut("execution(* com.chen.part_time.web..*.*(..)) && !execution(* com.chen.part_time.web.CommonController.*(..))")
    public void log(){}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        String username = "";
        if(user == null){
            username = "游客";
        }else if(user instanceof Admin){
            Admin u = (Admin) user;
            username = u.getUsername();
        }else if(user instanceof User){
            User u = (User) user;
            username = u.getUsername();
        }
        String classMethod = joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        BrowsingHistory browsingHistory = new BrowsingHistory(ip,url,username,classMethod,new Date());
        browsingHistoryService.addBrowsingHistory(browsingHistory);
    }

    /*@After("log()")
    public void doAfter(){
        logger.info("-----doAfter-----");
    }

    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturn(Object result){
        logger.info("result:"+result);
    }

    @AfterThrowing(throwing = "exception",pointcut = "log()")
    public void doException(Exception exception){
        logger.info("异常信息：" + exception.toString());
    }*/

}
