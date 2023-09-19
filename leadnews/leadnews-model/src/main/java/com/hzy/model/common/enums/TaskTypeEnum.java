package com.hzy.model.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @title: TaskTypeEnum
 * @Author zxwyhzy
 * @Date: 2023/9/19 16:48
 * @Version 1.0
 */
@Getter
@AllArgsConstructor
public enum TaskTypeEnum {

    NEWS_SCAN_TIME(1001, 1,"文章定时审核"),
    REMOTEERROR(1002, 2,"第三方接口调用失败，重试");
    private final int taskType; //对应具体业务
    private final int priority; //业务不同级别
    private final String desc; //描述信息
}