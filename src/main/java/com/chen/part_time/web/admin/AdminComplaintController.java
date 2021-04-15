package com.chen.part_time.web.admin;

import com.chen.part_time.service.IComplaintService;
import com.chen.part_time.vo.ComplaintVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理员界面-申述信息-Controller
 * @author 陈奕成
 * @create 2021 04 08 22:54
 */
@Controller
@RequestMapping("/admin")
public class AdminComplaintController {

    @Autowired
    private IComplaintService complaintService;


    /**
     * 去申述信息页面
     * @param pageNumber
     * @param model
     * @return
     */
    @GetMapping("/complaint")
    public String list(@RequestParam(required = false) Integer pageNumber, Model model){
        int pageNum = 1;
        if(pageNumber != null && pageNumber != 0){
            pageNum = pageNumber;
        }
        PageHelper.startPage(pageNum,1);
        List<ComplaintVo> allByComplaint = complaintService.getAllByComplaint("","",null);
        PageInfo<ComplaintVo> pageInfo = new PageInfo<>(allByComplaint,5);
        model.addAttribute("info",pageInfo);
        return "admin/complaint";
    }

    /**
     * @Author ChenYicheng
     * @Description 申述列表
     * @Date 2021/4/15 9:45
     */
    @PostMapping("/complaint/search")
    public String search(String title,String merchantName,Integer flag,Integer page,Model model){
        int pageNum = 1;
        if(page != null && page != 0){
            pageNum = page;
        }
        PageHelper.startPage(pageNum,1);
        List<ComplaintVo> allByComplaint = complaintService.getAllByComplaint(merchantName, title, flag);
        PageInfo<ComplaintVo> info = new PageInfo<>(allByComplaint, 5);
        model.addAttribute("info",info);
        return "admin/complaint :: complaintList";
    }

    /**
     * @Author ChenYicheng
     * @Description 修改申述信息状态
     * @Date 2021/4/15 9:45
     */
    @PutMapping("/complaint/{id}")
    @ResponseBody
    public String updateFlag(@PathVariable Long id){
        boolean b = complaintService.editFlag(id);
        if(b){
            return "success";
        }
        return "fail";
    }

}
