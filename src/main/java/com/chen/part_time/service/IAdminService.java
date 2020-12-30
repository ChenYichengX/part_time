package com.chen.part_time.service;

import com.chen.part_time.entity.Admin;

/**
 * @author 陈奕成
 * @create 2020 11 19 15:24
 */
public interface IAdminService {

    /**
     * 登录判断
     * @param admin
     * @return
     */
    Admin login(Admin admin);
}
