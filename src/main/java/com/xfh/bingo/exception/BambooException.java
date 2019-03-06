package com.xfh.bingo.exception;

import lombok.Data;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Description：
 * @Author: xfh
 * @Date: 2018/12/26 11:06
 */
@Data
public class BambooException extends Exception{
    private String msg;
    private Long code;
    public BambooException(){
        this.thisClassMethod();//调用本类方法
    }
    public String thisClassMethod(){
        return "调用本类方法";
    }
    public BambooException(Long code) {
        this();//调用构造方法
        this.code = code;
    }
    public BambooException(String msg) {
        this();
        this.msg = msg;
    }
    public BambooException(String msg,Long code){
        this.msg = msg;
        this.code = code;
    }

    /**
     * 获取异常的堆栈信息
     *
     * @param t
     * @return
     */
    public static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        try {
            t.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }
}
