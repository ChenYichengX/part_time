package com.chen.part_time.entity;

/**
 * Create by ChenYicheng
 * 2020/12/30 14:41
 */
public class Ad {

    private Long id; // 主键 id
    private String code; // 广告位 code
    private String title; // 广告标题
    private String imageUrl; // 图片地址
    private Integer actionType; // 跳转类型 1 当前窗口，2 新增窗口
    private String actionValue; // 跳转地址

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getActionType() {
        return actionType;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }

    public String getActionValue() {
        return actionValue;
    }

    public void setActionValue(String actionValue) {
        this.actionValue = actionValue;
    }
}
