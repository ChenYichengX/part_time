package com.chen.part_time.web.admin;

import com.chen.part_time.entity.SensitiveWork;
import com.chen.part_time.service.ISensitiveWorkService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        SensitiveWork sensitiveWork = new SensitiveWork();
        sensitiveWork.setWork(work);
        PageHelper.startPage(pageNum,10);
        List<SensitiveWork> list = sensitiveWorkService.getList(sensitiveWork);
        PageInfo<SensitiveWork> sensitiveWorkPageInfo = new PageInfo<>(list, 5);
        model.addAttribute("info",sensitiveWorkPageInfo);
        return "admin/sensitiveWork :: sensitiveList";
    }

    /**
     * @Author ChenYicheng
     * @Description 删除敏感词
     * @Date 2021/4/16 14:07
     */
    @GetMapping("/{id}/delete")
    public String deleteWork(@PathVariable Long id, RedirectAttributes attributes){
        boolean b = sensitiveWorkService.deleteWork(id);
        if(b){
            attributes.addFlashAttribute("message","删除成功");
        }else{
            attributes.addFlashAttribute("message","删除失败");
        }
        return "redirect:/admin/sensitive";
    }

    /**
     * @Author ChenYicheng
     * @Description 去敏感词新增页面
     * @Date 2021/4/16 15:37
     */
    @GetMapping("/input")
    public String toAddTypePage(Model model) {
        model.addAttribute("sensitiveWork", new SensitiveWork(null,""));
        return "admin/sensitiveWork-input";
    }

    @PostMapping
    public String addSensitiveWork(SensitiveWork sensitiveWork,RedirectAttributes attributes,Model model){
        // 1.后端校验 work 不能为空
        if(sensitiveWork.getWork() == null || "".equals(sensitiveWork.getWork())){
            attributes.addFlashAttribute("message", "不能为空");
            return "redirect:/admin/sensitive/input";
        }
        // 2.验证 name 不能重复
        Integer count = sensitiveWorkService.getCountByWork(sensitiveWork.getWork());
        if(count > 0){
            model.addAttribute("message", "敏感词已存在");
            model.addAttribute("sensitiveWork",sensitiveWork);
            return "admin/sensitiveWork-input";
        }
        boolean b = sensitiveWorkService.addSensitiveWork(sensitiveWork);
        if (b) { // 添加成功
            attributes.addFlashAttribute("message", "添加成功");
        } else { // 添加失败
            attributes.addFlashAttribute("message", "添加失败");
        }
        return "redirect:/admin/sensitive";
    }
}
