package com.xfh.bingo.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 *第一层过滤：过滤什么请求可以处理，什么请求不予处理
 */

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@Slf4j
public class CrosFilter implements Filter {

    @Value("${bamboo.filter.allowedOrigin}")
    private String allowedOrigin;

    @Value("${bamboo.filter.allowedHeaders}")
    private String allowedHeaders;

    private static final Set<String> DISALLOWED_METHOD = new HashSet<>();

    static {
        DISALLOWED_METHOD.add("OPTIONS");
        DISALLOWED_METHOD.add("PUT");
        DISALLOWED_METHOD.add("DELETE");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        response.setHeader("Access-Control-Allow-Origin", allowedOrigin);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET");
        response.setHeader("Access-Control-Allow-Headers", allowedHeaders);
        response.setHeader("Access-Control-Max-Age", "3600");
        log.info("进入CrosFilter拦截器");
        if (DISALLOWED_METHOD.contains(request.getMethod())) {
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
