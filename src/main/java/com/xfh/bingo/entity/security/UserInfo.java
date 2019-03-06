package com.xfh.bingo.entity.security;

import com.xfh.bingo.entity.BaseInfo;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description：用户信息表
 * @Author: xfh
 * @Date: 2018/12/27 17:37
 */

@Data
@Entity
@Table(name = "user_info")
public class UserInfo  {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "account_id")
    private Long accountId;


}
