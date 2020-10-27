package com.apang.icecream.core.services;

import com.apang.icecream.core.domain.bo.Permission;
import com.apang.icecream.core.domain.bo.Role;
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
public interface IRoleService extends IService<Role> {
	public boolean saveOrUpdateSafely(Role role);
	public boolean updatePortal(Role role, String portalId);
	public List<Permission> getPermision(String roleId, String portalId);

	IPage<Role> getPage(Page<Role> page, QueryWrapper<Role> queryWrapper);

	List<Role> getList(QueryWrapper<Role> queryWrapper);

}
