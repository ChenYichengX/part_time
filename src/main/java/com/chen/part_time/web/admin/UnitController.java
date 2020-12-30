package com.chen.part_time.web.admin;

import com.chen.part_time.entity.Unit;
import com.chen.part_time.service.IUnitService;
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
public class UnitController {

    @Autowired
    private IUnitService unitService;

    @GetMapping("/units")
    public String list(@RequestParam(required = false) Integer pageNumber, Model model) {
        int pageNum = 1;
        if (pageNumber != null) {
            pageNum = pageNumber;
        }
        PageHelper.startPage(pageNum, 7);
        List<Unit> allUnit = unitService.getAllUnit();
        PageInfo<Unit> pageInfo = new PageInfo<>(allUnit);

        model.addAttribute("info", pageInfo);
        return "admin/unit";
    }

    @GetMapping("/units/input")
    public String toAddUnitPage(Model model) {
        model.addAttribute("unit", new Unit(null, ""));
        return "admin/unit-input";
    }

    @PostMapping("/units")
    public String AddUnit(Unit unit, RedirectAttributes attributes,Model model) {
        // 1.后端验证 name 不能为空
        if (unit.getName() == null || "".equals(unit.getName())) {
            attributes.addFlashAttribute("message", "不能为空");
            return "redirect:/admin/units/input";
        }
        // 2.验证 name 不能重复
        Unit unitByName = unitService.getUnitByName(unit.getName());
        if (unitByName != null) {
            model.addAttribute("message", "名称重复");
            model.addAttribute("unit",unit);
            return "admin/unit-input";
        }

        if (unit.getId() != null) { // 说明是修改
            unitService.updateUnit(unit);
            attributes.addFlashAttribute("message", "修改成功");
        } else {
            boolean b = unitService.saveUnit(unit);
            if (b) { // 添加成功
                attributes.addFlashAttribute("message", "添加成功");
            } else { // 添加失败
                attributes.addFlashAttribute("message", "添加失败");
            }
        }
        return "redirect:/admin/units";
    }

    @GetMapping("/unit/{id}/input")
    public String toEditPage(@PathVariable Long id, Model model) {
        model.addAttribute("unit", unitService.getUnit(id));
        return "admin/unit-input";
    }

    @GetMapping("/unit/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        boolean b = unitService.deleteUnit(id);
        if(b){
            attributes.addFlashAttribute("message","删除成功");
        }else{
            attributes.addFlashAttribute("message","删除失败");
        }
        return "redirect:/admin/units";
    }
}
