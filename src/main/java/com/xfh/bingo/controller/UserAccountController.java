package com.xfh.bingo.controller;

import com.xfh.bingo.model.JsonResult;
import com.xfh.bingo.model.UserModel;
import com.xfh.bingo.service.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description：
 * @Author: xfh
 * @Date: 2019/2/12 17:46
 */

@RestController
@Slf4j
@RequestMapping("/user")
public class UserAccountController {

    @Autowired
    UserAccountService accountService;

    @RequestMapping("/add")
    public JsonResult add(UserModel userModel){
        return accountService.add(userModel);
    }
}
