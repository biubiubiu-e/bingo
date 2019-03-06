package com.xfh.bingo.enums;

import lombok.Data;
import lombok.Getter;

/**
 * @Description：
 * @Author: xfh
 * @Date: 2018/11/23 14:04
 */
public enum MembershipLevel implements BaseEnum<Integer>{

    ZERO_LEVEL(0,"普通用户","无特权"),
    FIRST_LEVEL(1,"一级会员",""),
    SECOND_LEVEL(2,"二级会员",""),
    THIRD_LEVEL(3,"三级会员",""),
    ;
    @Getter
    private Integer code;

    @Getter
    private String desc;

    private String msg;

    MembershipLevel(Integer code, String desc,String msg) {
        this.code = code;
        this.desc = desc;
        this.msg = msg;
    }

    public static class Convert extends BaseEnumConverter<MembershipLevel, Integer> {
    }
}
