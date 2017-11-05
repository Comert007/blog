package com.readdown.blog.service;

import com.readdown.blog.po.Comment;

import java.util.List;

/**
 * @author feng
 * @Date 2017/11/5
 * @Time 21:24
 */
public interface CommentService {

    List<Comment>  listCommentByBlogId(Long blogId);
    Comment saveCommment(Comment comment);
}
