package com.xfh.bingo.singleService;

import com.xfh.bingo.entity.security.UserInfo;

/**
 * @Description：
 * @Author: xfh
 * @Date: 2019/2/12 16:25
 */
public interface SingleUserInfoService {
    UserInfo findByUsername(String username);
}
