package com.readdown.blog.dao;

import com.readdown.blog.po.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author feng
 * @Date 2017/11/5
 * @Time 21:26
 */
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByBlogIdAndParentCommentNull(Long id, Sort sort);
}
