package com.chen.part_time.entity;

/**
 * 支付方式
 * @author 陈奕成
 * @create 2020 11 18 17:27
 */
public class PaymentType {
    private Long id; // 主键 id
    private String name; // 支付方式名称

    public PaymentType() {
    }

    public PaymentType(Long id, String name) {
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
        return "PaymentType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
