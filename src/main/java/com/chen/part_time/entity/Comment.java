package com.chen.part_time.entity;

/**
 * 评论（开始兼职的方可评论）
 * Create by ChenYicheng
 * 2021/2/6 15:54
 */
public class Comment {
    private Long id;
    private Long partTimeId;
    private Long userId;
    private String username; // 学生昵称
    private String content; // 内容

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPartTimeId() {
        return partTimeId;
    }

    public void setPartTimeId(Long partTimeId) {
        this.partTimeId = partTimeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
