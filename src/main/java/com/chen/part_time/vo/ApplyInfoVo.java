package com.chen.part_time.vo;

import java.util.Date;
import java.util.List;

/**
 * @author 陈奕成
 * @create 2020 12 16 16:33
 */
public class ApplyInfoVo {
    private Long part_time_id; // 兼职信息 id
    private String title; // 标题
    private String type_name; // 类型 name
    private Date publishDate; // 发布时间
    private String price; // 价格
    private Long user_id; // 商家 id
    private Integer doing; // 兼职状态
    private List<ApplyStu> stus; // 学生的申请信息

    public Integer getDoing() {
        return doing;
    }

    public void setDoing(Integer doing) {
        this.doing = doing;
    }

    public Long getPart_time_id() {
        return part_time_id;
    }

    public void setPart_time_id(Long part_time_id) {
        this.part_time_id = part_time_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public List<ApplyStu> getStus() {
        return stus;
    }

    public void setStus(List<ApplyStu> stus) {
        this.stus = stus;
    }

    @Override
    public String toString() {
        return "ApplyInfoVo{" +
                "part_time_id=" + part_time_id +
                ", title='" + title + '\'' +
                ", type_name='" + type_name + '\'' +
                ", publishDate=" + publishDate +
                ", price='" + price + '\'' +
                ", user_id=" + user_id +
                ", stus=" + stus +
                '}';
    }
}
