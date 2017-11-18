package com.readdown.blog.oauth.config;

import com.readdown.blog.oauth.api.GithubApi;
import com.readdown.blog.oauth.service.GithubOAuthService;
import com.readdown.blog.oauth.service.OAuthServiceDeractor;
import org.scribe.builder.ServiceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author feng
 * @Date 2017/11/18
 * @Time 18:49
 */
@Configuration
public class OAuthConfig {
    private static final String CALLBACK_URL = "%s/oauth/%s/callback";

    private String state = "bf97751ad76d4f228b99bb2d33da55e2";
    private String githubClientId = "ab7927c9083f700af80d";
    private String githubClientSecret = "f4201ea3907e61f90588bb5b6c1f39e8729f8ee0";

    String host = "http://120.78.198.235/";


    @Bean
    public GithubApi githubApi(){
        return new GithubApi(state);
    }

    @Bean
    public OAuthServiceDeractor getGithubOAuthService(){
        return new GithubOAuthService(new ServiceBuilder()
                .provider(githubApi())
                .apiKey(githubClientId)
                .apiSecret(githubClientSecret)
                .callback(String.format(CALLBACK_URL, host, OAuthTypes.GITHUB))
                .build());
    }


}
