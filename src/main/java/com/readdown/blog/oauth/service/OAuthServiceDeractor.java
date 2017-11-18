package com.readdown.blog.oauth.service;

import com.readdown.blog.po.OAuthUser;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

/**
 * @author feng
 * @Date 2017/11/18
 * @Time 18:39
 */
public abstract class OAuthServiceDeractor implements OAuthService {

    private final OAuthService oAuthService;
    private final String oAuthType;
    private final String authorizationUrl;


    public OAuthServiceDeractor(OAuthService oAuthService, String type) {
        super();
        this.oAuthService = oAuthService;
        this.oAuthType = type;
        this.authorizationUrl = oAuthService.getAuthorizationUrl(null);
    }


    @Override
    public Token getRequestToken() {
        return oAuthService.getRequestToken();
    }

    @Override
    public Token getAccessToken(Token token, Verifier verifier) {
        return oAuthService.getAccessToken(token, verifier);
    }

    @Override
    public void signRequest(Token token, OAuthRequest oAuthRequest) {
        oAuthService.signRequest(token, oAuthRequest);
    }

    @Override
    public String getVersion() {
        return oAuthService.getVersion();
    }

    @Override
    public String getAuthorizationUrl(Token token) {
        return oAuthService.getAuthorizationUrl(token);
    }

    public String getoAuthType() {
        return oAuthType;
    }

    public String getAuthorizationUrl(){
        return authorizationUrl;
    }

    public abstract OAuthUser getOAuthUser(Token accessToken);
}
