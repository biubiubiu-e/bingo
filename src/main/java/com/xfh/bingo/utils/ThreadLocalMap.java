package com.xfh.bingo.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description：
 * @Author: xfh
 * @Date: 2019/1/31 14:53
 */


public class ThreadLocalMap {

    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL_MAP = new ThreadLocal<>();

    public static void set(String key, Object value) {
        Map<String, Object> map = THREAD_LOCAL_MAP.get();
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(key, value);
        THREAD_LOCAL_MAP.set(map);
    }

    public static Object get(String key) {
        Map<String, Object> map = THREAD_LOCAL_MAP.get();
        if (map != null) {
            return map.get(key);
        }
        return null;
    }

    public static void remove() {
        THREAD_LOCAL_MAP.remove();
    }

}
