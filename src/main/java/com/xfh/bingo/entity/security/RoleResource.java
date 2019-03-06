package com.xfh.bingo.entity.security;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by wx on 2018/6/7.
 */
@Data
@Entity(name = "role_resource")
public class RoleResource {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name="resource_id")
    private Long resourceId;

    @Column(name="role_id")
    private Long roleId;
}
