package com.xfh.bingo.service.Impl;

import com.xfh.bingo.entity.HeroInfo;
import com.xfh.bingo.model.heroInfo.HeroBaseInfoModel;
import com.xfh.bingo.model.JsonResult;
import com.xfh.bingo.model.heroInfo.HeroBaseInfoModifyModel;
import com.xfh.bingo.repository.HeroBaseInfoRepository;
import com.xfh.bingo.service.HeroBaseInfoService;
import com.xfh.bingo.utils.BeanUtil;
import com.xfh.bingo.utils.ExceptionUtils;
import com.xfh.bingo.utils.FindByMaxIdUtils;
import com.xfh.bingo.utils.VaildatorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @Description
 * @Author: xfh
 * @Date: 2018/11/21 15:21
 * @Version
 */
@Service
@Slf4j
public class HeroBaseInfoServiceImpl implements HeroBaseInfoService {

    @Autowired
    private HeroBaseInfoRepository heroBaseInfoRepository;

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private VaildatorUtils vaildatorUtils;

    @Override
    public JsonResult add(HeroBaseInfoModel heroBaseInfoModel){
        vaildatorUtils.validate(heroBaseInfoModel);
        //原有不能新增
        HeroInfo heroInfo = heroBaseInfoRepository.findByName(heroBaseInfoModel.getName());
        if(heroInfo != null){
            return JsonResult.error("原有不能新增",0001L,null);
        }
        HeroInfo info = new HeroInfo();
        try{
            BeanUtils.copyProperties(heroBaseInfoModel,info);
            info.setId(FindByMaxIdUtils.findByMaxId("hero_base_info"));
            info.setUpdateTime(info.getCreateTime());
        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
            throw new RuntimeException("数据转换异常 ={}");
        }
        heroBaseInfoRepository.save(info);
        return JsonResult.Success("保存成功",0000L,info);
    }

    @Override
    public JsonResult modify(HeroBaseInfoModifyModel heroBaseInfoModifyModel){
        vaildatorUtils.validate(heroBaseInfoModifyModel);
        HeroInfo heroInfo = heroBaseInfoRepository.findByName(heroBaseInfoModifyModel.getName());
        if(heroInfo == null){
            return JsonResult.error("未查询到该英雄",0001L,null);
        }
        /*//制造异常
        heroInfo = null;*/
        HeroInfo info = new HeroInfo();
        try{
            BeanUtils.copyProperties(heroInfo,info);
        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
            throw new IllegalArgumentException("数据转换异常 ={}");
        }
        Object object= BeanUtil.modify(info,heroBaseInfoModifyModel);
        info = (HeroInfo)object;
        heroBaseInfoRepository.save(info);
        return JsonResult.Success("保存成功",0000L,info);
    }


}
