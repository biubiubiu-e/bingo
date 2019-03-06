package com.xfh.bingo.utils;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * @Description:验证工具类
 * @Author: xfh
 * @Date: 2018/11/21 16:35
 * @Version
 */
@Slf4j
@Component
public class VaildatorUtils<T> {
    @Autowired
    private Validator validator;

    public void validate(T t, Class<?>... groups) {
        Set<ConstraintViolation<T>> validateResult = validator.validate(t, groups);
        LinkedHashMap<String, String> errors = Maps.newLinkedHashMap();
        if (!validateResult.isEmpty()) {
            validateResult.forEach(s -> {
                errors.put(s.getPropertyPath().toString(), s.getMessage());
            });
        }
        if (MapUtils.isNotEmpty(errors)) {
            throw new RuntimeException(errors.toString());
        }
    }
}
