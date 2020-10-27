package com.apang.icecream.core.services;

import com.apang.icecream.core.domain.bo.OperateLog;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author andy.pang
 * @since 2019-09-06
 */
public interface IOperateLogService extends IService<OperateLog> {
    IPage<OperateLog> getPage(Page<OperateLog> page, @Param(Constants.WRAPPER) QueryWrapper<OperateLog> queryWrapper);

}
