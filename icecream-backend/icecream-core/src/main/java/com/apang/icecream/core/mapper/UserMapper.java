package com.apang.icecream.core.mapper;

import com.apang.icecream.core.domain.bo.Role;
import com.apang.icecream.core.domain.bo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author andy.pang
 * @since 2019-09-06
 */
public interface UserMapper extends BaseMapper<User> {
	void clearRolesByUsers(@Param("ids") List<String> ids);

	void insertRoles(String userId, List<Role> roles);

	User getDetailById(String id);

	IPage<User> getPage(Page<User> page, @Param(Constants.WRAPPER) QueryWrapper<User> queryWrapper);

	List<User> getList(@Param(Constants.WRAPPER) QueryWrapper<User> queryWrapper);

}
