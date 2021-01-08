package com.chen.part_time.web;

import com.chen.part_time.dao.IApplyDao;
import com.chen.part_time.entity.*;
import com.chen.part_time.service.*;
import com.chen.part_time.vo.MerchantPartTime;
import com.chen.part_time.vo.TypeVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 陈奕成
 * @create 2020 11 18 11:03
 */
@Controller
public class IndexController {

    @Autowired
    private IPartTimeService partTimeService;
    @Autowired
    private ITypeService typeService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IApplyService applyService;
    @Autowired
    private JavaMailSenderImpl javaMailSender;
    @Autowired
    private IAdService adService;

    private String host = "123.57.174.182";
    private int port = 6379;


    @GetMapping("/")
    public String index(Model model) {
        int pageNum = 1;
        // 按时间排序
        PartTimeCondition partTime = new PartTimeCondition();
        PageHelper.startPage(pageNum, 8);
        List<MerchantPartTime> allPartTime = partTimeService.getAllPartTime(partTime);
        PageInfo<MerchantPartTime> pageInfo = new PageInfo<>(allPartTime);

        //类型按数量排序
        PageHelper.startPage(1, 4);
        List<TypeVo> allType = typeService.getAllType();
        PageInfo<TypeVo> voPageInfo = new PageInfo<>(allType);

        // 中间要显示的内容。按浏览次数排序
        PartTimeCondition part2 = new PartTimeCondition();
        PageHelper.startPage(pageNum, 4);
        List<MerchantPartTime> allPartTimeByView = partTimeService.getAllPartTimeByView(part2);
        PageInfo<MerchantPartTime> contentInfo = new PageInfo<>(allPartTimeByView, 5);

        model.addAttribute("latest", pageInfo);
        model.addAttribute("types", voPageInfo);
        model.addAttribute("info", contentInfo);
        model.addAttribute("ads",adService.getAdsByCode("index-left"));
        return "index";
    }

    @PostMapping("/")
    public String indexPage(@RequestParam(required = false) Integer page, Model model) {
        int pageNum = 1;
        if (page != null && page != 0) {
            pageNum = page;
        }
        // 中间要显示的内容。按浏览次数排序
        PartTimeCondition part2 = new PartTimeCondition();
        PageHelper.startPage(pageNum, 4);
        List<MerchantPartTime> allPartTimeByView = partTimeService.getAllPartTimeByView(part2);
        PageInfo<MerchantPartTime> contentInfo = new PageInfo<>(allPartTimeByView, 5);

        model.addAttribute("info", contentInfo);
        return "index :: partTimeList";
    }

    @PostMapping("/search")
    public String search(@RequestParam(required = false) Integer page, String query, Model model) {
        int pageNum = 1;
        if (page != null && page != 0) {
            pageNum = page;
        }
        // 中间要显示的内容。按浏览次数排序
        PartTimeCondition part2 = new PartTimeCondition();
        part2.setContent(query);
        part2.setUser_name(query);
        part2.setTitle(query);
        part2.setType_name(query);
        PageHelper.startPage(pageNum, 4);
        List<MerchantPartTime> allPartTimeByView = partTimeService.getAllPartTimeByView(part2);
        PageInfo<MerchantPartTime> contentInfo = new PageInfo<>(allPartTimeByView, 5);

        model.addAttribute("info", contentInfo);
        model.addAttribute("query", query);
        return "search";
    }

    @PostMapping("/searchPage")
    public String searchPage(@RequestParam(required = false) Integer page, String query, Model model) {
        int pageNum = 1;
        if (page != null && page != 0) {
            pageNum = page;
        }
        // 中间要显示的内容。按浏览次数排序
        PartTimeCondition part2 = new PartTimeCondition();
        part2.setContent(query);
        part2.setUser_name(query);
        part2.setTitle(query);
        part2.setType_name(query);
        PageHelper.startPage(pageNum, 4);
        List<MerchantPartTime> allPartTimeByView = partTimeService.getAllPartTimeByView(part2);
        PageInfo<MerchantPartTime> contentInfo = new PageInfo<>(allPartTimeByView, 5);

        model.addAttribute("info", contentInfo);
        model.addAttribute("query", query);
        return "search :: partTimeList";
    }

    @GetMapping("/partTime/{id}")
    public String partTime(@PathVariable Long id, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        if (user == null) { // 说明未登录
            // 重定向到登录界面
            // session.setAttribute("message","请先登录，登录后可查看详细信息");
            return "redirect:/merchant";
        }
        if (user instanceof User) {
            User u = (User) user;
            int count = applyService.selectApply(new Apply(id, u.getId())); // 如果已经申请，则 count 为 1
            model.addAttribute("count", count);
        }
        if(user instanceof Admin){
            model.addAttribute("count", 2);
        }
        partTimeService.addViewsById(id);
        MerchantPartTime partTimeById = partTimeService.getPartTimeById(id);
        User userById = userService.getUserById(partTimeById.getUser_id());
        model.addAttribute("partTime", partTimeById);
        model.addAttribute("userInfo", userById);
        return "partTime";
    }

