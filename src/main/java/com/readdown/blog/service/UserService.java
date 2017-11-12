package com.readdown.blog.service;

import com.readdown.blog.po.User;

/**
 * @author feng
 * @Date 2017/11/4
 * @Time 19:24
 */
public interface UserService {
    User checkUser(String name,String password);
    boolean isExistUser(String username);

    User saveUser(User user);
}
