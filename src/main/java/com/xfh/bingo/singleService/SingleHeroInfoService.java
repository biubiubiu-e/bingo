package com.xfh.bingo.singleService;

import com.google.gson.JsonObject;
import com.xfh.bingo.model.JsonResult;

/**
 * @Description
 * @Author: xfh
 * @Date: 2018/11/21 15:22
 * @Version
 */
public interface SingleHeroInfoService {
        JsonResult findByName(String name);
}
