package com.apang.icecream.systemmanager.controller;


import com.apang.icecream.core.services.ITenantService;
import com.apang.icecream.core.domain.bo.Tenant;
import com.apang.icecream.core.util.ListUtil;
import com.apang.icecream.systemmanager.domain.vo.TenantVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.apang.icecream.core.base.HttpResult;
import com.apang.icecream.core.base.LoggerManage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 租户管理接口
 * </p>
 *
 * @author andy.pang
 * @since 2019-09-06
 */
@RestController
@RequestMapping("/api/sysmgr")
public class TenantController {
	@Autowired
	private ITenantService tenantService;

	@ApiOperation(value = "根据ID查询租户", notes = "根据ID查询租户")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/tenant/detail", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getById(@RequestParam("id") String id) {
		Tenant tenant = tenantService.getDetailById(id);
		HttpResult result = HttpResult.ok();
		result.setData(tenant);
		return result;
	}

	@ApiOperation(value = "根据条件分页查询租户", notes = "根据条件分页查询租户")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/tenant/page", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getPage(int current, int size, Tenant tenant) {
		Page<Tenant> page = new Page<Tenant>(current, size);
		QueryWrapper<Tenant> queryWrapper = new QueryWrapper<Tenant>(tenant);
		if (!StringUtils.isEmpty(tenant.getName())) {
			queryWrapper.like("name", tenant.getName());
		}

		IPage<Tenant> users = tenantService.getPage(page, queryWrapper);

		HttpResult result = HttpResult.ok();
		result.setData(users);
		return result;
	}

	@ApiOperation(value = "新增租户", notes = "新增租户")
	@ApiResponses({ @ApiResponse(code = 200, message = "新增成功") })
	@RequestMapping(value = "/tenant/add", method = RequestMethod.POST)
	@ResponseBody
	@LoggerManage(module = "租户管理", description = "", operate = "新增租户")
	public HttpResult save(@RequestBody Tenant tenant) {
		HttpResult result;
		try {
			tenantService.saveOrUpdateSafely(tenant);
			result = HttpResult.ok();
		} catch (Exception e) {
			result = HttpResult.error("服务器异常");
		}
		return result;
	}

	@ApiOperation(value = "更新租户", notes = "更新租户")
	@ApiResponses({ @ApiResponse(code = 200, message = "更新成功") })
	@RequestMapping(value = "/tenant/edit", method = RequestMethod.PUT)
	@ResponseBody
	@LoggerManage(module = "租户管理", description = "", operate = "更新租户")
	public HttpResult update(@RequestBody Tenant tenant) {
		HttpResult result;
		try {
			tenantService.saveOrUpdateSafely(tenant);
			result = HttpResult.ok();
		} catch (Exception e) {
			result = HttpResult.error("服务器异常");
		}
		return result;

	}

	@ApiOperation(value = "根据ID删除租户", notes = "逗号分隔")
	@ApiResponses({ @ApiResponse(code = 200, message = "删除成功") })
	@RequestMapping(value = "/tenant/del", method = RequestMethod.DELETE)
	@ResponseBody
	@LoggerManage(module = "租户管理", description = "", operate = "删除租户")
	public HttpResult del(@RequestParam("ids") String ids) {
		List<String> list = ListUtil.splitToList(ids, ",");
		boolean success = tenantService.removeByIds(list);

		HttpResult result = HttpResult.ok();
		result.setSuccess(success);

		return result;
	}

}
