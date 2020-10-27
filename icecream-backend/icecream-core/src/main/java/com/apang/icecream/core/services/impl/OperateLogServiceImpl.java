package com.apang.icecream.core.services.impl;

import com.apang.icecream.core.domain.bo.OperateLog;
import com.apang.icecream.core.mapper.OperateLogMapper;
import com.apang.icecream.core.services.IOperateLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author andy.pang
 * @since 2019-09-06
 */
@Service
public class OperateLogServiceImpl extends ServiceImpl<OperateLogMapper, OperateLog> implements IOperateLogService {
    @Autowired
    private SqlSessionFactory sessionFactory;

    @Override
    public IPage<OperateLog> getPage(Page<OperateLog> page, QueryWrapper<OperateLog> queryWrapper) {
        return getMapper().getPage(page, queryWrapper);
    }

    private OperateLogMapper getMapper() {
        OperateLogMapper mapper = sessionFactory.openSession().getMapper(OperateLogMapper.class);
        return mapper;
    }
}
