package com.chen.part_time.web;

import com.chen.part_time.entity.BrowsingHistory;
import com.chen.part_time.entity.PartTimeCondition;
import com.chen.part_time.service.IBrowsingHistoryService;
import com.chen.part_time.service.IPartTimeService;
import com.chen.part_time.vo.MerchantPartTime;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 一些公共的接口，不需要 aop 切面的
 * Create by ChenYicheng
 * 2021/2/23 12:00
 */
@Controller
public class CommonController {

    @Autowired
    private IPartTimeService partTimeService;

    @Autowired
    private IBrowsingHistoryService browsingHistoryService;

    /**
     * 渲染 footer 底部的最新兼职信息
     *
     * @return
     */
    @GetMapping("/footer/partTime2")
    @ResponseBody
    public Map<String, Object> newsFooter() {
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
     * 分页查询所有浏览记录
     * @return
     */
    @GetMapping("/admin/browsingHistory")
    public String getAllBrowsing(Model model){
        PageInfo<BrowsingHistory> all = browsingHistoryService.getAll(1, 20);
        model.addAttribute("browsings",all);
        return "admin/browsingHistory";
    }

    /**
     * 分页查询所有浏览记录（Ajax）
     * @return
     */
    @PostMapping("/admin/browsingHistory")
    public String search(@RequestParam(required = false) Integer page, Model model) {
        int pageNumber = 1;
        if (page != null && page != 0) {
            pageNumber = page;
        }
        PageInfo<BrowsingHistory> all = browsingHistoryService.getAll(pageNumber, 20);
        model.addAttribute("browsings", all);
        return "admin/browsingHistory :: browsingList";
    }

}
