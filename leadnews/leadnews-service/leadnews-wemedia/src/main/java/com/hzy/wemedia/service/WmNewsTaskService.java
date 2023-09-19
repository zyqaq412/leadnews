package com.hzy.wemedia.service;

import java.util.Date;

/**
 * @title: WmNewsTaskService
 * @Author zxwyhzy
 * @Date: 2023/9/19 16:47
 * @Version 1.0
 */
public interface WmNewsTaskService {

    /**
     * 添加任务到延迟队列中
     * @param id  文章的id
     * @param publishTime  发布的时间  可以做为任务的执行时间
     */
     void addNewsToTask(Integer id, Date publishTime);

    /**
     * 消费延迟队列数据
     */
    void scanNewsByTask();
}
