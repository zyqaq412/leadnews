package com.hzy.model.schedule.dtos;

import lombok.Data;

import java.io.Serializable;

/**
 * @title: Task
 * @Author zxwyhzy
 * @Date: 2023/9/19 14:54
 * @Version 1.0
 */
@Data
public class Task implements Serializable {

    /**
     * 任务id
     */
    private Long taskId;
    /**
     * 类型
     */
    private Integer taskType;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 执行id
     */
    private long executeTime;

    /**
     * task参数
     */
    private byte[] parameters;

}
