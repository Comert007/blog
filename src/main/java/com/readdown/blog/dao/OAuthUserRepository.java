package com.readdown.blog.dao;

import com.readdown.blog.po.OAuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author feng
 * @Date 2017/11/18
 * @Time 18:54
 */
public interface OAuthUserRepository extends JpaRepository<OAuthUser,Long> {

    OAuthUser findByOAuthTypeAndOAuthid(String oAuthType, String oAuthId);
}
