package com.chen.part_time.web.admin;

import com.chen.part_time.entity.Ad;
import com.chen.part_time.entity.Admin;
import com.chen.part_time.entity.User;
import com.chen.part_time.service.IAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Create by ChenYicheng
 * 2021/1/7 17:33
 */
@Controller
@RequestMapping("/admin")
public class AdController {

    @Autowired
    private IAdService adService;

    private String basePath = ""; // 图片的基路径

    public AdController(){
        try {
            String path = ResourceUtils.getURL("classpath:").getPath() + "static/images";
            String realPath = path.replace("/","\\").substring(1,path.length());
//            System.out.println(realPath); // D:\gitRep\part_time\target\classes\static\images
            basePath = realPath;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/ad")
    public String getAdsByCode(Model model) {
        List<Ad> adsByCode = adService.getAdsByCode(null);
        model.addAttribute("ads",adsByCode);
        return "admin/ad";
    }



    @GetMapping("/ad/add")
    public String toAddPage() {
        return "admin/ad-input";
    }

    @PostMapping("/ad")
    public String insertAd(Ad ad, MultipartFile file, HttpServletRequest request, RedirectAttributes attributes) {
        HttpSession session = request.getSession();
        Admin user = (Admin) session.getAttribute("user");
        // 将图片放到项目下
        String fileName = pictureFileUpload(file, user);
        ad.setImageUrl(fileName);
        boolean b = adService.insertAd(ad);
        if(b){
            attributes.addFlashAttribute("message","发布成功");
        } else {
            attributes.addFlashAttribute("message","发布失败");
        }
        return "redirect:/admin/ad";
    }

    @GetMapping("/ad/{id}/input")
    public String toUpdatePage(@PathVariable Long id,Model model){
        Ad adById = adService.getAdById(id);
        model.addAttribute("ad",adById);
        return "admin/ad-input";
    }

    @GetMapping("/ad/{id}/delete")
    public String deleteAdById(@PathVariable("id") Long id, RedirectAttributes attributes) {
        Ad adById = adService.getAdById(id);
        if(adById == null){
            attributes.addFlashAttribute("message","删除失败");
            return "redirect:/admin/ad";
        }
        // 删除图片
        File picture = new File(basePath,adById.getImageUrl());
        picture.delete();
        // 删除数据库记录
        boolean b = adService.deleteAdById(id);
        if (b) {
            attributes.addFlashAttribute("message","删除成功");
        }else{
            attributes.addFlashAttribute("message","删除失败");
        }
        return "redirect:/admin/ad";
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
