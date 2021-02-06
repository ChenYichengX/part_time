package com.chen.part_time.service;

import com.chen.part_time.entity.Comment;

import java.util.List;

/**
 * @author 陈奕成
 * @create 2021 02 06 22:10
 */
public interface ICommentService {

    // 添加评论
    boolean addComment(Comment comment);

    // 查询评论
    List<Comment> getAllByComment(Comment comment);
}
