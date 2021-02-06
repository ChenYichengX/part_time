package com.chen.part_time.entity;

/**
 * @author 陈奕成
 * @create 2020 11 19 15:20
 */
public class Admin {
    private Long id;
    private String username;
    private String password;
    private final Integer type = 1;

    public Admin() {
    }

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getType() {
        return type;
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
}
