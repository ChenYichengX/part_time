package com.chen.part_time.dao;

import com.chen.part_time.entity.Unit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 陈奕成
 * @create 2020 11 21 17:31
 */
@Mapper
public interface IUnitDao {

    /**
     * 新增类型
     * @return
     */
    boolean saveUnit(Unit unit);

    /**
     * 查询
     * @param id
     * @return
     */
    Unit getUnit(Long id);

    /**
     * 查询
     * @param name
     * @return
     */
    Unit getUnitByName(String name);

    /**
     * 查询所有
     * @return
     */
    List<Unit> getAllUnit();

    /**
     * 修改
     * @param unit
     * @return
     */
    boolean updateUnit(Unit unit);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean deleteUnit(Long id);
}
