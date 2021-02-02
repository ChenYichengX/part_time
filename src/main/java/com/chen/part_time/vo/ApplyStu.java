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
    private Integer choose; // 已选择1、未选择0
    private Date start_time; // 兼职开始时间
    private Date over_time; // 兼职开始时间

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getOver_time() {
        return over_time;
    }

    public void setOver_time(Date over_time) {
        this.over_time = over_time;
    }

    public Integer getChoose() {
        return choose;
    }

    public void setChoose(Integer choose) {
        this.choose = choose;
    }

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
