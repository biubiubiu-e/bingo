package com.xfh.bingo.config.security;

import com.xfh.bingo.model.UserModel;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description：??
 * @Author: xfh
 * @Date: 2018/12/27 13:59
 */

public class BbUserFactory {

    private BbUserFactory() {
    }

    public static BbUser create(LoginModel loginModel) {

        return new BbUser(
                loginModel.getUsername(),
                loginModel.getPassword(),
                loginModel.getRoleIdList(),
                Collections.EMPTY_LIST
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        if (CollectionUtils.isEmpty(authorities)) {
            return null;
        }
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
