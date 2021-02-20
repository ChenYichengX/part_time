package com.chen.part_time.web;

import com.chen.part_time.entity.User;
import com.chen.part_time.service.IUserService;
import com.chen.part_time.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Random;

/**
 * @author 陈奕成
 * @create 2020 12 03 15:46
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private IUserService userService;

    @Autowired
    private JavaMailSender javaMailSender;

    private static Random random = new Random();

    private String host = "123.57.174.182";
    private int port = 6379;

    @GetMapping()
    public String toRegister(){
        return "regist";
    }

    /**
     * ajax 异步验证注册的 username 是否存在
     * @param username
     * @return
     */
    @RequestMapping("/check")
    @ResponseBody
    public String ajaxCheckUserName(String username){
        int i = userService.checkUsername(username);
        if(i > 0){ // 已存在
            return "false";
        }
        return "ok";
    }

    /**
     * 发送验证码到邮箱
     * @param email
     * @return
     */
    @PostMapping("/sendCode")
    @ResponseBody
    public String sendCode(String email, HttpServletRequest request){
        Jedis jedis = new Jedis(host,port);
        String code = getCode(6);
        //获取发送验证码的次数
        String ip = request.getRemoteAddr();
        String countKey = ip + "_verify_code:" + email + "count";
        String count = jedis.get(countKey);
        if(count == null){
            //代表第一次
            jedis.setex(countKey,60*10,"1");
        }else if(Integer.parseInt(count) <= 2){
            jedis.incr(countKey);
        }else if(Integer.parseInt(count) > 2){
            jedis.close();
            return "limit";
        }
        // 发送验证码邮件
        new Thread(() -> sendCode(email)).start();
        //3.往 redis 中进行存储
        String codeKey = ip + "_verify_code:"+email+":code";// key--->  verify_code:132546:code
        jedis.setex(codeKey,90,code);
        jedis.close();
        return "true";
    }

    /**
     * 发送验证码邮件
     * @param email
     */
    private void sendCode(String email) {
        String code = getCode(6);
        //将验证码发送到用户邮箱
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("【南理兼职平台】-验证码");
        simpleMailMessage.setText("验证码为：" + code + "请于90秒内完成验证,过期无效！");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setFrom("361415506@qq.com");
        javaMailSender.send(simpleMailMessage);
    }


    /**
     * 验证邮箱验证码
     * @param user
     * @param validate
     * @return
     */
    @PostMapping("/verify")
    @ResponseBody
    public String verifyCode(User user, String validate,HttpServletRequest request){
        //1.获取邮箱和验证码
        //2.从 redis 中获取邮箱号对应的验证码
        String ip = request.getRemoteAddr();
        String codeKey = ip + "_verify_code:" + user.getEmail() + ":code";// key--->  verify_code:132546:code
        Jedis jedis = new Jedis(host,port);
        String code = jedis.get(codeKey);
        jedis.close();
        if(validate.equals(code)){
            user.setCreateDate(new Date());
            String password = user.getPassword();
            user.setPassword(MD5Util.code(password));
            userService.insertUser(user);
            return "true";
        }else{
            return "false";
        }
    }

    /**
     * 返回一串随机码作为验证码
     * @param length
     * @return
     */
    private static String getCode(int length){
        String code = "";
        for(int i = 0;i < length; i++){
            int rand = random.nextInt(10);
            code += rand;
        }
        return code;
    }
}
