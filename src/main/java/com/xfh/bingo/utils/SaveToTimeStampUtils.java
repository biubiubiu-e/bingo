package com.xfh.bingo.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Description：转化为时间戳（TimeStamp），并作范围校验工具类
 * @Author: xfh
 * @Date: 2018/12/1 14:02
 */
@Slf4j
public class SaveToTimeStampUtils {

    /**
     * 将Long类型的时间戳转换成String 类型的时间格式，时间格式为：yyyy-MM-dd HH:mm:ss
     */
    public static Timestamp convertLongToTimeStamp(Long time){
        Assert.notNull(time, "时间不能为空");
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Timestamp timestamp = Timestamp.valueOf(ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time),ZoneId.systemDefault())));
        if(time < 1000L || time > 2147454847000L){
            log.error("日期参数不正确,timestamp={}",timestamp);
            return null;
        }
        return timestamp;
    }

    /**
     * 将Long类型的时间戳转换成String 类型的时间格式，时间格式为：yyyy-MM-dd HH:mm:ss
     */
    public static Timestamp convertDateToTimeStamp(Date date){
        Assert.notNull(date, "时间不能为空");
        if(date.getTime() < 1000 || date.getTime() > 2147454847000L){
            log.error("日期参数不正确,date={}",date);
            return null;
        }
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;
    }

    /**
     * 将String类型的时间戳转换成String 类型的时间格式，时间格式为：yyyy-MM-dd HH:mm:ss
     */
    public static Timestamp convertStringToTimeStamp(String string){
        Assert.notNull(string, "时间不能为空");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try{
            date = sdf.parse(string);
        }catch (Exception e){
            log.error("日期格式不正确，time={},正确格式为\"yyyy-MM-dd HH:mm:ss\"",string);
            e.printStackTrace();
        }
        if(date.getTime() < 1000 || date.getTime() > 2147454847000L){
            log.error("日期参数不正确,date={}",date);
            return null;
        }
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;
    }

}
