package com.hzy.article;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @title: ArticleApplication
 * @Author zxwyhzy
 * @Date: 2023/9/8 16:23
 * @Version 1.0
 */

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.hzy.article.mapper")
public class ArticleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArticleApplication.class);
    }
}
