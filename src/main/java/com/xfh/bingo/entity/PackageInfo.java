package com.xfh.bingo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @Description：
 * @Author: xfh
 * @Date: 2018/12/5 21:19
 */
@Data
@Entity(name = "package_info")
public class PackageInfo extends BaseInfo{

    @Column(name = "rmb")
    private BigDecimal rmb;

    @Column(name = "golden_coins")
    private BigInteger goldenCoins;
}
