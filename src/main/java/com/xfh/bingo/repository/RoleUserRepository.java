package com.xfh.bingo.repository;

import com.xfh.bingo.entity.security.RoleUser;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @Description：
 * @Author: xfh
 * @Date: 2019/2/12 16:58
 */
public interface RoleUserRepository extends PagingAndSortingRepository<RoleUser, Long>, JpaSpecificationExecutor<RoleUser> {

    List<RoleUser> findByUserId(Long userId);
}
