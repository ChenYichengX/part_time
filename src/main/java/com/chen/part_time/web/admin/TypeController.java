package com.chen.part_time.web.admin;

import com.chen.part_time.entity.Type;
import com.chen.part_time.service.ITypeService;
import com.chen.part_time.vo.TypeVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

/**
 * @author 陈奕成
 * @create 2020 11 19 21:13
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private ITypeService typeService;

    @GetMapping("/types")
    public String list(@RequestParam(required = false) Integer pageNumber, Model model) {
        int pageNum = 1;
        if (pageNumber != null) {
            pageNum = pageNumber;
        }
        PageHelper.startPage(pageNum, 7);
        List<TypeVo> allType = typeService.getAllType();
        PageInfo<TypeVo> pageInfo = new PageInfo<>(allType, 3);

        model.addAttribute("info", pageInfo);
        return "admin/type";
    }

    @GetMapping("/types/input")
    public String toAddTypePage(Model model) {
        model.addAttribute("type", new Type(null, ""));
        return "admin/type-input";
    }

    @PostMapping("/types")
    public String AddType(Type type, RedirectAttributes attributes,Model model) {
        // 1.后端验证 name 不能为空
        if (type.getName() == null || "".equals(type.getName())) {
            attributes.addFlashAttribute("message", "不能为空");
            return "redirect:/admin/types/input";
        }
        // 2.验证 name 不能重复
        Type typeByName = typeService.getTypeByName(type.getName());
        if (typeByName != null) {
            model.addAttribute("message", "名称重复");
            model.addAttribute("type",type);
            return "admin/type-input";
        }

        if (type.getId() != null) { // 说明是修改
            typeService.updateType(type);
            attributes.addFlashAttribute("message", "修改成功");
        } else {
            boolean b = typeService.saveType(type);
            if (b) { // 添加成功
                attributes.addFlashAttribute("message", "添加成功");
            } else { // 添加失败
                attributes.addFlashAttribute("message", "添加失败");
            }
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/type/{id}/input")
    public String toEditPage(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getType(id));
        return "admin/type-input";
    }

    @GetMapping("/type/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        boolean b = typeService.deleteType(id);
        if(b){
            attributes.addFlashAttribute("message","删除成功");
        }else{
            attributes.addFlashAttribute("message","删除失败");
        }
        return "redirect:/admin/types";
    }
}
