package com.chen.part_time.vo;

/**
 * @author 陈奕成
 * @create 2020 12 04 11:21
 */
public class TypeVo {
    private Long id; // 主键 id
    private String name; // 类型名称
    private Integer size; // 个数

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

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
