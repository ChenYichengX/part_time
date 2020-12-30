package com.chen.part_time.entity;

import java.util.Date;

/**
 * 用户类
 * @author 陈奕成
 * @create 2020 11 18 17:29
 */
public class User {
    private Long id; // 主键 id
    private String username; // 用户名
    private String nickName; // 昵称
    private String password; // 密码
    private String email; // 邮箱
    private String phone; // 电话
    private Date createDate; // 创建时间
    private int type; // 0为学生，1为商家

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(Long id, String username, String nickName, String password, String email, String phone, Date createDate, int type) {
        this.id = id;
        this.username = username;
        this.nickName = nickName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.createDate = createDate;
        this.type = type;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", createDate=" + createDate +
                ", type=" + type +
                '}';
    }
}
