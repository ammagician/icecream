package com.apang.icecream.core.services;

import com.apang.icecream.core.domain.bo.Org;
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
public interface IOrgService extends IService<Org> {
	List<Org> getAll(int tenantId);

	Org getSummaryById(int id);

	Org getDetailById(int id);

	boolean updateOrder(List<Integer> ids);

	int getMaxOrder(Integer parentId);
}
