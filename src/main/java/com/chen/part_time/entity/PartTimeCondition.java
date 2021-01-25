package com.chen.part_time.entity;

/**
 * 兼职信息的条件
 * @author 陈奕成
 * @create 2020 12 02 15:07
 */
public class PartTimeCondition {

    private Long user_id; // 用户 id
    private Long type_id; // 类型 id
    private String title; // 标题
    private String user_name; // 用户名
    private String type_name; // 类型名
    private String content; // 内容
    private String require_text; // 要求

    public String getRequire_text() {
        return require_text;
    }

    public void setRequire_text(String require_text) {
        this.require_text = require_text;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PartTimeCondition() {
    }

    public PartTimeCondition(Long user_id, Long type_id, String title, String user_name) {
        this.user_id = user_id;
        this.type_id = type_id;
        this.title = title;
        this.user_name = user_name;
    }

    public PartTimeCondition(Long type_id, String title) {
        this.type_id = type_id;
        this.title = title;
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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
