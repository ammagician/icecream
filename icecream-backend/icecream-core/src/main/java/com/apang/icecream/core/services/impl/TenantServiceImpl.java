package com.apang.icecream.core.services.impl;

import com.apang.icecream.core.domain.bo.*;
import com.apang.icecream.core.util.EncodeUtil;
import com.apang.icecream.config.UserProperty;
import com.apang.icecream.core.mapper.TenantMapper;
import com.apang.icecream.core.services.IOrgService;
import com.apang.icecream.core.services.IRoleService;
import com.apang.icecream.core.services.ITenantService;
import com.apang.icecream.core.services.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

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
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements ITenantService {
	@Autowired
	private SqlSessionFactory sessionFactory;

	@Autowired
	private IOrgService orgService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private UserProperty userProperty;

	@Autowired
	private IUserService userService;


	@Override
	public boolean updatePortals(String id, List<String> ids) {
		TenantMapper mapper = getMapper();
		mapper.clearPortals(id);
		if (!CollectionUtils.isEmpty(ids)) {
			mapper.insertPortals(id, ids);
		}

		return true;
	}

	@Override
	public Tenant getDetailById(String id) {
		TenantMapper mapper = getMapper();
		return mapper.getDetailById(id);
	}

	@Override
	public void saveOrUpdateSafely(Tenant tenant) {
		if(StringUtils.isEmpty(tenant.getId())){
			String id = UUID.randomUUID().toString();
			tenant.setId(id);
			saveOrUpdate(tenant);
			updatePortals(id, tenant.getPortals());

			//创建组织
			Org org = new Org();
			org.setTenantId(id);
			org.setId(UUID.randomUUID().toString());
			org.setName(tenant.getRootOrg());
			org.setRemarks(tenant.getRootOrg());
			org.setCode(tenant.getRootOrgCode());
			org.setOrderNo(1);
			org.setType(1);
			boolean success = orgService.save(org);

			//创建角色,授权门户
			Role role = new Role();
			String roleId = UUID.randomUUID().toString();
			String portalId = "1d2e030e-0a65-4239-bfae-1dace826e581";
			role.setId(roleId);
			role.setName("管理员");
			role.setCode("admin");
			role.setRemarks("租户：" + tenant.getName()  + " --- 管理员角色");
			role.setTenantId(id);
			List<String> portals = new ArrayList<String>();
			portals.add(portalId);
			role.setPortals(portals);
			roleService.saveOrUpdateSafely(role);

			//授权菜单资源
			Role role_p = new Role();
			role_p.setId(roleId);
			List<Permission> permissions = new ArrayList<Permission>();
			permissions.add(new Permission(roleId, "d309dad3-8301-433b-a6f0-7feacd7ff2a5", portalId, 3));
			permissions.add(new Permission(roleId, "ef6ed001-c7c8-45d7-bb97-66f8250d09b1", portalId, 4));
			permissions.add(new Permission(roleId, "ef6ed001-c7c8-45d7-bb97-66f8250d09b2", portalId, 4));
			permissions.add(new Permission(roleId, "ef6ed001-c7c8-45d7-bb97-66f8250d09b7", portalId, 4));
			role_p.setPermissions(permissions);
			roleService.updatePortal(role_p, portalId);

			//创建管理员
			User user = new User();
			user.setTenantId(id);
			user.setId(UUID.randomUUID().toString());
			byte[] pwd = userProperty.getDefaultPassword().getBytes();
			String pwdStr = DigestUtils.md5DigestAsHex(pwd);
			user.setPassword(EncodeUtil.encode(pwdStr));
			user.setLoginName("admin");
			user.setOrgId(org.getId());
			user.setUserName(tenant.getName()  +" --- 管理员");
			user.setType(1);
			user.setRemarks("租户：" + tenant.getName()  + " --- 管理员");
			user.setEmail("13800000000@qq.com");
			user.setAvatar("https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png0");
			user.setMobile("13800000000");
			List<Role> roles = new ArrayList<Role>();
			roles.add(role);
			user.setRoles(roles);
			userService.saveOrUpdateSafely(user);
		}else{
			boolean success = saveOrUpdate(tenant);
			if (success) {
				String id = tenant.getId();
				updatePortals(id, tenant.getPortals());
			}
		}
	}

	@Override
	public IPage<Tenant> getPage(Page<Tenant> page, QueryWrapper<Tenant> queryWrapper) {
		return getMapper().getPage(page, queryWrapper);
	}

	@Override
	public List<TenantPortal> findAllByTenantId(String tenantId) {
		return getMapper().findAllByTenantId(tenantId);
	}

	private TenantMapper getMapper() {
		TenantMapper mapper = sessionFactory.openSession().getMapper(TenantMapper.class);
		return mapper;
	}
}
