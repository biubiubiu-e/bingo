package com.xfh.bingo.service;

import com.xfh.bingo.model.heroInfo.HeroBaseInfoModel;
import com.xfh.bingo.model.JsonResult;
import com.xfh.bingo.model.heroInfo.HeroBaseInfoModifyModel;

/**
 * @Description
 * @Author: xfh
 * @Date: 2018/11/21 15:20
 * @Version
 */
public interface HeroBaseInfoService {

    JsonResult add(HeroBaseInfoModel heroBaseInfoModel);

    JsonResult modify(HeroBaseInfoModifyModel heroBaseInfoModifyModel);
}
