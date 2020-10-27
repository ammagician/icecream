package com.apang.icecream.core.services.impl;

import com.apang.icecream.core.domain.bo.Role;
import com.apang.icecream.core.domain.bo.User;
import com.apang.icecream.core.mapper.UserMapper;
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

import java.util.Arrays;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
	@Autowired
	private SqlSessionFactory sessionFactory;

	@Override
	public User findByLoginNameAndTenantId(String loginName, String tenantId) {
		User user = new User();
		user.setLoginName(loginName);
		user.setTenantId(tenantId);
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>(user);
		User result = this.getOne(queryWrapper);
		if(result != null){
			result = getMapper().getDetailById(result.getId());
		}
		return result;
	}

	@Override
	public boolean updateRole(String ids, List<Role> roles) {
		UserMapper mapper = getMapper();
		String[] idArr = ids.split(",");
		List<String> list = Arrays.asList(idArr);
		mapper.clearRolesByUsers(list);
		if (!CollectionUtils.isEmpty(roles) && idArr.length == 1) {
			mapper.insertRoles(ids, roles);
		}

		return true;
	}

	@Override
	public User getDetailById(String id) {
		UserMapper mapper = getMapper();
		return mapper.getDetailById(id);
	}

	private UserMapper getMapper() {
		UserMapper mapper = sessionFactory.openSession().getMapper(UserMapper.class);
		return mapper;
	}

	@Override
	public void saveOrUpdateSafely(User user) {
		boolean success = saveOrUpdate(user);
		if (success) {
			String id = user.getId();
			updateRole(id, user.getRoles());
		}
	}

	@Override
	public IPage<User> getPage(Page<User> page, QueryWrapper<User> queryWrapper) {
		return getMapper().getPage(page, queryWrapper);
	}

	@Override
	public List<User> getList(QueryWrapper<User> queryWrapper) {
		return getMapper().getList(queryWrapper);
	}
	

}
