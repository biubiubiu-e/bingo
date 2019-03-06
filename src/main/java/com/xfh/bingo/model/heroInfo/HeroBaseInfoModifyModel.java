package com.xfh.bingo.model.heroInfo;

import com.xfh.bingo.model.BaseInfoModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Description：
 * @Author: xfh
 * @Date: 2018/12/6 16:10
 */
@Data
public class HeroBaseInfoModifyModel extends BaseInfoModel {
    @NotNull
    private String name;
    private BigDecimal bloodVolume;
    private  BigDecimal magicVolume;
    private BigDecimal attackPower;
    private BigDecimal magicPower;
    private BigDecimal physicalDefense;
    private BigDecimal magicDefense;
}
