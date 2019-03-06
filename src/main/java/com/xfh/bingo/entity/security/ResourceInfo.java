package com.xfh.bingo.entity.security;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by wx on 2018/6/7.
 */
@Data
@Entity(name = "resource_info")
public class ResourceInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "resource_name")
    private String resourceName;


    @Column(name = "resource_url")
    private String resourceUrl;


}
