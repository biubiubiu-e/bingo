package com.xfh.bingo.controller;

import com.xfh.bingo.annotation.VerificationAnnotation;
import com.xfh.bingo.model.heroInfo.HeroBaseInfoModel;
import com.xfh.bingo.model.JsonResult;
import com.xfh.bingo.model.heroInfo.HeroBaseInfoModifyModel;
import com.xfh.bingo.service.HeroBaseInfoService;
import com.xfh.bingo.singleService.SingleHeroInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author: xfh
 * @Date: 2018/11/21 14:42
 * @Version
 */

@RestController
@Slf4j
public class HeroInfoController {
    @Autowired
    private HeroBaseInfoService heroBaseInfoService;

    @Autowired
    private SingleHeroInfoService singleHeroInfoService;

    @PostMapping("/addHeroMessage")
    //@VerificationAnnotation(url = "/a")（该注解用于MD5串加密校验）
    public JsonResult add(HeroBaseInfoModel heroBaseInfoModel){
        log.info("传递参数,param={}",heroBaseInfoModel.toString());//@Data默认实现toString()
        return heroBaseInfoService.add(heroBaseInfoModel);
    }

    @PostMapping("/modifyHeroMessage")
    public JsonResult modify(HeroBaseInfoModifyModel heroBaseInfoModifyModel){
        return heroBaseInfoService.modify(heroBaseInfoModifyModel);
    }

    @PostMapping("/findByName")
    public JsonResult findByName(String name){
        //程序执行到这里退出
        System.exit(1);
        return singleHeroInfoService.findByName(name);
    }
}
