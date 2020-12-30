package com.chen.part_time.vo;

import java.util.Date;

/**
 * @author 陈奕成
 * @create 2020 12 17 16:12
 */
public class ApplyStu {
    private Long student_id; // 学生 id
    private String username; // 用户名
    private Date apply_time; // 申请时间
    private String phone; // 电话

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getApply_time() {
        return apply_time;
    }

    public void setApply_time(Date apply_time) {
        this.apply_time = apply_time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "ApplyStu{" +
                "student_id=" + student_id +
                ", username='" + username + '\'' +
                ", apply_time=" + apply_time +
                ", phone='" + phone + '\'' +
                '}';
    }
}
