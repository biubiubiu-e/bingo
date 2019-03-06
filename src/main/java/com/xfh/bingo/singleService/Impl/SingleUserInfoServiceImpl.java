package com.xfh.bingo.singleService.Impl;

import com.xfh.bingo.entity.security.UserInfo;
import com.xfh.bingo.repository.UserInfoRepository;
import com.xfh.bingo.singleService.SingleUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description：
 * @Author: xfh
 * @Date: 2019/2/12 16:26
 */

@Service
public class SingleUserInfoServiceImpl implements SingleUserInfoService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByUsername(String username){
        return userInfoRepository.findByUsername(username);
    }
}
