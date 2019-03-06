package com.xfh.bingo.zdemo.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description：
 * @Author: xfh
 * @Date: 2019/2/1 14:23
 */

@RestController
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @PostMapping("/async")
    public void async() {
        System.out.println("a");
        asyncService.testAsync();
        System.out.println("b");
    }


}
