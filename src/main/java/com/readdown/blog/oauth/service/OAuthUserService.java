package com.readdown.blog.oauth.service;

import com.readdown.blog.po.OAuthUser;

/**
 * @author feng
 * @Date 2017/11/18
 * @Time 19:19
 */
public interface OAuthUserService {

    OAuthUser getOAuthUser(String oAuthType, String oAuthId);
}
