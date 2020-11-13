package com.apang.icecream.core.services.impl;

import com.apang.icecream.core.domain.bo.Permission;
import com.apang.icecream.core.domain.bo.Role;
import com.apang.icecream.core.mapper.RoleMapper;
import com.apang.icecream.core.services.IPermissionService;
import com.apang.icecream.core.services.IRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
	/**
	 * DOZER mapper.
	 */
	@Autowired
	Mapper mapper;

	@Autowired
	private SqlSessionFactory sessionFactory;

	@Autowired
	private IPermissionService permissionService;

	@Override
	public boolean saveOrUpdateSafely(Role role) {
		boolean success = saveOrUpdate(role);
		if (success) {
			// 更新门户权限以及对应其他权限
			List<String> portals = role.getPortals();
			success = updatePortals(role.getId(), portals);
		}

		return success;
	}

	private boolean updatePortals(int roleId, List<String> portals) {
		QueryWrapper<Permission> queryWrapper = new QueryWrapper<Permission>();
		queryWrapper.eq("ROLEID", roleId);
		queryWrapper.eq("TYPE", 1);
		List<Permission> list = permissionService.list(queryWrapper);
		List<String> portals_new = new ArrayList<String>();
		List<String> permission_del = new ArrayList<String>();
		portals_new.addAll(portals);

		for (Permission p : list) {
			String resId = p.getResId().toString();
			if (portals.contains(resId)) {
				portals_new.remove(resId);
			}else{
				permission_del.add(p.getId().toString());
			}
		}

		permissionService.removeByIds(permission_del);

		if (portals_new.size() == 0) {
			return true;
		}

		list = new ArrayList<Permission>();
		for (String id : portals_new) {
			Permission p = new Permission();
			p.setPortalId(Integer.parseInt(id));
			p.setResId(Integer.parseInt(id));
			p.setRoleId(roleId);
			p.setType(1);
			list.add(p);
		}
		return addResources(list);
	}

	private boolean addResources(List<Permission> list) {
		return permissionService.saveOrUpdateBatch(list);
	}

	@Override
	public boolean updatePortal(Role role, String portalId) {
		String roleId = role.getId().toString();
		QueryWrapper<Permission> queryWrapper = new QueryWrapper<Permission>();
		queryWrapper.eq("ROLEID", roleId);
		queryWrapper.eq("PortalId", portalId);
		queryWrapper.notIn("RESID", portalId);
		boolean success = true;
		if (permissionService.count(queryWrapper) > 0) {
			success = permissionService.remove(queryWrapper);
		}
		List<Permission> list = role.getPermissions();
		if (success && !CollectionUtils.isEmpty(list)) {
			success = addResources(list);
		}

		return success;
	}

	@Override
	public List<Permission> getPermision(String roleId, String portalId) {
		Permission o = new Permission();
		o.setRoleId(Integer.parseInt(roleId));
		o.setPortalId(Integer.parseInt(portalId));
		QueryWrapper<Permission> queryWrapper = new QueryWrapper<Permission>(o);
		queryWrapper.notIn("RESID", portalId);
		return permissionService.list(queryWrapper);
	}

	private RoleMapper getMapper() {
		RoleMapper mapper = sessionFactory.openSession().getMapper(RoleMapper.class);
		return mapper;
	}

	@Override
	public IPage<Role> getPage(Page<Role> page, QueryWrapper<Role> queryWrapper) {
		return getMapper().getPage(page, queryWrapper);
	}

	@Override
	public List<Role> getList(QueryWrapper<Role> queryWrapper) {
		return getMapper().getList(queryWrapper);
	}

}
