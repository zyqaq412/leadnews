package com.hzy.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzy.model.user.pojos.ApUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @title: ApUserMapper
 * @Author zxwyhzy
 * @Date: 2023/9/6 20:12
 * @Version 1.0
 */

@Mapper
public interface ApUserMapper extends BaseMapper<ApUser> {
}
