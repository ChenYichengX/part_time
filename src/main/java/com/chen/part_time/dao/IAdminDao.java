package com.chen.part_time.dao;

import com.chen.part_time.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 陈奕成
 * @create 2020 11 19 15:21
 */
@Mapper
@Repository
public interface IAdminDao {
    /**
     * 登录判断
     * @param admin
     * @return
     */
    Admin login(Admin admin);
}
