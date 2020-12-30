package com.chen.part_time.entity;

import java.util.Date;

/**
 * 兼职信息类
 * @author 陈奕成
 * @create 2020 11 18 17:18
 */
public class PartTime {
    private Long id; //主键 id
    private Long user_id; // 用户 id
    private Long type_id; // 类型 id
    private String title; // 标题
    private String content; // 内容
    private String require_text; // 要求
    private Date publishDate; // 发布时间
    private String treatment; // 待遇
    private String price; // 价格
    private Long pay_id; // 支付类型 id
    private Date updateDate; // 更新时间
    private String firstPicture; // 图片地址
    private Integer views; // 浏览次数
    private Integer doing; // 0是发布状态，1是已兼职。是否已兼职

    public PartTime() {
    }

    public PartTime(Long type_id, String title) {
        this.type_id = type_id;
        this.title = title;
    }


    public PartTime(Long id, Long user_id, Long type_id, String title, String content, String require_text, Date publishDate, String treatment, String price, Long pay_id, Date updateDate, String firstPicture, Integer views) {
        this.id = id;
        this.user_id = user_id;
        this.type_id = type_id;
        this.title = title;
        this.content = content;
        this.require_text = require_text;
        this.publishDate = publishDate;
        this.treatment = treatment;
        this.price = price;
        this.pay_id = pay_id;
        this.updateDate = updateDate;
        this.firstPicture = firstPicture;
        this.views = views;
    }

    @Override
    public String toString() {
        return "PartTime{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", type_id=" + type_id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", require_text='" + require_text + '\'' +
                ", publishDate=" + publishDate +
                ", treatment='" + treatment + '\'' +
                ", price='" + price + '\'' +
                ", pay_id=" + pay_id +
                ", updateDate=" + updateDate +
                ", firstPicture='" + firstPicture + '\'' +
                ", views=" + views +
                '}';
    }

    public Integer getDoing() {
        return doing;
    }

    public void setDoing(Integer doing) {
        this.doing = doing;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getType_id() {
        return type_id;
    }

    public void setType_id(Long type_id) {
        this.type_id = type_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRequire_text() {
        return require_text;
    }

    public void setRequire_text(String require_text) {
        this.require_text = require_text;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public Long getPay_id() {
        return pay_id;
    }

    public void setPay_id(Long pay_id) {
        this.pay_id = pay_id;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }
}
