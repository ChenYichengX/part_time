package com.chen.part_time.entity;

/**
 * 申述实体类
 * @author 陈奕成
 * @create 2021 03 30 22:53
 */
public class Complaint {

    private Long id; // id
    private Long partTimeId; // 兼职信息 id
    private String content; // 申述内容
    private Long userId; // 学生 id
    private String username; // 学生昵称

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "id=" + id +
                ", partTimeId=" + partTimeId +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }
}
