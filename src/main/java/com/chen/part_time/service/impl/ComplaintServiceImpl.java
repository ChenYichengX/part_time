package com.chen.part_time.service.impl;

import com.chen.part_time.dao.IComplaintDao;
import com.chen.part_time.entity.Comment;
import com.chen.part_time.entity.Complaint;
import com.chen.part_time.service.ICommentService;
import com.chen.part_time.service.IComplaintService;
import com.chen.part_time.vo.ComplaintVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 陈奕成
 * @create 2021 03 31 22:43
 */
@Service
public class ComplaintServiceImpl implements IComplaintService {

    @Autowired
    private IComplaintDao complaintDao;


    @Override
    public boolean addComplaint(Complaint complaint) {
        return complaintDao.addComplaint(complaint);
    }

    @Override
    public List<ComplaintVo> getAllByComplaint(String merchantName, String title, Integer flag) {
        return complaintDao.getAllByComplaint(merchantName,title,flag);
    }

    @Override
    public boolean editFlag(Long id) {
        return complaintDao.editFlag(id);
    }
}
