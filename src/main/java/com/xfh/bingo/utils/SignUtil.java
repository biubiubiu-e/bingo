package com.xfh.bingo.utils;

import com.google.common.collect.ImmutableSortedMap;
import com.xfh.bingo.model.heroInfo.HeroBaseInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;


import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @Description：验签工具（生成，加密，解密）
 * 验签规则：根据参数名称（除签名和图片）将所有请求参数按照字母先后顺序排序：key + value …. key + value
 * 例如：将foo=1,bar=2,baz=3 排序为bar=2,baz=3,foo=1，参数名和参数值链接后，得到拼装字符串bar2baz3foo1
 * md5：将salt 拼接到参数字符串头、尾进行md5加密，格式是：md5(saltkey1value1key2value2…salt)
 * 注：salt 分配；value为null，不拼串 ；先计算签名后拼装请求
 * MD5校验目的：
 * ①接口服务方提供加密规则，如参数拼接顺序，只有双方知道的salt值（对于相同参数，相同加密规则，生成的MD5加密串必相同）
 * ②接口调用方对传值加密后sign随参数一起传递，
 * ③接口提供方对接受的参数也进行MD5加密，得到sign,比对传来的sign,
 * ④相同说明有接口的调用权限，不同说明没有使用权限或者参数传递中有缺失或者解码错误
 * @Author: xfh
 * @Date: 2018/12/19 10:17
 */
@Component //!!!!!
public class SignUtil {

    @Value("${SALT}")
    private String salt;

    private static SignUtil signUtil;
    @PostConstruct
    public void init(){
        signUtil = this;
        signUtil.salt = this.salt;
    }

    public static String createSign(Object object){
        Map map = BeanUtil.objectToMapWithoutBlankValue(object);
        map.remove("sign");
        ImmutableSortedMap sortedMap = new ImmutableSortedMap.Builder<String,String>(String::compareTo).putAll(map).build();
        //按规则拼接串
        StringBuffer sb = new StringBuffer();
        sb.append(signUtil.salt);
        sortedMap.forEach((k,v) ->sb.append(k).append(v));
        sb.append(signUtil.salt);
        //串加密
        return DigestUtils.md5DigestAsHex(sb.toString().getBytes());
    }

}
