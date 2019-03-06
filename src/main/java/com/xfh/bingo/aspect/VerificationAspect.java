package com.xfh.bingo.aspect;

import com.xfh.bingo.annotation.VerificationAnnotation;
import com.xfh.bingo.enums.BambooCode;
import com.xfh.bingo.exception.BambooException;
import com.xfh.bingo.model.BaseInfoModel;
import com.xfh.bingo.model.heroInfo.HeroBaseInfoModel;
import com.xfh.bingo.utils.BeanUtil;
import com.xfh.bingo.utils.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

/**
 * @Description：参数校验注解
 * @Author: xfh
 * @Date: 2018/12/25 10:40
 */
@Aspect //  声明切面
@Component //声明组件
@ComponentScan //组件自动扫描
@Slf4j
//@EnableAspectJAutoProxy //spring自动切换JDK动态代理和CGLIB
public class VerificationAspect {

    @Value("${SALT}")
    private String SALT;

    @Pointcut("@annotation(com.xfh.bingo.annotation.VerificationAnnotation) ")
    public void methodPointCut() {
    }

    @Around(value = "methodPointCut()")
    public Object aroundDealWith(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Object[] args = proceedingJoinPoint.getArgs();
        if(ObjectUtils.isEmpty(args)){
            log.error(BambooCode.PARAMETER_IS_INCORRECT.getMsg());
            throw new BambooException(BambooCode.PARAMETER_IS_INCORRECT.getMsg(),BambooCode.PARAMETER_IS_INCORRECT.getCode());
        }
      /*  String targetName = proceedingJoinPoint.getTarget().getClass().getName();//切点所在的类路径
        String methodName = proceedingJoinPoint.getSignature().getName();//切点作用的方法名
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();//类下的方法集合
        //获取注解内传的值（本注解不传默认为""）
        String url = null;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == args.length) {
                    url = method.getAnnotation(VerificationAnnotation.class).url();
                    break;
                }
            }
        }*/
        HeroBaseInfoModel heroBaseInfoModel = (HeroBaseInfoModel) args[0];
        /*if(!heroBaseInfoModel.getSign().equals(SignUtil.createSign(heroBaseInfoModel))){//sign校验
            log.error("sign校验不通过,his sign={},my sign={}",heroBaseInfoModel.getSign(),SignUtil.createSign(heroBaseInfoModel));
            return new BambooException(BambooCode.NOT_PERMISSIONS.getMsg(),BambooCode.NOT_PERMISSIONS.getCode());
        }*/
        Object proceed = proceedingJoinPoint.proceed();
        return proceed;
    }
}
