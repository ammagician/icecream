package com.apang.icecream.systemmanager.controller;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.apang.icecream.auth.utils.AuthenticationUtil;
import com.apang.icecream.core.domain.bo.User;
import com.apang.icecream.core.services.IOrgService;
import com.apang.icecream.core.domain.bo.Org;
import com.apang.icecream.core.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
public class OrgController {
	@Autowired
	private IOrgService orgService;
	@Autowired
	private IUserService userService;

	@ApiOperation(value = "根据ID查询机构", notes = "根据ID查询机构")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/org/detail", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getById(@RequestParam("id") String id) {

		Org org = orgService.getById(id);
		HttpResult result = HttpResult.ok();
		result.setData(org);
		return result;
	}

	@ApiOperation(value = "查询机构树", notes = "查询机构树")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/org/list", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getAll() {
		List<Org> orgs = orgService.getAll(AuthenticationUtil.getTenantId());
		HttpResult result = HttpResult.ok();
		result.setData(orgs);
		return result;
	}

	@ApiOperation(value = "新增机构", notes = "新增机构")
	@ApiResponses({ @ApiResponse(code = 200, message = "新增成功") })
	@RequestMapping(value = "/org/add", method = RequestMethod.POST)
	@ResponseBody
	@LoggerManage(module = "组织管理", description = "", operate = "新增组织")
	public HttpResult save(@RequestBody Org org) {
		org.setId(UUID.randomUUID().toString());
		org.setTenantId(AuthenticationUtil.getTenantId());
		int size = orgService.getMaxOrder(org.getParentId());
		org.setOrderNo(size + 1);
		boolean success = orgService.save(org);

		HttpResult result = HttpResult.ok();
		result.setSuccess(success);
		return result;
	}

	@ApiOperation(value = "更新机构", notes = "更新机构")
	@ApiResponses({ @ApiResponse(code = 200, message = "更新成功") })
	@RequestMapping(value = "/org/edit", method = RequestMethod.PUT)
	@ResponseBody
	@LoggerManage(module = "组织管理", description = "", operate = "更新组织")
	public HttpResult update(@RequestBody Org org) {
		boolean success = orgService.updateById(org);
		HttpResult result = HttpResult.ok();
		result.setSuccess(success);
		return result;
	}

	@ApiOperation(value = "根据ID删除机构", notes = "逗号分隔")
	@ApiResponses({ @ApiResponse(code = 200, message = "删除成功") })
	@RequestMapping(value = "/org/del", method = RequestMethod.DELETE)
	@ResponseBody
	@LoggerManage(module = "组织管理", description = "", operate = "删除组织")
	public HttpResult del(@RequestParam("ids") String ids) {
		HttpResult result;
		String[] arr = ids.split(",");
		List<String> list = Arrays.asList(arr);

		QueryWrapper<Org> orgQueryWrapper = new QueryWrapper<Org>();
		orgQueryWrapper.in("parentId", list);
		if (orgService.count(orgQueryWrapper) > 0) {
			result = HttpResult.error("存在下级组织，不可删除");
		} else {
			QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
			userQueryWrapper.in("orgId", list);
			if (userService.count(userQueryWrapper) > 0) {
				result = HttpResult.error("组织中存在用户，不可删除");
			} else {
				boolean success = orgService.removeByIds(list);
				result = HttpResult.ok();
				result.setSuccess(success);
				result.setMsg(success ? "删除成功" : "删除失败");
			}
		}

		return result;
	}

	@ApiOperation(value = "更新机构顺序", notes = "更新机构顺序")
	@ApiResponses({ @ApiResponse(code = 200, message = "更新成功") })
	@RequestMapping(value = "/org/order", method = RequestMethod.PUT)
	@ResponseBody
	@LoggerManage(module = "组织管理", description = "", operate = "更新组织顺序")
	public HttpResult updateOrder(@RequestBody List<String> ids) {
		boolean success = orgService.updateOrder(ids);
		HttpResult result = HttpResult.ok();
		result.setSuccess(success);
		return result;
	}
}
