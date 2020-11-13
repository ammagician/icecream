package com.apang.icecream.core.services.impl;

import com.apang.icecream.core.domain.bo.Resource;
import com.apang.icecream.core.mapper.ResourceMapper;
import com.apang.icecream.core.services.IResourceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {
	@Autowired
	private SqlSessionFactory sessionFactory;

	private ResourceMapper getMapper() {
		ResourceMapper mapper = sessionFactory.openSession().getMapper(ResourceMapper.class);
		return mapper;
	}


	@Override
	public boolean updateOrder(List<String> ids) {
		List<Resource> list = new ArrayList<Resource>();
		int i = 1;
		for (String id : ids) {
			Resource res = new Resource();
			res.setId(Integer.parseInt(id));
			res.setOrderNo(i);
			list.add(res);
			i++;
		}

		return this.updateBatchById(list);
	}

	@Override
	public int getMaxOrder(Integer parentId, List<Integer> types) {
		return getMapper().getMaxOrder(parentId, types);
	}

	@Override
	public List<Resource> getList(QueryWrapper<Resource> queryWrapper) {
		return getMapper().getCustomList(queryWrapper);
	}

	@Override
	public List<Resource> getGrantResources(int userId) {
		return getMapper().getGrantResources(userId);
	}

	@Override
	public IPage<Resource> getPage(Page<Resource> page, QueryWrapper<Resource> queryWrapper) {
		return getMapper().getCustomPage(page, queryWrapper);
	}
}
