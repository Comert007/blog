package com.readdown.blog.dao;

import com.readdown.blog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author feng
 * @Date 2017/11/4
 * @Time 19:26
 */
public interface UserRepository extends JpaRepository<User,Long>{
    User findByUsernameAndPassword(String username,String password);
}
