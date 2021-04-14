package com.chen.part_time.vo;

/**
 * 申述信息的 Vo
 * @author 陈奕成
 * @create 2021 04 11 16:44
 */
public class ComplaintVo {

    private Long id; // 申述信息 id
    private String merchantName; // 商家 username
    private String title; // 兼职标题
    private String studentName; // 学生 username
    private String content; // 申述内容
    private int flag; // 默认0：未处理；1：已处理

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
