package com.readdown.blog.dao;

import com.readdown.blog.po.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author feng
 * @Date 2017/11/4
 * @Time 22:43
 */
public interface TagRepository extends JpaRepository<Tag,Long> {
    Tag findByName(String name);
}
