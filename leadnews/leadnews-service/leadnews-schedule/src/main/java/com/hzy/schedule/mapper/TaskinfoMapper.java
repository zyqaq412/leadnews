package com.hzy.schedule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzy.model.schedule.pojos.Taskinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.Date;
import java.util.List;
/**
 * @title: TaskinfoMapper
 * @Author zxwyhzy
 * @Date: 2023/9/19 14:56
 * @Version 1.0
 */
@Mapper
public interface TaskinfoMapper extends BaseMapper<Taskinfo> {

    List<Taskinfo> queryFutureTime(@Param("taskType")int type, @Param("priority")int priority, @Param("future")Date future);
}

