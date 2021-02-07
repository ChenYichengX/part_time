package com.chen.part_time.web.admin;

import com.chen.part_time.entity.*;
import com.chen.part_time.service.IPartTimeService;
import com.chen.part_time.service.IPaymentTypeService;
import com.chen.part_time.service.ITypeService;
import com.chen.part_time.service.IUnitService;
import com.chen.part_time.vo.MerchantPartTime;
import com.chen.part_time.vo.TypeVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 陈奕成
 * @create 2020 12 02 11:28
 */
@RequestMapping("/admin")
@Controller
public class AdminPartTimeController {

    @Autowired
    private IPartTimeService partTimeService;
    @Autowired
    private ITypeService typeService;
    @Autowired
    private IPaymentTypeService paymentTypeService;
    @Autowired
    private IUnitService unitService;

    private String basePath = ""; // 图片的基路径

    public AdminPartTimeController(){
        try {
            String path = ResourceUtils.getURL("classpath:").getPath() + "static/images";
//            String realPath = path.replace("/","\\").substring(1,path.length());
            String realPath = path.substring(1);
            basePath = realPath;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("partTimes")
    public String partTime(@RequestParam(required = false) Integer pageNumber, Model model, HttpSession session) {
        int pageNum = 1;
        if (pageNumber != null) {
            pageNum = pageNumber;
        }
        PartTimeCondition partTime = new PartTimeCondition();
        PageHelper.startPage(pageNum, 6);
        List<MerchantPartTime> allPartTime = partTimeService.getAllPartTime(partTime);
        PageInfo<MerchantPartTime> pageInfo = new PageInfo<>(allPartTime, 5);
        model.addAttribute("info", pageInfo);
        model.addAttribute("types", typeService.getAllType());
        return "admin/partTime";
    }

    /**
     * 条件查找
     * @param page
     * @param title
     * @param typeId
     * @param model
     * @return
     */
    @PostMapping("partTimes/search")
    public String search(@RequestParam(required = false) Integer page, String title,String user_name, Long typeId, Model model) {
        int pageNum = 1;
        if (page != null && page != 0) {
            pageNum = page;
        }
        PartTimeCondition partTime = new PartTimeCondition(typeId, title);
        partTime.setUser_name(user_name);
        PageHelper.startPage(pageNum, 6);
        List<MerchantPartTime> allPartTime = partTimeService.getAllPartTime(partTime);
        PageInfo<MerchantPartTime> pageInfo = new PageInfo<>(allPartTime, 5);
        model.addAttribute("info", pageInfo);
        model.addAttribute("types", typeService.getAllType());
        return "admin/partTime :: partTimeList";
    }

    /**
     * 删除
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/partTime/{id}/delete")
    public String deleteFile(@PathVariable Long id, RedirectAttributes attributes){
        MerchantPartTime partTime = partTimeService.getPartTimeById(id);
        if(partTime == null){ // 不存在
            attributes.addFlashAttribute("message","删除失败，该兼职信息不存在");
            return "redirect:/admin/partTimes";
        }
        // 删除图片
        File picture = new File(basePath,partTime.getFirstPicture());
        picture.delete();
        // 删除数据库的记录
        boolean b = partTimeService.deletePartTime(id);
        if(b){
            attributes.addFlashAttribute("message","删除成功");
        }else{
            attributes.addFlashAttribute("message","删除失败");
        }
        return "redirect:/admin/partTimes";
    }

    /**
     * 去修改页面
     * @param model
     * @return
     */
    @GetMapping("/partTime/{id}/input")
    public String toEditPage(Model model, @PathVariable Long id) {
        List<PaymentType> allPaymentType = paymentTypeService.getAllPaymentType();
        List<TypeVo> allType = typeService.getAllType();
        model.addAttribute("types", allType);
        model.addAttribute("payments", allPaymentType);
        model.addAttribute("units", unitService.getAllUnit());
        MerchantPartTime partTimeById = partTimeService.getPartTimeById(id);
        model.addAttribute("partTime", partTimeById);
        String[] split = partTimeById.getPrice().split("元/");
        model.addAttribute("price", split[0]);
        model.addAttribute("unitStr", split[1]);
        return "admin/partTime-input";
    }

    /**
     * 修改方法
     * @param partTime
     * @param price
     * @param unit
     * @param file
     * @param session
     * @param attributes
     * @return
     */
    @PostMapping("partTime/input")
    public String AddPartTime(PartTime partTime,
                              int price, int unit,
                              @RequestParam(value = "file", required = false) MultipartFile file,
                              HttpSession session,
                              RedirectAttributes attributes) {
        String priceStr = "";
        priceStr += price; // 将价格拼串；拼成 5元/单
        priceStr += "元/";
        List<Unit> allUnit = unitService.getAllUnit();
        for (Unit unit1 : allUnit) {
            if (unit1.getId() == unit) {
                priceStr += unit1.getName();
                break;
            }
        }
        partTime.setPrice(priceStr);
        partTime.setPublishDate(new Date());
        Admin user = (Admin) session.getAttribute("user");
        // 表示是修改
        partTime.setUpdateDate(new Date()); // 设置更新时间
        boolean b = false;
        if (file.isEmpty()) { // 没有上传图片
            // 重新写一个 update 方法,不修改 firstPicture 值
            b = partTimeService.updatePartTimeNoPicture(partTime);
        } else { // 上传了新的图片
            File srcFile = new File(basePath,partTime.getFirstPicture());
            srcFile.delete(); // 删掉之前的图片
            String filename = pictureFileUpload(file, user); // 将图片上传并返回处理后的文件名
            partTime.setFirstPicture(filename);
            // 调用 update 方法
            b = partTimeService.updatePartTime(partTime);
        }
        if (b) { // 修改成功
            attributes.addFlashAttribute("message", "修改成功");
        } else { // 失败
            attributes.addFlashAttribute("message", "修改失败");
        }
        return "redirect:/admin/partTimes";
    }

    /**
     * 将图片上传到服务器的 static/images 目录下
     * 并返回处理过后的文件名
     * @param file
     * @param user
     * @return
     */
    private String pictureFileUpload(MultipartFile file, Admin user) {
        String filename = user.getUsername() + UUID.randomUUID().toString().substring(0, 5) + new Date().getTime() + "_" + file.getOriginalFilename();
        File picture = new File(basePath,filename);
        try {
            file.transferTo(picture);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }

}
