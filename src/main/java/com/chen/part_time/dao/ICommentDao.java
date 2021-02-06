package com.chen.part_time.dao;

import com.chen.part_time.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Create by ChenYicheng
 * 2021/2/6 17:25
 */
@Mapper
public interface ICommentDao {

    // 添加评论
    boolean addComment(Comment comment);

    // 查询评论
    List<Comment> getAllByComment(Comment comment);
}
