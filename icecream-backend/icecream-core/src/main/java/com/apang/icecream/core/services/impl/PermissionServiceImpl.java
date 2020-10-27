package com.apang.icecream.core.services.impl;

import com.apang.icecream.core.domain.bo.Permission;
import com.apang.icecream.core.mapper.PermissionMapper;
import com.apang.icecream.core.services.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author andy.pang
 * @since 2019-09-06
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
