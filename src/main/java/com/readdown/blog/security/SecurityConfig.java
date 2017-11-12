//package com.readdown.blog.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * @author feng
// * @Date 2017/11/12
// * @Time 23:34
// */
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.
//                authorizeRequests()
//                .antMatchers("/", "/blogs/{id}", "/types/{id}", "/tags/{id}", "/search", "/footer/newblog",
//                        "/comments/{blogId}","/comments","/archives","/about","/login","/register","/users/register",
//                        "/users/registerSuccess","/logout")
//                .permitAll()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/admin/login")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/admin/logout")
//                .logoutSuccessUrl("/admin")
//                .invalidateHttpSession(true)
//                .permitAll();
//
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER");
//    }
//
//}
