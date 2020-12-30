package com.chen.part_time.entity;

/**
 * 类型
 * @author 陈奕成
 * @create 2020 11 18 17:26
 */
public class Type {
    private Long id; // 主键 id
    private String name; // 类型名称

    public Type() {
    }

    public Type(Long id, String name) {
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

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
