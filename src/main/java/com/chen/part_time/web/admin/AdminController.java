package com.chen.part_time.web.admin;

import com.chen.part_time.entity.Admin;
import com.chen.part_time.entity.PartTimeCondition;
import com.chen.part_time.service.IAdminService;
import com.chen.part_time.service.IPartTimeService;
import com.chen.part_time.service.ITypeService;
import com.chen.part_time.util.MD5Util;
import com.chen.part_time.vo.MerchantPartTime;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 登录、注销的跳转
 * @author 陈奕成
 * @create 2020 11 19 15:25
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;
    @Autowired
    private IPartTimeService partTimeService;
    @Autowired
    private ITypeService typeService;

    @GetMapping
    public String loginPage() {
        return "admin/login";
    }

    @GetMapping("/login")
    public String login(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes, Model model) {
        Admin user = adminService.login(new Admin(username, MD5Util.code(password)));
        if (user != null) {
            session.setAttribute("user", user);
            int pageNum = 1;
            PageHelper.startPage(pageNum, 6);
            PartTimeCondition partTime = new PartTimeCondition();
            List<MerchantPartTime> allPartTime = partTimeService.getAllPartTime(partTime);
            PageInfo<MerchantPartTime> pageInfo = new PageInfo<>(allPartTime, 5);
            model.addAttribute("info", pageInfo);
            model.addAttribute("types", typeService.getAllType());
            return "admin/partTime";
        } else {
            attributes.addFlashAttribute("message","用户名和密码错误");
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
