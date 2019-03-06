package com.xfh.bingo.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @Description
 * @Author: xfh
 * @Date: 2018/11/21 14:49
 * @Version
 */
@Data
@Entity
@Table(name = "hero_base_info")
public class HeroInfo extends BaseInfo{

    @Column(name = "name")
    private String name;

    @Column(name = "blood_volume")
    private BigDecimal bloodVolume;

    @Column(name = "magic_volume")
    private  BigDecimal magicVolume;

    @Column(name = "attack_power")
    private BigDecimal attackPower;

    @Column(name = "magic_power")
    private BigDecimal magicPower;

    @Column(name = "physical_defense")
    private BigDecimal physicalDefense;

    @Column(name = "magic_defanse")
    private BigDecimal magicDefense;

}
