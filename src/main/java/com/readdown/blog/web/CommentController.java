package com.readdown.blog.web;

import com.readdown.blog.po.Blog;
import com.readdown.blog.po.Comment;
import com.readdown.blog.po.User;
import com.readdown.blog.service.BlogService;
import com.readdown.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author feng
 * @Date 2017/11/5
 * @Time 21:19
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{blogId}")
    public String commentList(@PathVariable Long blogId, Model model){
        model.addAttribute("comments",commentService.listCommentByBlogId(blogId));
        return "blog :: commentList";
    }


    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
        Long blogId = comment.getBlog().getId();
        Blog blog = blogService.getBlog(blogId);
        comment.setBlog(blog);
        User user = (User) session.getAttribute("user");
        User u = (User) session.getAttribute("normalUser");
        if (u!=null ){
            comment.setAvatar(u.getAvatar());
            if ( u.getUsername().equals(blog.getUser().getUsername())){
                comment.setAdminComment(true);
            }
        }else {
            comment.setAvatar(avatar);
        }

        commentService.saveCommment(comment);
        return "redirect:/comments/"+blogId;
    }
}
