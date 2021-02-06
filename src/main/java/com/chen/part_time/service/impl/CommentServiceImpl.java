package com.chen.part_time.service.impl;

import com.chen.part_time.dao.ICommentDao;
import com.chen.part_time.entity.Comment;
import com.chen.part_time.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 陈奕成
 * @create 2021 02 06 22:10
 */
@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private ICommentDao commentDao;

    @Override
    public boolean addComment(Comment comment) {
        return commentDao.addComment(comment);
    }

    @Override
    public List<Comment> getAllByComment(Comment comment) {
        return commentDao.getAllByComment(comment);
    }
}
