package com.chen.part_time.service.impl;

import com.chen.part_time.dao.IAdminDao;
import com.chen.part_time.entity.Admin;
import com.chen.part_time.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 陈奕成
 * @create 2020 11 19 15:24
 */
@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private IAdminDao adminDao;

    @Override
    public Admin login(Admin admin) {
        return adminDao.login(admin);
    }
}
