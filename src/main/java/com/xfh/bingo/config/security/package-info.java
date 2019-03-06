/**
 * @Description：权限系统设计思路
 * @Author: xfh
 * @Date: 2019/2/13 17:32
 */
package com.xfh.bingo.config.security;

/*
* 配置类WebSecurityConfig配值拦截器顺序，定向异常跳转到BbAuthenticationEntryPoint，premitt登陆等接口
* 第一层拦截器CrosFilter 用于过滤请求方式（如delete不被允许），和请求头Origin, No-Cache, x-auth-token, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type,Authorization等
* 自定义拦截器BbAuthenticationTokenFilter用于封装SecurityContextHolder信息
* BbAuthenticationEntryPoint处理异常跳转
*
*
* */