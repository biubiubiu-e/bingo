package com.xfh.bingo.entity.security;

import com.xfh.bingo.entity.BaseInfo;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by wudi on 2018/6/7.
 */
@Data
@Entity(name="role_info")
public class RoleInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_no")
    private String roleNo;

    @Column(name = "role_name")
    private String roleName;




}
