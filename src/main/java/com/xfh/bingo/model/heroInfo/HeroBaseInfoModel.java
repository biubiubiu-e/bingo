package com.xfh.bingo.model.heroInfo;

import com.xfh.bingo.model.BaseInfoModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Description
 * @Author: xfh
 * @Date: 2018/11/21 15:09
 * @Version
 */
@Data
public class HeroBaseInfoModel extends BaseInfoModel {

    @NotNull
    private String name;
    @NotNull
    private BigDecimal bloodVolume;
    @NotNull
    private  BigDecimal magicVolume;
    @NotNull
    private BigDecimal attackPower;
    @NotNull
    private BigDecimal magicPower;
    @NotNull
    private BigDecimal physicalDefense;
    @NotNull
    private BigDecimal magicDefense;

    //private String sign;
}
