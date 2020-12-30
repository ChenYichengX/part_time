package com.chen.part_time.web.admin;

import com.chen.part_time.entity.PaymentType;
import com.chen.part_time.service.IPaymentTypeService;
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
 * @create 2020 11 20 21:51
 */
@Controller
@RequestMapping("/admin")
public class PaymentTypeController {

    @Autowired
    private IPaymentTypeService paymentTypeService;

    @GetMapping("/paymentTypes")
    public String list(@RequestParam(required = false) Integer pageNumber, Model model) {
        int pageNum = 1;
        if (pageNumber != null) {
            pageNum = pageNumber;
        }
        PageHelper.startPage(pageNum, 7);
        List<PaymentType> allType = paymentTypeService.getAllPaymentType();
        PageInfo<PaymentType> pageInfo = new PageInfo<>(allType, 3);

        model.addAttribute("info", pageInfo);
        return "admin/payment_type";
    }

    @GetMapping("/paymentTypes/input")
    public String toAddTypePage(Model model) {
        model.addAttribute("paymentType", new PaymentType(null, ""));
        return "admin/paymentType-input";
    }

    @PostMapping("/paymentTypes")
    public String AddType(PaymentType paymentType, RedirectAttributes attributes,Model model) {
        // 1.后端验证 name 不能为空
        if (paymentType.getName() == null || "".equals(paymentType.getName())) {
            attributes.addFlashAttribute("message", "不能为空");
            return "redirect:/admin/paymentTypes/input";
        }
        // 2.验证 name 不能重复
        PaymentType typeByName = paymentTypeService.getPaymentTypeByName(paymentType.getName());
        if (typeByName != null) {
            model.addAttribute("message", "名称重复");
            model.addAttribute("paymentType",paymentType);
            return "admin/paymentType-input";
        }

        if (paymentType.getId() != null) { // 说明是修改
            paymentTypeService.updatePaymentType(paymentType);
            attributes.addFlashAttribute("message", "修改成功");
        } else {
            boolean b = paymentTypeService.savePaymentType(paymentType);
            if (b) { // 添加成功
                attributes.addFlashAttribute("message", "添加成功");
            } else { // 添加失败
                attributes.addFlashAttribute("message", "添加失败");
            }
        }
        return "redirect:/admin/paymentTypes";
    }

    @GetMapping("/paymentType/{id}/input")
    public String toEditPage(@PathVariable Long id, Model model) {
        model.addAttribute("paymentType", paymentTypeService.getPaymentType(id));
        return "admin/paymentType-input";
    }

    @GetMapping("/paymentType/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        boolean b = paymentTypeService.deletePaymentType(id);
        if(b){
            attributes.addFlashAttribute("message","删除成功");
        }else{
            attributes.addFlashAttribute("message","删除失败");
        }
        return "redirect:/admin/paymentTypes";
    }
}
