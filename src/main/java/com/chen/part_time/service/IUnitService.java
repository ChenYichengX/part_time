package com.chen.part_time.service;

import com.chen.part_time.entity.Unit;

import java.util.List;

/**
 * 价格单位 CRUD
 * @author 陈奕成
 * @create 2020 11 21 17:37
 */
public interface IUnitService {

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