    @GetMapping("/verify")
    @ResponseBody
    public String verify(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "false";
        }
        return "true";
    }

    /**
     * 通过 id 查询指定的分类去分类页
     *
     * @param id
     * @param page
     * @param model
     * @return
     */
    @GetMapping("/type/{id}")
    public String type(@PathVariable(value = "id", required = false) Long id, Integer page, Model model) {
        int num = 1;
        if (page != null && page != 0) {
            num = page;
        }

        List<TypeVo> allType = typeService.getAllType();
        if (id == null || id == 0) {
            TypeVo typeVo = allType.get(0);
            id = typeVo.getId();
        }
        PartTimeCondition partTimeCondition = new PartTimeCondition();
        partTimeCondition.setType_id(id);

        PageHelper.startPage(num, 1);
        List<MerchantPartTime> allPartTime = partTimeService.getAllPartTime(partTimeCondition);
        PageInfo<MerchantPartTime> info = new PageInfo<>(allPartTime, 5);

        model.addAttribute("allType", allType);
        model.addAttribute("typeTotal", allType.size());
        model.addAttribute("info", info);
        model.addAttribute("id", id);
        return "type";
    }

    /**
     * 返回 type 分类页
     *
     * @param model
     * @return
     */
    @GetMapping("/type")
    public String types(Model model) {
        List<TypeVo> allType = typeService.getAllType();

        TypeVo typeVo = allType.get(0);
        Long id = typeVo.getId();

        PartTimeCondition partTimeCondition = new PartTimeCondition();
        partTimeCondition.setType_id(id);

        PageHelper.startPage(1, 4);
        List<MerchantPartTime> allPartTime = partTimeService.getAllPartTime(partTimeCondition);
        PageInfo<MerchantPartTime> info = new PageInfo<>(allPartTime, 5);

        model.addAttribute("allType", allType);
        model.addAttribute("typeTotal", allType.size());
        model.addAttribute("info", info);
        model.addAttribute("id", id);
        return "type";
    }

    /**
     * 渲染 footer 底部的最新兼职信息（第一版失败）
     *
     * @param model
     * @return
     */
    @GetMapping("/footer/partTime")
    public String newsFooter(Model model) {
        // 按时间排序
        PartTimeCondition partTime = new PartTimeCondition();
        PageHelper.startPage(1, 3);
        List<MerchantPartTime> allPartTime = partTimeService.getAllPartTime(partTime);
        PageInfo<MerchantPartTime> pageInfo = new PageInfo<>(allPartTime);
        model.addAttribute("latestFooter", pageInfo);
        return "_fragment :: partTimeDiv";
    }

    /**
     * 渲染 footer 底部的最新兼职信息
     *
     * @return
     */
    @GetMapping("/footer/partTime2")
    @ResponseBody
    public Map<String, Object> newsFooter2() {
        // 按时间排序
        PartTimeCondition partTime = new PartTimeCondition();
        PageHelper.startPage(1, 3);
        List<MerchantPartTime> allPartTime = partTimeService.getAllPartTime(partTime);
        PageInfo<MerchantPartTime> pageInfo = new PageInfo<>(allPartTime);
        Map<String, Object> map = new HashMap<>();
        map.put("latestFooter", pageInfo);
        return map;
    }

    /**
     * 申请兼职信息
     * 发送给商家，说谁谁谁申请了你的哪个兼职
     *
     * @return
     */
    @GetMapping("/apply/{id}")
    @ResponseBody
    public String apply(@PathVariable Long id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        if (user == null) { // 未登录
            return "false";
        }
        if (user instanceof Admin) { // 不是学生
            return "NoStu";
        }
        User u = null;
        if (user instanceof User) {
            u = (User) user;
        }
        if (u.getType() == 1) { // 不是学生
            return "NoStu";
        }
        Apply apply = new Apply(id, u.getId(), new Date());
        applyService.addApply(apply); // 添加兼职申请记录
        // 通过 part_time_id 获取商家信息
        User merchant = userService.getUserByPart_time_id(id);
        // 发送邮件通知商家
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("通知");
        simpleMailMessage.setText("亲爱的" + merchant.getNickName() + ",刚刚" + u.getUsername() + "申请了您的一个职位,快去查看吧(*^_^*)");
        simpleMailMessage.setTo(merchant.getEmail());
        simpleMailMessage.setFrom("361415506@qq.com");
        javaMailSender.send(simpleMailMessage);
        return "success";
    }
}
