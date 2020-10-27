package com.apang.icecream.core.mapper;

import com.apang.icecream.core.domain.bo.OperateLog;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author andy.pang
 * @since 2019-09-06
 */
public interface OperateLogMapper extends BaseMapper<OperateLog> {
    IPage<OperateLog> getPage(Page<OperateLog> page, @Param(Constants.WRAPPER) QueryWrapper<OperateLog> queryWrapper);
}
