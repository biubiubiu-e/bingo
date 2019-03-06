package com.xfh.bingo.zdemo.TimeTask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description：spring Task 定时任务，在主类上加@EnableScheduling注解开启对定时任务的支持，启动项目定时任务即开始，单线程
 * @Author: xfh
 * @Date: 2018/12/13 18:02
 */

@Slf4j
@Component
public class ScheduledService {
    /*@Scheduled(cron = "0/5 * * * * *")
    public void scheduled(){
        log.info("=====>>>>>使用cron  {}",System.currentTimeMillis());
    }
    @Scheduled(fixedRate = 5000)
    public void scheduled1() {
        log.info("=====>>>>>使用fixedRate{}", System.currentTimeMillis());
    }
    @Scheduled(fixedDelay = 5000)
    public void scheduled2() {
        log.info("=====>>>>>fixedDelay{}",System.currentTimeMillis());
    }*/
}
