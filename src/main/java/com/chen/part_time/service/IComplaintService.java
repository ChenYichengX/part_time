package com.chen.part_time.service;

import com.chen.part_time.entity.Complaint;
import com.chen.part_time.vo.ComplaintVo;

import java.util.List;

/**
 * 申述管理 Service
 * @author 陈奕成
 * @create 2021 03 31 22:42
 */
public interface IComplaintService {
    // 添加申述
    boolean addComplaint(Complaint complaint);

    // 查询申述
    List<ComplaintVo> getAllByComplaint(String merchantName, String title, Integer flag);

    // 设为已处理
    boolean editFlag(Long id);

}
