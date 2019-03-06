package com.xfh.bingo.config.security;

import lombok.Data;

import java.util.List;

/**
 * @Description：
 * @Author: xfh
 * @Date: 2019/2/12 16:24
 */
@Data
public class LoginModel {

    private String username;
    private String password;

    private List<Long> roleIdList;
}
