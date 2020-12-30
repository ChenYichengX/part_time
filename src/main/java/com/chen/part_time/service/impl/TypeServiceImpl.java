package com.chen.part_time.service.impl;

import com.chen.part_time.dao.ITypeDao;
import com.chen.part_time.entity.Type;
import com.chen.part_time.service.ITypeService;
import com.chen.part_time.vo.TypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 陈奕成
 * @create 2020 11 19 20:59
 */
@Service
public class TypeServiceImpl implements ITypeService {

    @Autowired
    private ITypeDao typeDao;

    @Transactional
    @Override
    public boolean saveType(Type type) {
        return typeDao.saveType(type);
    }

    @Override
    public Type getType(Long id) {
        return typeDao.getType(id);
    }

    @Override
    public List<TypeVo> getAllType() {
        return typeDao.getAllType();
    }

    @Transactional
    @Override
    public boolean updateType(Type type) {
        return typeDao.updateType(type);
    }

    @Transactional
    @Override
    public boolean deleteType(Long id) {
        return typeDao.deleteType(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }
}
