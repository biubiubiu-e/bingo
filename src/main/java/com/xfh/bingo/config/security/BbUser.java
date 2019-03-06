package com.xfh.bingo.config.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.EAN;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


/**
 * @Description：
 * @Author: xfh
 * @Date: 2018/12/27 13:58
 */

public class BbUser implements UserDetails {

    private  String username;
    private  String password;
    private List<Long> roleIdList;

    private Collection<? extends GrantedAuthority> authorities;

    public BbUser(
            String username,
            String password,
            List<Long> roleIdList,
            Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.roleIdList = roleIdList;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }


}
