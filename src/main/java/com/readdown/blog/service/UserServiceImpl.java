package com.readdown.blog.service;

import com.readdown.blog.dao.UserRepository;
import com.readdown.blog.po.User;
import com.readdown.blog.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * @author feng
 * @Date 2017/11/4
 * @Time 19:25
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    private String DEFAULT_AVATAR ="https://picsum.photos/100/100?image=0";

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Util.code(password));
        return user;
    }

    /**
     * 检测用户是否存在
     * @param username
     * @return
     */
    @Override
    public boolean isExistUser(String username){
        if (username==null || username.isEmpty()){
            return false;
        }
        User user = userRepository.findByUsername(username);
        if (user!=null){
            return true;
        }else {
            return false;
        }
    }

    @Transactional
    public User saveUser(User user){
        if (user!=null){
            user.setPassword(MD5Util.code(user.getPassword()));
            user.setAvatar(DEFAULT_AVATAR);
            user.setType(3);
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
          User u=  userRepository.save(user);
          return u;
        }
        return null;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//       User user= userRepository.findByUsername(username);
//        return user;
//    }
}
