package com.xfh.bingo;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnablePrometheusEndpoint
//@EnableSpringBootMetricsCollector
@EnableAsync //开启异步线程调度
@SpringBootApplication
@Configuration
@EnableScheduling
@PropertySource({"classpath:app.properties", "classpath:bamboo.properties"})
//@EnableAdminServer
@EnableEurekaClient
public class BingoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BingoApplication.class, args);
    }
}
