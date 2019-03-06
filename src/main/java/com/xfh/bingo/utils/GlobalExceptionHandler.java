package com.xfh.bingo.utils;

/**
 * @Description：全局异常类，接住throw出的异常
 * @Author: xfh
 * @Date: 2019/1/31 15:27
 */

import com.xfh.bingo.model.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public JsonResult handleRunException(RuntimeException ex) {
        return JsonResult.error(ex.getMessage(), 1111L,"拦住啦");
    }

     @ExceptionHandler(IllegalArgumentException.class)
    public JsonResult handleIllException(RuntimeException ex) {
        return JsonResult.error(ex.getMessage(), 1112L,"拦住啦");
    }
}
