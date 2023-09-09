package com.hzy.wemedia.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzy.model.media.pojos.WmUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WmUserMapper extends BaseMapper<WmUser> {
}