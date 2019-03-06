package com.xfh.bingo.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * @Description：
 * @Author: xfh
 * @Date: 2018/12/27 17:21
 */
@Data
public class UserModel {

    @NotNull(message = "不能为空")
    private String username;
    @NotNull(message = "不能为空")
    private String accountname;
    @NotNull(message = "不能为空")
    private String password;

}
