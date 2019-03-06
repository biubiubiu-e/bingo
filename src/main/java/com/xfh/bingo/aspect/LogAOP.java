package com.xfh.bingo.aspect;


import com.alibaba.fastjson.JSONObject;
import com.xfh.bingo.Constants;
import com.xfh.bingo.utils.ThreadLocalMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


/**
 * @Description：自定义日志切面 功能：1.出参入参打印到log 2.接口调用时间计算  todo3.接口调用时间监控
 * @Author: xfh
 * @Date: 2018/12/13 13:51
 */

@Aspect //  声明切面
@Component //声明组件
@ComponentScan //组件自动扫描
@EnableAspectJAutoProxy //spring自动切换JDK动态代理和CGLIB
@Slf4j
public class LogAOP {
    /**
     *自定义日志
     */
    private org.slf4j.Logger logger = LoggerFactory.getLogger("==========="+LogAOP.class+"=============");

    private static final String TRACING_ID = "TRACING_ID";

    //切入点
    @Pointcut("execution (public * com.xfh.bingo.controller..* (..))")
    //@Pointcut("execution (public * com.xfh.bingo.controller.SMSController.sendMessage())")
    public void methodPointCut() {
    }

    @Around("methodPointCut()")
    public Object doBeforeAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

        //获取目标方法的传入参数信息
        Object[] obj = joinPoint.getArgs();

        //获取连接点的方法签名对象
        Signature signature = joinPoint.getSignature();

        //代理的是哪一个方法(modify)
        String methodNmae = signature.getName();

        //AOP代理类的名字(com.xfh.bingo.controller.HeroInfoController)
        String className = signature.getDeclaringTypeName();

        /*String tracingId = RandomStringUtils.randomAlphanumeric(10);
        MDC.put(TRACING_ID, tracingId);*/

        //打印入参
        logger.info(className + "." + methodNmae + ", request: " + JSONObject.toJSONString(obj));

        long beginTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        //打印出参
        logger.info(className + "." + methodNmae + ", response: " + JSONObject.toJSONString(result));
        //接口耗时日志层级分类
        long elapsedTime = endTime - beginTime;
        if (elapsedTime < 500) {
            logger.info(className + "." + methodNmae + ", elapsed: " + elapsedTime + "ms.");
        } else if (elapsedTime < 2000) {
            //接口超时报warn
            logger.warn(className + "." + methodNmae + ", elapsed: " + elapsedTime + "ms.");
        } else {
            logger.warn(className + "." + methodNmae + ", elapsed: " + elapsedTime + "ms.");
            // TODO: 2019/1/31 接口耗时过长发送告警

        }
        return result;
    }

    @Pointcut("execution(public * com.xfh.bingo.utils.GlobalExceptionHandler.* (..))")
    public void afterPointCut() {
    }

    @AfterReturning(pointcut = "afterPointCut()", returning = "result")
    public void exceptionAdvice(Object result) {
        Long end = System.currentTimeMillis();
        Long start = (Long) ThreadLocalMap.get("start");
        Map parameterMap = (Map) ThreadLocalMap.get("parameterMap");
        String url = (String) ThreadLocalMap.get("url");
        List<String> urlLogFilter = Constants.URL_LOG_FILTER;
        boolean flag = true;
        for (String str : urlLogFilter) {
            if (url.contains(str)) {
                flag = false;
            }
        }
        if (flag) {
            log.info("request completed. url:{}, params:{}, cost:{}ms, result:{}", url, JSONObject.toJSONString(parameterMap), end - start, JSONObject.toJSONString(result));
        }
        ThreadLocalMap.remove();
    }


}
