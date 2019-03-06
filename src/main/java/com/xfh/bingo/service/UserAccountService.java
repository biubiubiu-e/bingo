package com.xfh.bingo.service;

import com.xfh.bingo.model.JsonResult;
import com.xfh.bingo.model.UserModel;

/**
 * @Description：
 * @Author: xfh
 * @Date: 2019/2/13 11:03
 */
public interface UserAccountService {
    JsonResult add(UserModel userModel);
}
