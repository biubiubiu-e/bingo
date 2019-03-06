package com.xfh.bingo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.sql.Timestamp;

/**
 * @Description：基础实体类
 * @Author: xfh
 * @Date: 2018/11/23 13:58
 */
@MappedSuperclass
/*通过这个注解，可以将该实体类当成基类实体，
  它不会隐射到数据库表，但继承它的子类实体在隐射时会自动扫描该基类实体的隐射属性，
  添加到子类实体的对应数据库表中。*/
@Data
public class BaseInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "update_time")
    private Timestamp updateTime;

    @PrePersist
    public void perPersist(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        createTime = timestamp;
        updateTime = timestamp;
    }

    @PreUpdate
    public void preUpdate(){
        updateTime = new Timestamp(System.currentTimeMillis());
    }
}
