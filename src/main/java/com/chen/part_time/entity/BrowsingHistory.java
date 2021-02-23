package com.chen.part_time.entity;

import java.util.Date;

/**
 * Create by ChenYicheng
 * 2021/2/23 11:06
 */
public class BrowsingHistory {
    private Long id;
    private String ip;
    private String url;
    private String username; // 访问者
    private String classMethod; // 方法名
    private Date browsing_time; // 浏览时间

    public BrowsingHistory(String ip, String url, String username, String classMethod, Date browsing_time) {
        this.ip = ip;
        this.url = url;
        this.username = username;
        this.classMethod = classMethod;
        this.browsing_time = browsing_time;
    }

    public BrowsingHistory(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClassMethod() {
        return classMethod;
    }

    public void setClassMethod(String classMethod) {
        this.classMethod = classMethod;
    }

    public Date getBrowsing_time() {
        return browsing_time;
    }

    public void setBrowsing_time(Date browsing_time) {
        this.browsing_time = browsing_time;
    }
}
