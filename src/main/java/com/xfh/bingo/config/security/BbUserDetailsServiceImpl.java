package com.xfh.bingo.config.security;


import com.xfh.bingo.entity.security.AccountInfo;
import com.xfh.bingo.entity.security.RoleUser;
import com.xfh.bingo.entity.security.UserInfo;
import com.xfh.bingo.repository.AccountInfoRepository;
import com.xfh.bingo.repository.RoleUserRepository;
import com.xfh.bingo.repository.UserInfoRepository;
import com.xfh.bingo.singleService.SingleUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Description：
 * @Author: xfh
 * @Date: 2019/2/12 16:17
 */
@Service("BbUserDetailsService")
@Slf4j
public class BbUserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    AccountInfoRepository accountInfoRepository;

    @Autowired
    RoleUserRepository roleUserRepository;

    @Autowired
    SingleUserInfoService singleUserInfoService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AccountInfo accountInfo = accountInfoRepository.findByAccountName(username);
        if (null == accountInfo){
            throw new UsernameNotFoundException("帐户不存在");
        }

        UserInfo userInfo = userInfoRepository.findByUsername(username);
        if (null == userInfo){
            throw new UsernameNotFoundException("用户名不存在");
        }
        log.info("查询到的帐户信息={}，用户信息={}",accountInfo,userInfo);
        LoginModel loginModel = new LoginModel();
        loginModel.setUsername(username);
        loginModel.setPassword(accountInfo.getPassword());

        List<RoleUser> roleUserList = roleUserRepository.findByUserId(userInfo.getId());
        if (CollectionUtils.isNotEmpty(roleUserList)){
            List<Long> roleIdList = roleUserList.stream().map(RoleUser::getRoleId).collect(Collectors.toList());
            loginModel.setRoleIdList(roleIdList);
        } else {
            throw new UsernameNotFoundException("该用户没有任何有效的权限岗位");
        }

        BbUser bbUser = BbUserFactory.create(loginModel);
        return bbUser;
    }
}
