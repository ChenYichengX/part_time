package com.chen.part_time.dao;

import com.chen.part_time.entity.PartTime;
import com.chen.part_time.entity.PartTimeCondition;
import com.chen.part_time.vo.MerchantPartTime;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 兼职信息的一些操作
 * @author 陈奕成
 * @create 2020 11 21 9:34
 */
@Mapper
public interface IPartTimeDao {

    MerchantPartTime getPartTimeById(Long id);

    /**
     * 一个动态的分页查询
     * @param partTime
     * @return
     */
    List<MerchantPartTime> getAllPartTime(PartTimeCondition partTime);

    /**
     * 根据浏览次数来排序
     * @return
     */
    List<MerchantPartTime> getAllPartTimeByView(PartTimeCondition partTime);

    // 新增
    boolean savePartTime(PartTime partTime);

    // 更新
    boolean updatePartTime(PartTime partTime);

    // 更新不带图片的
    boolean updatePartTimeNoPicture(PartTime partTime);

    // 删除
    boolean deletePartTime(Long id);

    /**
     * 根据 id 对浏览次数加一
     * @param id
     */
    void addViewsById(Long id);

    boolean updateDoing(@Param("id") Long id, @Param("type") Integer type);

}
