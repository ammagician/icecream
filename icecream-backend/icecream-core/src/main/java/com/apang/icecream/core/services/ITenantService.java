package com.apang.icecream.core.services;

import com.apang.icecream.core.domain.bo.Tenant;
import com.apang.icecream.core.domain.bo.TenantPortal;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public interface ITenantService extends IService<Tenant> {
	public boolean updatePortals(int id, List<String> ids);

	public Tenant getDetailById(int id);

	public void saveOrUpdateSafely(Tenant tenant);

	IPage<Tenant> getPage(Page<Tenant> page, QueryWrapper<Tenant> queryWrapper);

	public List<TenantPortal> findAllByTenantId(int tenantId);
}
