package com.apang.icecream.core.services.impl;

import com.apang.icecream.core.domain.bo.LoginLog;
import com.apang.icecream.core.mapper.LoginLogMapper;
import com.apang.icecream.core.services.ILoginLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author andy.pang
 * @since 2019-09-06
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements ILoginLogService {

    @Override
    public void logoutBySessionId(String sessionId) {
        LoginLog log = new LoginLog();
        log.setSessionID(sessionId);
        QueryWrapper<LoginLog> queryWrapper = new QueryWrapper<LoginLog>(log);

        List<LoginLog> logs = this.list(queryWrapper);
        for (LoginLog logOut : logs) {
            logOut.setLogoutTime(new Timestamp(System.currentTimeMillis()));
        }
        this.saveBatch(logs);
    }

    @Override
    public List<LoginLog> findByVisitorAndTenantIdAndLogoutTimeIsNull(String userName, int tenant) {
        LoginLog log = new LoginLog();
        log.setVisitor(userName);
        log.setTenantId(tenant);
        QueryWrapper<LoginLog> queryWrapper = new QueryWrapper<LoginLog>(log);

        return this.list(queryWrapper);
    }
}
