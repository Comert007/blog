package com.readdown.blog.dao;

import com.readdown.blog.po.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author feng
 * @Date 2017/11/4
 * @Time 23:43
 */
public interface BlogRepository extends JpaRepository<Blog,Long>, JpaSpecificationExecutor<Blog>{
}
