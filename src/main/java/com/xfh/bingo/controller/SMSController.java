package com.xfh.bingo.controller;

import com.xfh.bingo.model.SMS.SMSRequestModel;
import org.apache.http.HttpResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description：
 * @Author: xfh
 * @Date: 2018/12/13 15:54
 */
@RestController
public class SMSController {

    @PostMapping("/sendMessage")
    public void sendMessage(SMSRequestModel model){
        String host = "http://yzx.market.alicloudapi.com";
        String path = "/yzx/sendSms";
        String method = "POST";
        String appcode = "850e4173c4e74390b0f4312ae139af93";
        Map<String, String> headers = new HashMap<>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", "15776671632");
        querys.put("param", "code:Hello world!");
        querys.put("tpl_id", "TP1712238");
        Map<String, String> bodys = new HashMap<String, String>();


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            //HttpResponse response = HttpUtils.(host, path, method, headers, querys, bodys);
            HttpResponse response = com.xfh.bingo.utils.HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
