package com.xfh.bingo.config.security;

import com.alibaba.fastjson.JSON;
import com.xfh.bingo.model.JsonResult;
import com.xfh.bingo.service.RedisService;
import com.xfh.bingo.utils.AESUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description：验证header中的参数拦截器
 * @Author: xfh
 * @Date: 2019/2/12 18:00
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Component
@Slf4j
public class BbAuthenticationTokenFilter extends OncePerRequestFilter {

    @Value("${bamboo.aes.key}")
    private String decryptAesKey;

    @Value("${jwt.header}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Value("${token.expire}")
    private int tokenExpire;
    @Value("${unAuth.api.list}")
    private String unAuthAPIS;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private RedisService redisService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        log.info("im in BbAuthenticationTokenFilter");
        StringBuffer requestURL = request.getRequestURL();
        String serverPath = request.getServletPath();
        String authHeader = request.getHeader(this.tokenHeader);
        //登陆接口不传header走过滤器，需要鉴权的接口走下面逻辑
        if (StringUtils.isNotBlank(authHeader) && authHeader.startsWith(tokenHead)) {
            final String authToken = authHeader.substring(tokenHead.length()); // The part after "Bearer "
            String username = tokenUtil.getUsernameFromToken(authToken);
            logger.info("checking authentication " + username);
            //白名单
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                LoginModel user = getCurrentUser(authHeader);//从redis捞取token对应的user信息
                if (user == null) {
                    JsonResult jsonResult = JsonResult.error("token过期", 111L);
                    log.info("token过期, 需要重新登录, username:{}, token:{}", username, authToken);
                    new BbAuthenticationEntryPoint().genResponse(response, jsonResult);
                    return;
                }
                UserDetails userDetails = BbUserFactory.create(user);
                redisService.expire(authToken, tokenExpire,TimeUnit.MINUTES);//重置该token的有效时间

                boolean checkAuth = checkAuthentication(serverPath);
                if(! checkAuth){
                    //// TODO: 2019/2/13  url与role对应
                    checkAuth = true;
                }
                if (checkAuth) {
                    logger.info("此用户 : " + username + ", 有权限访问:" + requestURL);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                            request));
                    logger.info("authenticated user " + username + ", setting security context");
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    request.getSession().setAttribute("user", encryptUser(user));//aes加密传输
                }
            }
        }
        chain.doFilter(request, response);
    }

    private boolean checkAuthentication(String serverPath){
        //修改密码和登出接口，不需要权限验证
        String[] arr = unAuthAPIS.split(",");
        List<String> list = Arrays.asList(arr);
        if(list.contains(serverPath)){
            return true;
        }
        return false;
    }

    public LoginModel getCurrentUser(String header) {
        String token = header.substring(tokenHead.length(), header.length());
        log.debug("获取用户token, token:{}", token);
        String loginJson = redisService.get(token);
        if (StringUtils.isBlank(loginJson)) {
            log.info("token失效, 未获取到用户信息, 需要重新登录");
            return null;
        }
        LoginModel login = JSON.parseObject(loginJson, LoginModel.class);
        log.debug("解析成login对象, login:{}", login);
        return login;
    }

    public String encryptUser(LoginModel user){

        try {
            return AESUtils.encrypt(JSON.toJSONString(user), decryptAesKey);
        } catch (Exception e) {
            return null;
        }
    }
}
