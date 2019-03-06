package com.xfh.bingo.entity.security;

import com.xfh.bingo.entity.BaseInfo;
import com.xfh.bingo.enums.MembershipLevel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description：账号信息表
 * @Author: xfh
 * @Date: 2018/12/27 17:34
 */
@Data
@Entity
@Table(name = "account_info")
public class AccountInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "password")
    private String password;

}
