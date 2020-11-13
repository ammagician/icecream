package com.apang.icecream.core.services;

import com.apang.icecream.core.domain.bo.Role;
import com.apang.icecream.core.domain.bo.User;
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
public interface IUserService extends IService<User> {
	public User findByLoginNameAndTenantId(String loginName, int tenantId);

	public boolean updateRole(int id, List<Role> roles);

	public User getDetailById(int id);

	public void saveOrUpdateSafely(User user);

	IPage<User> getPage(Page<User> page, QueryWrapper<User> queryWrapper);

	List<User> getList(QueryWrapper<User> queryWrapper);

}
