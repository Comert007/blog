package com.readdown.blog.dao;

import com.readdown.blog.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author feng
 * @Date 2017/11/4
 * @Time 20:53
 */
public interface TypeRepository extends JpaRepository<Type,Long> {
    Type findByName(String name);
}
