package com.readdown.blog.oauth.api;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.model.OAuthConfig;
import org.scribe.utils.OAuthEncoder;

/**
 * @author feng
 * @Date 2017/11/18
 * @Time 18:27
 */
public class GithubApi extends DefaultApi20 {

    private static final String AUTHORIZE_URL="https://github.com/login/oauth/authorize?client_id=%s&redirect_uri=%s&state=%s";
    private static final String SCOPED_AUTHORIZE_URL=AUTHORIZE_URL +"&scope=%s";
    private static final String ACCESS_TOKEN_URL="https://github.com/login/oauth/access_token?state=%s";

    private final String githubState;

    public GithubApi(String state){
        this.githubState = state;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return  String.format(ACCESS_TOKEN_URL, githubState);
    }

    @Override
    public String getAuthorizationUrl(OAuthConfig oAuthConfig) {

        if (oAuthConfig.hasScope()){
            return String.format(SCOPED_AUTHORIZE_URL,oAuthConfig.getApiKey(), OAuthEncoder.encode(oAuthConfig.getCallback()),
                   githubState,OAuthEncoder.encode(oAuthConfig.getScope()));
        }else {
            return String.format(AUTHORIZE_URL, oAuthConfig.getApiKey(), OAuthEncoder.encode(oAuthConfig.getCallback()), githubState);
        }
    }
}
