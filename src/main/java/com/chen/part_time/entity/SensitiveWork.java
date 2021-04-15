package com.chen.part_time.entity;

/**
 * @ClassName SensitiveWork
 * @Author ChenYicheng
 * @Description 敏感词
 * @Date 2021/4/15 15:26
 */
public class SensitiveWork {

    private Long id;

    private String work; // 敏感词

    private int level; // 等级：默认1级

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
