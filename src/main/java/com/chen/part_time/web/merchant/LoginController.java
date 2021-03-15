package com.chen.part_time.web.merchant;

import com.chen.part_time.entity.PartTimeCondition;
import com.chen.part_time.entity.User;
import com.chen.part_time.service.IPartTimeService;
import com.chen.part_time.service.ITypeService;
import com.chen.part_time.service.IUserService;
import com.chen.part_time.util.MD5Util;
import com.chen.part_time.vo.MerchantPartTime;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户登录、注销的跳转
 * @author 陈奕成
 * @create 2020 11 18 21:25
 */
@Controller
@RequestMapping("/merchant")
public class LoginController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IPartTimeService partTimeService;
    @Autowired
    private ITypeService typeService;


    @GetMapping
    public String loginPage() {
        return "login";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpServletRequest request,
                        Model model,
                        RedirectAttributes attributes) {
        HttpSession session = request.getSession();
        User user = userService.checkUser(new User(username, MD5Util.code(password)));
        if (user != null) {
            session.setAttribute("user", user);
            if(user.getType() == 0){ // 说明是学生
                return "redirect:/";
            }
            PageHelper.startPage(1,6);
            PartTimeCondition partTimeCondition = new PartTimeCondition();
            partTimeCondition.setUser_id(user.getId());
            List<MerchantPartTime> allPartTime = partTimeService.getAllPartTime(partTimeCondition);
            PageInfo<MerchantPartTime> pageInfo = new PageInfo<>(allPartTime,5);
            model.addAttribute("info",pageInfo);
            model.addAttribute("types",typeService.getAllType());
            return "merchant/partTime";
        } else {
            attributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/merchant";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/merchant";
    }

}
