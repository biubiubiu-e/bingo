package com.xfh.bingo.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description：
 * @Author: xfh
 * @Date: 2018/12/6 15:31
 */
@Data
public class BaseInfoModel {
    private Long id;
    private Long createTime;
    private Long updateTime;

}
