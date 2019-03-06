package com.xfh.bingo.repository;

import com.xfh.bingo.entity.HeroInfo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @Description
 * @Author: xfh
 * @Date: 2018/11/21 15:16
 * @Version
 */
public interface HeroBaseInfoRepository extends PagingAndSortingRepository<HeroInfo, Long>, JpaSpecificationExecutor<HeroInfo> {

    HeroInfo findById(Long id);
    HeroInfo findByName(String name);

}
