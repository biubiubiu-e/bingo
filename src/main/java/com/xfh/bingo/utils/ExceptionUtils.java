package com.xfh.bingo.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Description
 * @Author: xfh
 * @Date: 2018/11/21 16:07
 * @Version
 */

public class ExceptionUtils {

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
