package com.chen.part_time.web;

import com.chen.part_time.entity.Complaint;
import com.chen.part_time.entity.User;
import com.chen.part_time.service.IPartTimeService;
import com.chen.part_time.service.ITypeService;
import com.chen.part_time.service.IUserService;
import com.chen.part_time.vo.MerchantPartTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 申述接口
 * @author 陈奕成
 * @create 2021 03 30 22:49
 */
@Controller
public class ComplaintController {

    @Autowired
    private IPartTimeService partTimeService;

    @Autowired
    private IUserService userService;

    /**
     * 去申述界面
     * @param id
     * @return
     */
    @GetMapping("/complaint")
    public String complaint(Long id, Model model){
        MerchantPartTime partTimeById = partTimeService.getPartTimeById(id); // 兼职信息
        User userByPart_time_id = userService.getUserByPart_time_id(id); // 商家信息
        model.addAttribute("partTime",partTimeById);
        model.addAttribute("merchantInfo",userByPart_time_id);
        return "complaint";
    }

    /**
     * 提交申述信息
     * @return
     */
    @PostMapping("/complaint")
    @ResponseBody
    public String submitComplaint(Complaint complaint, HttpSession session) {
        System.out.println(complaint);
        return "";
    }
}
