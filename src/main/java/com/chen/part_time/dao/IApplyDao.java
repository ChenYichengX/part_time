package com.chen.part_time.dao;

import com.chen.part_time.entity.Apply;
import com.chen.part_time.vo.ApplyInfoVo;
import com.chen.part_time.vo.StuApplyInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 陈奕成
 * @create 2020 12 09 10:42
 */
@Mapper
public interface IApplyDao {

    // 添加申请信息
    boolean addApply(Apply apply);

    // 删除该兼职信息下的所有的申请记录
    boolean deleteOtherApply(Apply apply);

    // 查询该用户是否已经申请该兼职
    int selectApply(Apply apply);

    // 查询申请记录（给商家显示）
    List<ApplyInfoVo> getApplyInfoByUser_id(Long user_id);

    // 将学生的选择状态改
    boolean updateStuChoose(Apply apply);

    // 查询兼职记录（通过学生id）
    List<StuApplyInfoVo> getApplyInfoByStuId(Long stuId);

    // 通过兼职 part_time_id 和 user_id 查询兼职状态
    Apply getOneByApply(Apply apply);
}
