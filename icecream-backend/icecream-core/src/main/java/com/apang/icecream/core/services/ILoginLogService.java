package com.apang.icecream.core.services;

import com.apang.icecream.core.domain.bo.LoginLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author andy.pang
 * @since 2019-09-06
 */
public interface ILoginLogService extends IService<LoginLog> {
    void logoutBySessionId(String sessionId);
    List<LoginLog> findByVisitorAndTenantIdAndLogoutTimeIsNull(String userName, String tenant);
}
