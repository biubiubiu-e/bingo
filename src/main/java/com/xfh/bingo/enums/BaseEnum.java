package com.xfh.bingo.enums;

/**
 * @Description：
 * @Author: xfh
 * @Date: 2018/11/23 14:05
 */
public interface BaseEnum<T> {
    /**
     * 存取到数据库中的值.
     *
     * @return
     */
    T getCode();

    /**
     * 获取到描述
     *
     * @return
     */
    String getDesc();
}
