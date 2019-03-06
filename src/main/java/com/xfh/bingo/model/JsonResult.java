package com.xfh.bingo.model;


import lombok.Data;
import springfox.documentation.spring.web.json.Json;

/**
 * @Description
 * @Author: xfh
 * @Date: 2018/11/21 15:26
 * @Version
 */
@Data
public class JsonResult<T> {
    private String msg;
    private Long code;
    private T data;

    public T getData() {
        return data;
    }
    public Long getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }

    public JsonResult setData(T data) {
        this.data = data;
        return this;
    }
    public JsonResult setCode(Long code) {
        this.code = code;
        return this;
    }
    public JsonResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public JsonResult(){
    }
    public JsonResult(String msg,Long code){
        this.msg = msg;
        this.code =code;
    }
    public JsonResult(String msg,Long code,T data){
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public static JsonResult Success(String msg,Long code,Object data){
        return new JsonResult(msg,code,data);
    }
    public static JsonResult error(String msg,Long code,Object data){
        return new JsonResult(msg,code,data);
    }
    public static JsonResult error(String msg,Long code){
        return new JsonResult(msg,code);
    }
}
