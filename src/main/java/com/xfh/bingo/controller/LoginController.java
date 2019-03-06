package com.xfh.bingo.controller;

import com.alibaba.fastjson.JSON;
import com.xfh.bingo.config.security.BbUser;
import com.xfh.bingo.config.security.LoginModel;
import com.xfh.bingo.config.security.TokenUtil;
import com.xfh.bingo.model.JsonResult;
import com.xfh.bingo.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description：登录接口
 * @Author: xfh
 * @Date: 2018/12/27 14:29
 */

@RestController
@RequestMapping
@Slf4j
public class LoginController {

    @Value("${token.expire}")
    private int tokenExpire;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private RedisService redisService;

    /*登录接口，实现用户认证，生成登陆token，设置token过期时间*/
    @RequestMapping("/login")
    public JsonResult login(@RequestBody LoginModel loginModel){
        UserDetails userDetails = null;
        try {
            UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(
                    loginModel.getUsername(), loginModel.getPassword());
            // Perform the security
            Authentication authentication = authenticationManager.authenticate(upToken);//??神马操作
            SecurityContextHolder.getContext().setAuthentication(authentication);
            Object principal =  authentication.getPrincipal();
            userDetails = (UserDetails)principal;
        }catch (Exception e){
            return JsonResult.error("用户名或密码错误",1L,null);
        }
        // Reload password post-security so we can generate token
        BbUser ncfUser = (BbUser) userDetails;
        String token = tokenUtil.generateToken(userDetails);
        log.info("获取到token存入redis, token:{}", token);
        redisService.set(token, JSON.toJSONString(ncfUser));
        String userLoginKey = ncfUser.getUsername();
        redisService.set(userLoginKey, token);
        redisService.expire(token, tokenExpire, TimeUnit.MINUTES);
        redisService.expire(userLoginKey, tokenExpire, TimeUnit.MINUTES);
        Map<String, String> map = new HashMap<>();
        map.put("token",token);
        map.put("timestamp", new Timestamp(System.currentTimeMillis()).toString());
        return JsonResult.Success("登录成功",0000L,map);
    }

}
