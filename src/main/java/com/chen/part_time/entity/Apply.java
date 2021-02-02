package com.chen.part_time.entity;

import java.util.Date;

/**
 * 申请兼职记录
 * @author 陈奕成
 * @create 2020 12 09 10:27
 */
public class Apply {
    private Long id; // 主键 id
    private Long part_time_id; // 兼职信息 id
    private Long user_id; // 用户 id
    private Date apply_time; // 申请时间
    private Integer choose; // 已选择1、未选择0
    private Date start_time; // 兼职开始时间
    private Date over_time; // 兼职结束时间

    public Apply() {
    }

    public Apply(Long part_time_id, Long user_id) {
        this.part_time_id = part_time_id;
        this.user_id = user_id;
    }

    public Apply(Long part_time_id, Long user_id, Date apply_time) {
        this.part_time_id = part_time_id;
        this.user_id = user_id;
        this.apply_time = apply_time;
    }

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

    public Date getApply_time() {
        return apply_time;
    }

    public void setApply_time(Date apply_time) {
        this.apply_time = apply_time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPart_time_id() {
        return part_time_id;
    }

    public void setPart_time_id(Long part_time_id) {
        this.part_time_id = part_time_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
