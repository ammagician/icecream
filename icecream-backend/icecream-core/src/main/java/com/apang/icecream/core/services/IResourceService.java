package com.apang.icecream.core.services;

import com.apang.icecream.core.domain.bo.Resource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author andy.pang
 * @since 2019-09-06
 */
public interface IResourceService extends IService<Resource> {

	boolean updateOrder(List<String> ids);

	int getMaxOrder(Integer parentId, List<Integer> types);

	IPage<Resource> getPage(Page<Resource> page, @Param("queryWrapper") QueryWrapper<Resource> queryWrapper);

	List<Resource> getList(@Param("queryWrapper") QueryWrapper<Resource> queryWrapper);

	List<Resource> getGrantResources(int userId);

}
