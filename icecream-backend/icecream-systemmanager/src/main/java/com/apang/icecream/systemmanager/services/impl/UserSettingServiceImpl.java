package com.apang.icecream.systemmanager.services.impl;

import com.apang.icecream.systemmanager.domain.bo.UserSetting;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.apang.icecream.systemmanager.mapper.UserSettingMapper;
import com.apang.icecream.systemmanager.services.IUserSettingService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author andy.pang
 * @since 2019-09-16
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserSettingServiceImpl extends ServiceImpl<UserSettingMapper, UserSetting> implements IUserSettingService {

}
