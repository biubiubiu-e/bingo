package com.xfh.bingo.singleService.Impl;

import com.xfh.bingo.entity.HeroInfo;
import com.xfh.bingo.model.JsonResult;
import com.xfh.bingo.repository.HeroBaseInfoRepository;
import com.xfh.bingo.singleService.SingleHeroInfoService;
import com.xfh.bingo.utils.VaildatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author: xfh
 * @Date: 2018/11/21 15:23
 * @Version
 */
@Service
public class SingleHeroInfoServiceImpl implements SingleHeroInfoService {

    @Autowired
    private VaildatorUtils vaildatorUtils;

    @Autowired
    private HeroBaseInfoRepository heroBaseInfoRepository;

    @Override
    public JsonResult findByName(String name){
        vaildatorUtils.validate(name);
        HeroInfo info = heroBaseInfoRepository.findByName(name);
        return info == null?JsonResult.error("未查到",1111L,null):JsonResult.Success("查询成功",0000L,info);
    }
}
