package com.apang.icecream.core.mapper;

import com.apang.icecream.core.domain.bo.Resource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author andy.pang
 * @since 2019-09-06
 */
public interface ResourceMapper extends BaseMapper<Resource> {

	Set<String> getPermisionByRole(String roleId);
	
	int getMaxOrder(String parentId, List<Integer> types);

	IPage<Resource> getCustomPage(Page<Resource> page,
								  @Param(Constants.WRAPPER) QueryWrapper<Resource> queryWrapper);

	List<Resource> getCustomList(@Param(Constants.WRAPPER) QueryWrapper<Resource> queryWrapper);

	List<Resource> getGrantResources(String userId);
}
