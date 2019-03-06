package com.xfh.bingo.repository;

import com.xfh.bingo.entity.security.UserInfo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @Description：
 * @Author: xfh
 * @Date: 2019/2/12 16:28
 */
public interface UserInfoRepository extends PagingAndSortingRepository<UserInfo, Long>, JpaSpecificationExecutor<UserInfo> {

    UserInfo findByUsername(String username);
}
