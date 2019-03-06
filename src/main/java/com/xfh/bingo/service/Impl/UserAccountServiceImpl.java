package com.xfh.bingo.service.Impl;

import com.alibaba.fastjson.JSON;
import com.xfh.bingo.entity.security.AccountInfo;
import com.xfh.bingo.entity.security.UserInfo;
import com.xfh.bingo.model.JsonResult;
import com.xfh.bingo.model.UserModel;
import com.xfh.bingo.repository.AccountInfoRepository;
import com.xfh.bingo.repository.UserInfoRepository;
import com.xfh.bingo.service.UserAccountService;
import com.xfh.bingo.utils.VaildatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description：
 * @Author: xfh
 * @Date: 2019/2/13 11:02
 */

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    VaildatorUtils vaildatorUtils;

    @Autowired
    AccountInfoRepository accountInfoRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    public JsonResult add(UserModel userModel){
        vaildatorUtils.validate(userModel);
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setAccountName(userModel.getAccountname());
        accountInfo.setPassword(userModel.getPassword());
        accountInfoRepository.save(accountInfo);
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(userModel.getUsername());
        userInfo.setAccountId(accountInfoRepository.findByAccountName(userModel.getAccountname()).getId());
        userInfoRepository.save(userInfo);
        return JsonResult.Success("添加成功",0000L,JSON.toJSONString(userModel));
    }
}
