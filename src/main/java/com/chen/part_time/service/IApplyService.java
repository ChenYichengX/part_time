package com.chen.part_time.service;

import com.chen.part_time.entity.Apply;
import com.chen.part_time.vo.ApplyInfoVo;
import com.chen.part_time.vo.StuApplyInfoVo;

import java.util.List;

/**
 * 申请兼职信息
 * @author 陈奕成
 * @create 2020 12 15 9:44
 */
public interface IApplyService {

    // 添加申请信息
    boolean addApply(Apply apply);

    // 删除该兼职信息下的所有的申请记录
    boolean deleteOtherApply(Apply apply);

    // 查询该用户是否已经申请该兼职
    int selectApply(Apply apply);

    // 查询申请记录（给商家显示）
    List<ApplyInfoVo> getApplyInfoByUser_id(Long user_id);

    // 将学生的选择状态改为1
    boolean updateStuChoose(Apply apply);

    // 查询兼职记录（通过学生id）
    List<StuApplyInfoVo> getApplyInfoByStuId(Long stuId);
}
