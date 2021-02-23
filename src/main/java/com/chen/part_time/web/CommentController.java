package com.chen.part_time.web;

import com.chen.part_time.entity.Admin;
import com.chen.part_time.entity.Apply;
import com.chen.part_time.entity.Comment;
import com.chen.part_time.entity.User;
import com.chen.part_time.service.IApplyService;
import com.chen.part_time.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 评论的接口
 * @author 陈奕成
 * @create 2021 02 06 22:05
 */
@Controller
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @Autowired
    private IApplyService applyService;

    @GetMapping("/comments/{id}")
    public String getCommentByPartTimeId(@PathVariable("id") Long id, Model model) {
        Comment comment = new Comment();
        comment.setPartTimeId(id);
        List<Comment> allByComment = commentService.getAllByComment(comment);
        model.addAttribute("comments", allByComment);
        return "partTime :: commentList";
    }

    @PostMapping("/comments")
    @ResponseBody
    public String submitPost(Comment comment, HttpSession session) {
        Object user = session.getAttribute("user");
        if (user == null) {
            return "登录后才可以评论呀！！！";
        }
        if (user instanceof Admin) { // 是管理员
            return "管理员不能评论哦！！！";
        }
        User u = null;
        if (user instanceof User) {
            u = (User) user;
        }
        if (u.getType() == 1) { // 是商家
            return "商家不能评论哦！！！";
        }
        // 查询当前用户该兼职的兼职状态
        Apply apply = new Apply(comment.getPartTimeId(), u.getId());
        Apply oneByApply = applyService.getOneByApply(apply);
        if (apply == null) {
            return "只有开始兼职的人才可以评论哦！！！";
        }
        if (oneByApply.getChoose() < Apply.CHOOSE_STARTED) {
            // 说明不可以评论
            return "等开始兼职了就可以评论啦！！！";
        }
        comment.setUserId(u.getId());
        List<Comment> allByComment = commentService.getAllByComment(comment);
        if(allByComment != null && allByComment.size() >= 2){
            // 已评论过
            return "您已经评论过 2 次啦！！！";
        }
        try {
            commentService.addComment(comment);
        } catch (Exception e) {
            return "评论失败，请联系管理员!";
        }
        return "success";
    }
}
