package com.readdown.blog.oauth.service;

import com.readdown.blog.dao.OAuthUserRepository;
import com.readdown.blog.po.OAuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author feng
 * @Date 2017/11/18
 * @Time 19:26
 */
@Service
public class OAuthUserServiceImpl implements OAuthUserService {

    @Autowired
    OAuthUserRepository oAuthUserRepository;

    @Override
    public OAuthUser getOAuthUser(String oAuthType, String oAuthId) {
        return oAuthUserRepository.findByOAuthTypeAndOAuthid(oAuthType, oAuthId);
    }
}
