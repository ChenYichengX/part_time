package com.chen.part_time.vo;

import java.util.Date;

/**
 * 学生的兼职记录
 * Create by ChenYicheng
 * 2021/2/6 14:03
 */
public class StuApplyInfoVo {

    private Long partTimeId; // 兼职信息 id
    private String title; // 标题
    private String typeName; // 类型 name
    private String price; // 价格
    private String payName; // 结算方式 name
    private Date apply_time; // 申请时间
    private Integer choose; // 待确认0、已确认1、已开始2、已结束3
    private Date start_time; // 兼职开始时间
    private Date over_time; // 兼职开始时间

    public Long getPartTimeId() {
        return partTimeId;
    }

    public void setPartTimeId(Long partTimeId) {
        this.partTimeId = partTimeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public Date getApply_time() {
        return apply_time;
    }

    public void setApply_time(Date apply_time) {
        this.apply_time = apply_time;
    }

    public Integer getChoose() {
        return choose;
    }

    public void setChoose(Integer choose) {
        this.choose = choose;
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
}
