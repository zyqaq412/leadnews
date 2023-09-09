package com.hzy.wemedia;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.hzy.wemedia.mapper")
public class WemediaApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(WemediaApplication.class);
    }
}
