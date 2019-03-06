package com.xfh.bingo.config.InterfaceResponseTime;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.MDC;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @Description：自定义拦截器 功能：计算接口响应时间
 * @Author: xfh
 * @Date: 2019/1/31 17:52
 */
@Slf4j
public class ResponseMetricsInterceptor extends HandlerInterceptorAdapter {
    private static final String TRACING_ID = "TRACING_ID";
    private static final String RESPONSE_TIME = "RESPONSE_TIME";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //MDC把随机生成的tracingId变量 打入logback作为logId,两次MDC.put 之间的logid相同
        String tracingId = RandomStringUtils.randomAlphanumeric(10);
        MDC.put(TRACING_ID, tracingId);
        log.info("service.tracing.start.请求开始 url={}", request.getServletPath());
        request.setAttribute(RESPONSE_TIME, System.currentTimeMillis());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        System.out.println("11");
        try {
            log.info("service.tracing.end.请求结束 url={}, 消耗时间：{}ms", request.getServletPath(),
                    System.currentTimeMillis() - Long.valueOf(
                            Objects.toString(request.getAttribute(RESPONSE_TIME), "0")));
        } catch (NumberFormatException e) {
        } finally {
            MDC.remove(TRACING_ID);
        }
        super.afterCompletion(request, response, handler, ex);
    }
}
