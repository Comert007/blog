package com.readdown.blog.service;

import com.readdown.blog.dao.UserRepository;
import com.readdown.blog.po.User;
import com.readdown.blog.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author feng
 * @Date 2017/11/4
 * @Time 19:25
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Util.code(password));
        return user;
    }
}
