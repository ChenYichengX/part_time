package com.chen.part_time.entity;

/**
 * 价格单位
 * @author 陈奕成
 * @create 2020 11 21 17:29
 */
public class Unit {
    private Long id; // 主键 id
    private String name; // 单位名称

    public Unit() {
    }

    public Unit(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
