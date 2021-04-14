package com.chen.part_time.dao;

import com.chen.part_time.entity.Complaint;
import com.chen.part_time.vo.ComplaintVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 申述 Dao
 * @author 陈奕成
 * @create 2021 03 31 22:19
 */
@Mapper
public interface IComplaintDao {

    // 添加申述
    boolean addComplaint(Complaint complaint);

    // 查询申述
    List<ComplaintVo> getAllByComplaint(@Param("merchantName") String merchantName,
                                        @Param("title") String title,
                                        @Param("flag") Integer flag);

    // 已处理
    boolean editFlag(Long id);
}
