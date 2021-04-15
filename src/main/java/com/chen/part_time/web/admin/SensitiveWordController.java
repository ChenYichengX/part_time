package com.chen.part_time.web.admin;

import com.chen.part_time.entity.SensitiveWork;
import com.chen.part_time.service.ISensitiveWorkService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ClassName SensitiveWordController
 * @Author ChenYicheng
 * @Description 敏感词管理-controller
 * @Date 2021/4/15 15:21
 */
@Controller
@RequestMapping("/admin/sensitive")
public class SensitiveWordController {

    @Autowired
    private ISensitiveWorkService sensitiveWorkService;


    /**
     * @Author ChenYicheng
     * @Description 敏感词列表
     * @Date 2021/4/15 15:23
     */
    @GetMapping("")
    public String toSensitiveWorkPage(Model model){
        PageHelper.startPage(1,10);
        List<SensitiveWork> list = sensitiveWorkService.getList(null);
        PageInfo<SensitiveWork> sensitiveWorkPageInfo = new PageInfo<>(list, 5);
        model.addAttribute("info",sensitiveWorkPageInfo);
        return "admin/sensitiveWork";
    }
    
    /**
     * @Author ChenYicheng
     * @Description 敏感词列表
     * @Date 2021/4/15 15:23
     */
    @PostMapping("/list")
    public String list(String work,Integer page,Model model){
        int pageNum = 1;
        if(page != null && page != 0){
            pageNum = page;
        }
        PageHelper.startPage(pageNum,10);
        List<SensitiveWork> list = sensitiveWorkService.getList(null);
        PageInfo<SensitiveWork> sensitiveWorkPageInfo = new PageInfo<>(list, 5);
        model.addAttribute("info",sensitiveWorkPageInfo);
        return "admin/sensitiveWork :: sensitiveList";
    }
}
