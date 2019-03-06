package com.xfh.bingo.config.security;

import com.alibaba.fastjson.JSON;
import com.xfh.bingo.enums.BambooCode;
import com.xfh.bingo.exception.BambooException;
import com.xfh.bingo.model.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

/*
 *
 * @Description：401返回值，在用户没有登录时用于引导用户进行登录认证
 * @Author: xfh
 * @Date: 2018/12/27 14:00
 */

@Component
public class BbAuthenticationEntryPoint implements AuthenticationEntryPoint,Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(BbAuthenticationEntryPoint.class);
    private static final long serialVersionUID = -8970718410437077606L;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        try {
            JsonResult jsonResult = JsonResult.error(BambooCode.AUTH_INVALID.getMsg(),BambooCode.AUTH_INVALID.getCode());
            String header = request.getHeader(tokenHeader);
            if (StringUtils.isBlank(header) || StringUtils.isBlank(tokenUtil.getUsernameFromToken(header.substring(tokenHead.length())))) {
                LOGGER.info("token不合法, header:{}", header);
                jsonResult = JsonResult.error(BambooCode.TOKEN_INVALID.getMsg(),BambooCode.TOKEN_INVALID.getCode());
            }
            genResponse(response, jsonResult);
        } catch (Exception e) {
            LOGGER.error("返回权限信息出错, 出错信息:{}", BambooException.getStackTrace(e));
        }
    }

    public void genResponse(HttpServletResponse response, JsonResult jsonResult) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            String json = JSON.toJSONString(jsonResult);
            LOGGER.info("返回json:{}", json);
            out.append(json);
        } catch (Exception e) {
            LOGGER.error("生成response出现错误, 出错信息:{}", BambooException.getStackTrace(e));
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
