package com.xfh.bingo.zdemo.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description：
 * @Author: xfh
 * @Date: 2019/2/1 14:32
 */

@Service
@Slf4j
public class Async implements AsyncService{

    @Override
    @org.springframework.scheduling.annotation.Async
    public void testAsync() {
        System.out.println("1");
        try{
            Thread.sleep(1000L);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("2");
    }
}
