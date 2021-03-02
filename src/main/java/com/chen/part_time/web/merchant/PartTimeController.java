package com.chen.part_time.web.merchant;

import com.chen.part_time.entity.*;
import com.chen.part_time.service.*;
import com.chen.part_time.vo.ApplyInfoVo;
import com.chen.part_time.vo.MerchantPartTime;
import com.chen.part_time.vo.TypeVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 陈奕成
 * @create 2020 11 21 10:09
 */
@Controller
@RequestMapping("/merchant")
public class PartTimeController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IPartTimeService partTimeService;
    @Autowired
    private ITypeService typeService;
    @Autowired
    private IPaymentTypeService paymentTypeService;
    @Autowired
    private IUnitService unitService;
    @Autowired
    private IApplyService applyService;
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    private String host = "123.57.174.182";
    private int port = 6379;

    private File basePath; // 图片的基路径

    public PartTimeController() {
        try {
//            String path = ResourceUtils.getURL("classpath:").getPath() + "static/images";
//            String realPath = path.replace("/", "\\").substring(1, path.length());
            File rootPath = new File(ResourceUtils.getURL("classpath:").getPath());
//            String realPath = path.substring(1);
//            System.out.println(realPath); // D:\gitRep\part_time\target\classes\static\images
            if(!rootPath.exists()){
                rootPath = new File("");
            }
            basePath = new File(rootPath.getAbsolutePath()+"/images");
            if(!basePath.exists()){
                //不存在，创建
                basePath.mkdirs();
            }
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
        User user = (User) session.getAttribute("user");
        PartTimeCondition partTime = new PartTimeCondition();
        partTime.setUser_id(user.getId());
        PageHelper.startPage(pageNum, 6);
        List<MerchantPartTime> allPartTime = partTimeService.getAllPartTime(partTime);
        PageInfo<MerchantPartTime> pageInfo = new PageInfo<>(allPartTime, 5);
        model.addAttribute("info", pageInfo);
        model.addAttribute("types", typeService.getAllType());
        return "merchant/partTime";
    }

    @PostMapping("partTimes/search")
    public String search(@RequestParam(required = false) Integer page, String title, Long typeId, Model model, HttpSession session) {
        int pageNum = 1;
        if (page != null && page != 0) {
            pageNum = page;
        }
        User user = (User) session.getAttribute("user");
        PartTimeCondition partTime = new PartTimeCondition(typeId, title);
        partTime.setUser_id(user.getId());
        PageHelper.startPage(pageNum, 6);
        List<MerchantPartTime> allPartTime = partTimeService.getAllPartTime(partTime);
        PageInfo<MerchantPartTime> pageInfo = new PageInfo<>(allPartTime, 5);
        model.addAttribute("info", pageInfo);
        model.addAttribute("types", typeService.getAllType());
        return "merchant/partTime :: partTimeList";
    }

    /**
     * 去新增页面
     *
     * @param model
     * @return
     */
    @GetMapping("/partTime/input")
    public String toInputPage(Model model) {
        List<PaymentType> allPaymentType = paymentTypeService.getAllPaymentType();
        List<TypeVo> allType = typeService.getAllType();
        model.addAttribute("types", allType);
        model.addAttribute("payments", allPaymentType);
        model.addAttribute("units", unitService.getAllUnit());
        model.addAttribute("partTime", new PartTime());
        return "merchant/partTime-input";
    }

    /**
     * 去修改页面
     *
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
        return "merchant/partTime-input";
    }

    /**
     * 新增和修改同一个方法
     *
     * @param partTime
     * @param price
     * @param unit
     * @param file
     * @param session
     * @param request
     * @param attributes
     * @return
     */
    @PostMapping("partTime/input")
    public String AddPartTime(PartTime partTime,
                              String price, int unit,
                              @RequestParam(value = "file", required = false) MultipartFile file,
                              HttpSession session,
                              HttpServletRequest request,
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
        User user = (User) session.getAttribute("user");
        partTime.setUser_id(user.getId());
        if (partTime.getId() != null) { // 表示是修改
            partTime.setUpdateDate(new Date()); // 设置更新时间
            boolean b = false;
            if (file.isEmpty()) { // 没有上传图片
                // 重新写一个 update 方法,不修改 firstPicture 值
                b = partTimeService.updatePartTimeNoPicture(partTime);
            } else { // 上传了新的图片
                File srcFile = new File(basePath, partTime.getFirstPicture());
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
            return "redirect:/merchant/partTimes";
        }
        // 表示是新增
        if (file.isEmpty()) { // 没有上传图片
            partTime.setFirstPicture("");
        } else { // 上传了图片
            String filename = pictureFileUpload(file, user);
            partTime.setFirstPicture(filename);
        }
        boolean b = partTimeService.savePartTime(partTime);
        if (b) { // 发布兼职信息成功
            attributes.addFlashAttribute("message", "发布成功");
        } else { // 失败
            attributes.addFlashAttribute("message", "发布失败");
        }
        return "redirect:/merchant/partTimes";
    }

    /**
     * 将图片上传到服务器的 static/images 目录下
     * 并返回处理过后的文件名
     *
     * @param file
     * @param user
     * @return
     */
    private String pictureFileUpload(MultipartFile file, User user) {
        String filename = user.getUsername() + UUID.randomUUID().toString().substring(0, 5) + new Date().getTime() + "_" + file.getOriginalFilename();
        File picture = new File(basePath, filename);
        try {
            file.transferTo(picture);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }

    /**
     * 删除
     *
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/partTime/{id}/delete")
    public String deleteFile(@PathVariable Long id, RedirectAttributes attributes, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User merchant = (User) session.getAttribute("user");
        // 判断该兼职是否可删
        List<Apply> oneByApply = applyService.getOneByApply(new Apply(id, null));
        for (Apply apply : oneByApply) {
            if(apply.getChoose() > Apply.CHOOSE_UNSELECTED && apply.getChoose() != Apply.CHOOSE_OVER){
                attributes.addFlashAttribute("message", "删除失败,该兼职已选择了学生，不可删除");
                return "redirect:/merchant/partTimes";
            }
        }
        PartTimeCondition partTimeCondition = new PartTimeCondition();
        partTimeCondition.setUser_id(merchant.getId());
        List<MerchantPartTime> allPartTime = partTimeService.getAllPartTime(partTimeCondition);
        for (MerchantPartTime merchantPartTime : allPartTime) {
            if (merchantPartTime.getId().equals(id)) {
                // 获取所有申请人的信息
                List<User> stus = userService.getUsersByPart_time_id(id);
                if(stus != null && stus.size() > 0){
                    // 发送通知
                    for (User user : stus) {
                        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                        simpleMailMessage.setSubject("通知");
                        simpleMailMessage.setText("亲爱的" + user.getNickName() + ",刚刚" + merchant.getUsername() + "删除了标题为" + merchantPartTime.getTitle() + ",类型为" +
                                merchantPartTime.getType_name() + "的兼职信息,您的申请已无效{{{(>_<)}}},快去看看又有哪些新的兼职吧");
                        simpleMailMessage.setTo(user.getEmail());
                        simpleMailMessage.setFrom("361415506@qq.com");
                        javaMailSender.send(simpleMailMessage);
                    }
                    // 删除申请记录
                    applyService.deleteOtherApply(new Apply(id, null));
                }
                // 删除图片
                File picture = new File(basePath, merchantPartTime.getFirstPicture());
                picture.delete();
                // 删除数据库的记录
                boolean b = partTimeService.deletePartTime(id);
                if (b) {
                    attributes.addFlashAttribute("message", "删除成功");
                } else {
                    attributes.addFlashAttribute("message", "删除失败");
                }
                return "redirect:/merchant/partTimes";
            }
        }
        attributes.addFlashAttribute("message", "删除失败，该兼职信息不存在");
        return "redirect:/merchant/partTimes";
    }

    /**
     * 查看申请记录
     *
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/apply")
    public String applyRecord(@RequestParam(required = false) Integer page,HttpServletRequest request, Model model) {
        int pageNum = 1;
        if (page != null && page != 0) {
            pageNum = page;
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        PageHelper.startPage(pageNum, 6);
        List<ApplyInfoVo> applyInfoByUser_id = applyService.getApplyInfoByUser_id(user.getId());
        PageInfo<ApplyInfoVo> pageInfo = new PageInfo(applyInfoByUser_id, 5);
        model.addAttribute("applys", pageInfo);
        return "merchant/jobs";
    }

    @PostMapping("/apply/search")
    public String applyRecordSearch(@RequestParam(required = false) Integer page,HttpServletRequest request, Model model) {
        int pageNum = 1;
        if (page != null && page != 0) {
            pageNum = page;
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        PageHelper.startPage(pageNum, 6);
        List<ApplyInfoVo> applyInfoByUser_id = applyService.getApplyInfoByUser_id(user.getId());
        PageInfo<ApplyInfoVo> pageInfo = new PageInfo(applyInfoByUser_id, 5);
        model.addAttribute("applys", pageInfo);
        return "merchant/jobs :: applyList";
    }

    /**
     * 删除有人申请的兼职信息,并通知申请人
     * @param id
     * @param attributes
     * @return
     */
    /*@GetMapping("/partTime/{id}/delApply")
    public String deleteFileAndApply(@PathVariable Long id, RedirectAttributes attributes, HttpServletRequest request) {
        // 获取商家信息
        HttpSession session = request.getSession();
        User merchant = (User) session.getAttribute("user");
        // 判断该兼职是否可删
        List<Apply> oneByApply = applyService.getOneByApply(new Apply(id, null));
        for (Apply apply : oneByApply) {
            if(apply.getChoose() > 0){
                attributes.addFlashAttribute("message", "删除失败,该兼职已选择了学生，不可删除");
                return "redirect:/merchant/apply";
            }
        }
        PartTimeCondition partTimeCondition = new PartTimeCondition();
        partTimeCondition.setUser_id(merchant.getId());
        List<MerchantPartTime> allPartTime = partTimeService.getAllPartTime(partTimeCondition);
        for (MerchantPartTime merchantPartTime : allPartTime) {
            if (merchantPartTime.getId().equals(id)) {
                // 删除图片
                File picture = new File(basePath, merchantPartTime.getFirstPicture());
                picture.delete();
                // 获取所有申请人的信息
                List<User> stus = userService.getUsersByPart_time_id(id);
                // 发送通知
                for (User user : stus) {
                    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                    simpleMailMessage.setSubject("通知");
                    simpleMailMessage.setText("亲爱的" + user.getNickName() + ",刚刚" + merchant.getUsername() + "删除了标题为" + merchantPartTime.getTitle() + ",类型为" +
                            merchantPartTime.getType_name() + "的兼职信息,您的申请已无效{{{(>_<)}}},快去看看又有哪些新的兼职吧");
                    simpleMailMessage.setTo(user.getEmail());
                    simpleMailMessage.setFrom("361415506@qq.com");
                    javaMailSender.send(simpleMailMessage);
                }

                // 删除数据库的记录
                boolean b = partTimeService.deletePartTime(id);
                if (b) {
                    attributes.addFlashAttribute("message", "删除成功");
                } else {
                    attributes.addFlashAttribute("message", "删除失败");
                }
                return "redirect:/merchant/apply";
            }
        }
        attributes.addFlashAttribute("message", "删除失败，该兼职信息不存在");
        return "redirect:/merchant/apply";
    }*/

    @GetMapping("/choose")
    @ResponseBody
    public String choose(Long partTimeId, Long stuId, String partTimeName, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User merchant = (User) session.getAttribute("user"); // 当前用户
        User stu = userService.getUserById(stuId);
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setSubject("【南理兼职平台】-通知");
            simpleMailMessage.setText("亲爱的" + stu.getUsername() + ",刚刚商家" + merchant.getUsername() +
                    "的一个标题为 ’" + partTimeName + "‘ 的兼职选择您作为了兼职人，请尽快给他回复吧");
            simpleMailMessage.setTo(stu.getEmail());
            simpleMailMessage.setFrom("361415506@qq.com");
            javaMailSender.send(simpleMailMessage);

            // 将选择表中的选择状态改为 CHOOSE_SELECTED
            Apply apply = new Apply(partTimeId, stuId);
            apply.setChoose(Apply.CHOOSE_SELECTED);
            applyService.updateStuChoose(apply);
        } catch (MailException e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @GetMapping("/start")
    @ResponseBody
    public String startJob(Long partTimeId, Long stuId, String partTimeName) {
        User stu = userService.getUserById(stuId);
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setSubject("【南理兼职平台】-通知");
            simpleMailMessage.setText("亲爱的" + stu.getUsername() + ",您的’" + partTimeName + "‘兼职已经开始。");
            simpleMailMessage.setTo(stu.getEmail());
            simpleMailMessage.setFrom("361415506@qq.com");
            javaMailSender.send(simpleMailMessage);

            // 将选择表中的选择状态改为 CHOOSE_STARTED
            Apply apply = new Apply(partTimeId, stuId);
            apply.setChoose(Apply.CHOOSE_STARTED);
            apply.setStart_time(new Date());
            applyService.updateStuChoose(apply);
        } catch (MailException e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @GetMapping("/over")
    @ResponseBody
    public String overJob(Long partTimeId, Long stuId, String partTimeName) {
        User stu = userService.getUserById(stuId);
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setSubject("【南理兼职平台】-通知");
            simpleMailMessage.setText("亲爱的" + stu.getUsername() + ",您的’" + partTimeName + "‘兼职已经结束。");
            simpleMailMessage.setTo(stu.getEmail());
            simpleMailMessage.setFrom("361415506@qq.com");
            javaMailSender.send(simpleMailMessage);

            // 将选择表中的选择状态改为 CHOOSE_OVER
            Apply apply = new Apply(partTimeId, stuId);
            apply.setChoose(Apply.CHOOSE_OVER);
            apply.setOver_time(new Date());
            applyService.updateStuChoose(apply);
        } catch (MailException e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }
}
