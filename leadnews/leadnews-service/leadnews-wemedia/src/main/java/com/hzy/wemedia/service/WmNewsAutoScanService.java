package com.hzy.wemedia.service;

/**
 * @title: WmNewsAutoScanService
 * @Author zxwyhzy
 * @Date: 2023/9/18 14:10
 * @Version 1.0
 */
public interface WmNewsAutoScanService {

    /**
     * 自媒体文章审核
     * @param id  自媒体文章id
     */
    public void autoScanWmNews(Integer id);
}
