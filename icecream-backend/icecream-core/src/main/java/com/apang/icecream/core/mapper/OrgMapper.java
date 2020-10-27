package com.apang.icecream.core.mapper;

import com.apang.icecream.core.domain.bo.Org;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author andy.pang
 * @since 2019-09-06
 */
public interface OrgMapper extends BaseMapper<Org> {
	List<Org> getAllOrgs(String tenantId);

	Org getSummaryById(String id);

	List<Org> getByParentId(String parentId);

	int getMaxOrder(String parentId);
}
