package com.xfh.bingo.repository;

import com.xfh.bingo.entity.security.AccountInfo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @Description：
 * @Author: xfh
 * @Date: 2019/2/12 16:42
 */
public interface AccountInfoRepository extends PagingAndSortingRepository<AccountInfo, Long>, JpaSpecificationExecutor<AccountInfo> {

    AccountInfo findByAccountName(String accountname);
}
