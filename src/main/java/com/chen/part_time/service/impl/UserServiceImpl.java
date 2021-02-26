package com.chen.part_time.service.impl;

import com.chen.part_time.dao.IUserDao;
import com.chen.part_time.entity.User;
import com.chen.part_time.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 陈奕成
 * @create 2020 11 18 21:04
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public User checkUser(User user) {
        return userDao.checkUser(user);
    }

    @Override
    public int checkUsername(String username) {
        return userDao.checkUsername(username);
    }

    @Transactional
    @Override
    public int insertUser(User user) {
        int i = checkUsername(user.getUsername());
        if(i > 0){ // 已存在
            return -1;
        }
        return userDao.insertUser(user);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByPart_time_id(Long part_time_id) {
        return userDao.getUserByPart_time_id(part_time_id);
    }

    @Override
    public List<User> getUsersByPart_time_id(Long part_time_id) {
        return userDao.getUsersByPart_time_id(part_time_id);
    }
}
