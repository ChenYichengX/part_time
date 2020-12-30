package com.chen.part_time.service.impl;

import com.chen.part_time.dao.IUnitDao;
import com.chen.part_time.entity.Unit;
import com.chen.part_time.service.IUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 陈奕成
 * @create 2020 11 21 17:38
 */
@Service
public class UnitServiceImpl implements IUnitService {
    @Autowired
    private IUnitDao unitDao;

    @Override
    public boolean saveUnit(Unit unit) {
        return unitDao.saveUnit(unit);
    }

    @Override
    public Unit getUnit(Long id) {
        return unitDao.getUnit(id);
    }

    @Override
    public Unit getUnitByName(String name) {
        return unitDao.getUnitByName(name);
    }

    @Override
    public List<Unit> getAllUnit() {
        return unitDao.getAllUnit();
    }

    @Override
    public boolean updateUnit(Unit unit) {
        return unitDao.updateUnit(unit);
    }

    @Override
    public boolean deleteUnit(Long id) {
        return unitDao.deleteUnit(id);
    }
}
