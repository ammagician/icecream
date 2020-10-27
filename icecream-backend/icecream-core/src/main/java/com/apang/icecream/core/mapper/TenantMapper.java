package com.apang.icecream.core.mapper;

import com.apang.icecream.core.domain.bo.Tenant;
import com.apang.icecream.core.domain.bo.TenantPortal;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author andy.pang
 * @since 2019-09-06
 */
public interface TenantMapper extends BaseMapper<Tenant> {
	public void clearPortals(@Param("id") String tenantId);

	public void insertPortals(String tenantId, List<String> ids);

	public Tenant getDetailById(String id);

	IPage<Tenant> getPage(Page<Tenant> page, @Param(Constants.WRAPPER) QueryWrapper<Tenant> queryWrapper);

	public List<TenantPortal> findAllByTenantId(String tenantId);
}
