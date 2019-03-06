package com.xfh.bingo.enums;

import lombok.Data;

/**
 * @Description：
 * @Author: xfh
 * @Date: 2018/12/26 11:19
 */

public enum BambooCode {
    SUCCESS(0000L,"操作成功"),
    QUERY_FAIL(5000L,"查询失败"),
    PARAMETER_IS_INCORRECT(5001L,"参数不正确"),
    SYSTEM_NAME_INVALID(5002L,"接口未开通"),
    SALT_INVALID(5003L,"验签未通过"),
    SOURCE_TYPE_INVALID(5004L,"服务未开通"),
    SYSTEM_ERROR(5005L,"系统错误，请联系管理人员"),
    NOT_PERMISSIONS(5006L,"权限校验失败"),

    TIMESTAMP_INVALID(1001L, "时间戳不对"),
    SIGN_INVALID(1002L, "sign 校验失败"),
    TOKEN_INVALID(1003L, "token 不合法"),
    TOKEN_EXPIRE(1004L, "token 过期"),
    AUTH_INVALID(1005L, "权限不足"),
    CLIENT_NUMBER_NULL(1006L, "clientNumber 为空"),
    PARAM_INVALID(1008L, "请求参数错误"),

    USER_NOT_EXSIT(1010L,"用户不存在"),
    ;
    private Long code;
    private String msg;
    BambooCode(){}
    BambooCode(Long code,String msg){
        this.msg = msg;
        this.code =code;
    }

    public String getMsg() {
        return msg;
    }

    public Long getCode() {
        return code;
    }
}

