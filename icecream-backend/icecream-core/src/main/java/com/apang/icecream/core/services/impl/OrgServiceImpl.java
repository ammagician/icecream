package com.apang.icecream.core.services.impl;

import com.apang.icecream.core.domain.bo.Org;
import com.apang.icecream.core.mapper.OrgMapper;
import com.apang.icecream.core.services.IOrgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
public class OrgServiceImpl extends ServiceImpl<OrgMapper, Org> implements IOrgService {

	@Autowired
	private SqlSessionFactory sessionFactory;

	private OrgMapper getMapper() {
		OrgMapper mapper = sessionFactory.openSession().getMapper(OrgMapper.class);
		return mapper;
	}


	@Override
	public List<Org> getAll(String tenantId) {
		OrgMapper mapper = getMapper();
		List<Org> orgs = mapper.getAllOrgs(tenantId);
		return listToTree(orgs);
	}

	private List<Org> listToTree(List<Org> list) {
		List<Org> result = new ArrayList<Org>();
		Map<String, Org> hash = list.stream().collect(Collectors.toMap(r -> r.getId(), r -> r));
		for (Org r : list) {
			Org p = hash.get(r.getParentId());
			if (p == null) {
				result.add(r);
			} else {
				if (p.getChildren() == null) {
					p.setChildren(new ArrayList<Org>());
				}
				p.getChildren().add(r);
			}
		}

		return result;
	}

	@Override
	public boolean updateOrder(List<String> ids) {
		List<Org> list = new ArrayList<Org>();
		int i = 1;
		for (String id : ids) {
			Org res = new Org();
			res.setId(id);
			res.setOrderNo(i);
			list.add(res);
			i++;
		}

		return this.updateBatchById(list);
	}

	@Override
	public int getMaxOrder(String parentId) {
		if (parentId == null) {
			parentId = "";
		}

		OrgMapper mapper = getMapper();
		return mapper.getMaxOrder(parentId);
	}

	@Override
	public Org getSummaryById(String id) {
		OrgMapper mapper = getMapper();
		return mapper.getSummaryById(id);
	}

	@Override
	public Org getDetailById(String id) {
		OrgMapper mapper = getMapper();
		Org org = mapper.getSummaryById(id);
		org.setChildren(mapper.getByParentId(org.getParentId()));
		return org;
	}

}
