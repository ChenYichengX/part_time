package com.chen.part_time.dao;

import com.chen.part_time.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 陈奕成
 * @create 2020 11 18 20:39
 */
@Repository
@Mapper
public interface IUserDao {

    /**
     * 登录判断
     * @param user
     * @return
     */
    User checkUser(User user);

    /**
     * 检查 username 是否存在
     * @param username
     * @return
     */
    int checkUsername(String username);

    /**
     * 新增一个
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 查询用户信息通过 id
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 查询用户信息通过 兼职信息的 id
     * @param part_time_id
     * @return
     */
    User getUserByPart_time_id(Long part_time_id);

    /**
     * 通过 part_time_id 获取申请了的的学生信息
     * @param part_time_id
     * @return
     */
    List<User> getUsersByPart_time_id(Long part_time_id);
}
