package com.readdown.blog.po;


import javax.persistence.*;


@Entity
@Table(name = "t_oauth_user")
public class OAuthUser {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private User user;

    private String oAuthType;
    private String oAuthid;


    public OAuthUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getoAuthType() {
        return oAuthType;
    }

    public void setoAuthType(String oAuthType) {
        this.oAuthType = oAuthType;
    }

    public String getoAuthid() {
        return oAuthid;
    }

    public void setoAuthid(String oAuthid) {
        this.oAuthid = oAuthid;
    }
}
