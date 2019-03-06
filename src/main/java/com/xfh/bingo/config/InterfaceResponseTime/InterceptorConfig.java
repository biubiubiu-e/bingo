package com.xfh.bingo.config.InterfaceResponseTime;


import com.xfh.bingo.utils.ApplicationContextHolder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by wx on 2018/6/7.
 */
@Lazy
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    ResponseMetricsInterceptor metricsInterceptor = new ResponseMetricsInterceptor();
    MyInterceptor myInterceptor = new MyInterceptor();
    ApplicationContextHolder.getApplicationContext()
        .getAutowireCapableBeanFactory();

    registry.addInterceptor(metricsInterceptor).addPathPatterns("/**");
    registry.addInterceptor(myInterceptor).addPathPatterns("/add");
  }
}
