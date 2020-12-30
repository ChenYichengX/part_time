package com.chen.part_time.service.impl;

import com.chen.part_time.dao.IApplyDao;
import com.chen.part_time.entity.Apply;
import com.chen.part_time.service.IApplyService;
import com.chen.part_time.vo.ApplyInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 陈奕成
 * @create 2020 12 15 9:44
 */
@Service
public class ApplyServiceImpl implements IApplyService {
    @Autowired
    private IApplyDao applyDao;

    @Override
    public boolean addApply(Apply apply) {
        return applyDao.addApply(apply);
    }

    @Override
    public boolean deleteOtherApply(Apply apply) {
        return applyDao.deleteOtherApply(apply);
    }

    @Override
    public int selectApply(Apply apply) {
        return applyDao.selectApply(apply);
    }

    @Override
    public List<ApplyInfoVo> getApplyInfoByUser_id(Long user_id) {
        return applyDao.getApplyInfoByUser_id(user_id);
    }
}
