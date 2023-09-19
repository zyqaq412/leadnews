package com.hzy.schedule;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @title: ScheduleApplication
 * @Author zxwyhzy
 * @Date: 2023/9/19 13:49
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.hzy.schedule.mapper")
public class ScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleApplication.class,args);
    }
}

