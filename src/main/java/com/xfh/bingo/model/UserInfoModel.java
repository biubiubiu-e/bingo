package com.xfh.bingo.model;

import com.xfh.bingo.enums.MembershipLevel;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

/**
 * @Description：
 * @Author: xfh
 * @Date: 2018/12/6 14:15
 */
@Data
public class UserInfoModel {

    private String userName;

    private String password;

    private MembershipLevel membershipLevel;

    private Long packageId;

    @Email
    private String email;
}
