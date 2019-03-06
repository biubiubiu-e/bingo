package com.xfh.bingo.utils;
/**
 * @Description
 * @Author: xfh
 * @Date: 2018/11/21 16:21
 * @Version
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;

/**
 *
 * @param
 * @param
 * @return
 */
@Component
public class FindByMaxIdUtils {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    //静态工具类中引入service
    private static FindByMaxIdUtils findByMaxIdUtils;
    @PostConstruct
    public void init(){
        findByMaxIdUtils = this;
        findByMaxIdUtils.entityManager = this.entityManager;
    }
    public static Long findByMaxId(String table){

        String sql = " select  max(id)+1 from " + table;
        Query dataQuery = findByMaxIdUtils.entityManager.createNativeQuery(sql);
        if(dataQuery.getSingleResult() == null){
            return 1L;
        }else {
            return Long.valueOf(dataQuery.getSingleResult().toString());
        }
    }
}
