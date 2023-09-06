package com.hzy.app.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @title: AppGatewayApplication
 * @Author zxwyhzy
 * @Date: 2023/9/6 21:42
 * @Version 1.0
 */

@SpringBootApplication
@EnableDiscoveryClient  //开启注册中心
public class AppGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppGatewayApplication.class);
    }
}
