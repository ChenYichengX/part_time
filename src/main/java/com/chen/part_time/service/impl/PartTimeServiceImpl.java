package com.chen.part_time.service.impl;

import com.chen.part_time.dao.IPartTimeDao;
import com.chen.part_time.entity.PartTime;
import com.chen.part_time.entity.PartTimeCondition;
import com.chen.part_time.service.IPartTimeService;
import com.chen.part_time.vo.MerchantPartTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 陈奕成
 * @create 2020 11 21 10:05
 */
@Service
public class PartTimeServiceImpl implements IPartTimeService {

    @Autowired
    private IPartTimeDao partTimeDao;

    @Override
    public MerchantPartTime getPartTimeById(Long id) {
        return partTimeDao.getPartTimeById(id);
    }

    @Override
    public List<MerchantPartTime> getAllPartTime(PartTimeCondition partTime) {
        return partTimeDao.getAllPartTime(partTime);
    }

    @Override
    public List<MerchantPartTime> getAllPartTimeByView(PartTimeCondition partTime) {
        return partTimeDao.getAllPartTimeByView(partTime);
    }


    @Transactional
    @Override
    public boolean savePartTime(PartTime partTime) {
        return partTimeDao.savePartTime(partTime);
    }

    @Transactional
    @Override
    public boolean updatePartTime(PartTime partTime) {
        return partTimeDao.updatePartTime(partTime);
    }

    @Override
    public boolean updatePartTimeNoPicture(PartTime partTime) {
        return partTimeDao.updatePartTimeNoPicture(partTime);
    }

    @Transactional
    @Override
    public boolean deletePartTime(Long id) {
        return partTimeDao.deletePartTime(id);
    }

    @Override
    public void addViewsById(Long id) {
        partTimeDao.addViewsById(id);
    }
}
