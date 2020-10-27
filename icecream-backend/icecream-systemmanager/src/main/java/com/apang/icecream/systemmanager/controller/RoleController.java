package com.apang.icecream.systemmanager.controller;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.apang.icecream.auth.utils.AuthenticationUtil;
import com.apang.icecream.core.domain.bo.Permission;
import com.apang.icecream.core.domain.bo.Role;
import com.apang.icecream.core.services.IResourceService;
import com.apang.icecream.core.services.IRoleService;
import com.apang.icecream.systemmanager.domain.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.apang.icecream.core.base.HttpResult;
import com.apang.icecream.core.base.LoggerManage;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author andy.pang
 * @since 2019-09-06
 */
@RestController
@RequestMapping("/api/sysmgr")
public class RoleController {
	@Autowired
	private IRoleService roleService;

	@Autowired
	private IResourceService resourcesService;

	@ApiOperation(value = "根据ID查询角色", notes = "根据ID查询角色")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/role/detail", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getById(@RequestParam("id") String id) {

		Role role = roleService.getById(id);
		HttpResult result = HttpResult.ok();
		result.setData(role);
		return result;
	}

	@ApiOperation(value = "查询角色列表", notes = "查询角色列表")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/role/list", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getList(Role role) {
		QueryWrapper<Role> wrapper = getBaseQueryWrapper(role);
		List<Role> roles = roleService.getList(wrapper);
		HttpResult result = HttpResult.ok();
		result.setData(roles);
		return result;
	}

	@ApiOperation(value = "根据条件分页查询角色", notes = "根据条件分页查询角色")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/role/page", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getPage(int current, int size, Role role) {
		Page<Role> page = new Page<Role>(current, size);
		QueryWrapper<Role> wrapper = getBaseQueryWrapper(role);

		IPage<Role> roles = roleService.getPage(page, wrapper);

		HttpResult result = HttpResult.ok();
		result.setData(roles);
		return result;
	}

	@ApiOperation(value = "新增角色", notes = "新增角色")
	@ApiResponses({ @ApiResponse(code = 200, message = "新增成功") })
	@RequestMapping(value = "/role/add", method = RequestMethod.POST)
	@ResponseBody
	@LoggerManage(module = "角色管理", description = "", operate = "新增角色")
	public HttpResult save(@RequestBody RoleVo role) {
		HttpResult result;
		try {
			role.setId(UUID.randomUUID().toString());
			role.setTenantId(AuthenticationUtil.getTenantId());
			roleService.saveOrUpdateSafely(role);
			result = HttpResult.ok();
		}catch(Exception e) {
			result = HttpResult.error("服务器异常");
		}
		
		return result;
	}

	@ApiOperation(value = "更新角色", notes = "更新角色")
	@ApiResponses({ @ApiResponse(code = 200, message = "更新成功") })
	@RequestMapping(value = "/role/edit", method = RequestMethod.PUT)
	@ResponseBody
	@LoggerManage(module = "角色管理", description = "", operate = "更新角色")
	public HttpResult update(@RequestBody RoleVo role) {
		HttpResult result;
		try {
			roleService.saveOrUpdateSafely(role);
			result = HttpResult.ok();
		} catch (Exception e) {
			result = HttpResult.error("服务器异常");
		}
		return result;
	}



	@ApiOperation(value = "根据ID删除角色", notes = "逗号分隔")
	@ApiResponses({ @ApiResponse(code = 200, message = "删除成功") })
	@RequestMapping(value = "/role/del", method = RequestMethod.DELETE)
	@ResponseBody
	@LoggerManage(module = "角色管理", description = "", operate = "删除角色")
	public HttpResult del(@RequestParam("ids") String ids) {

		String[] arr = ids.split(",");
		List<String> string = Arrays.asList(arr);
		boolean success = roleService.removeByIds(string);

		HttpResult result = HttpResult.ok();
		result.setSuccess(success);

		return result;
	}

	@ApiOperation(value = "更新权限", notes = "更新权限")
	@ApiResponses({ @ApiResponse(code = 200, message = "更新成功") })
	@RequestMapping(value = "/role/permision/edit", method = RequestMethod.PUT)
	@ResponseBody
	@LoggerManage(module = "角色管理", description = "", operate = "更新角色权限")
	public HttpResult updatePermision(@RequestBody RoleVo role) {
		HttpResult result;
		try {
			roleService.updatePortal(role, role.getPortalId());
			result = HttpResult.ok();
		} catch (Exception e) {
			result = HttpResult.error("服务器异常");
		}
		return result;
	}

	@ApiOperation(value = "根据角色查询权限信息", notes = "根据角色查询权限信息")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/role/permision", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getRolePermision(String roleId, String portalId) {
		List<Permission> list = roleService.getPermision(roleId, portalId);

		HttpResult result = HttpResult.ok();
		result.setData(list);
		return result;
	}

	private QueryWrapper<Role> getBaseQueryWrapper(Role role) {
		QueryWrapper<Role> wrapper = new QueryWrapper<Role>();
		if (!StringUtils.isEmpty(role.getName())) {
			wrapper.like("name", role.getName());
		}

		if (!StringUtils.isEmpty(role.getCode())) {
			wrapper.like("code", role.getCode());
		}


		wrapper.like("tenantId", AuthenticationUtil.getTenantId());

		return wrapper;
	}
}
