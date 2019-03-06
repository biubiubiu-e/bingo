package com.xfh.bingo.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description：
 * @Author: xfh
 * @Date: 2018/11/21 16:42
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "validator")
public class ValidatorProperties {
    private boolean fastFail;
}
