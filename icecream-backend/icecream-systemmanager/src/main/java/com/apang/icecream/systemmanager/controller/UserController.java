package com.apang.icecream.systemmanager.controller;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.apang.icecream.core.domain.bo.User;
import com.apang.icecream.core.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
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
import com.apang.icecream.auth.domain.vo.AuthUserDetailVO;
import com.apang.icecream.auth.utils.AuthenticationUtil;
import com.apang.icecream.auth.utils.EncodeUtil;
import com.apang.icecream.systemmanager.domain.vo.UserVo;
import com.apang.icecream.config.UserProperty;
import com.apang.icecream.core.base.HttpResult;
import com.apang.icecream.core.base.LoggerManage;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * <p>
 * 用户管理接口
 * </p>
 *
 * @author andy.pang
 * @since 2019-09-06
 */
@RestController
@RequestMapping("/api/sysmgr")
public class UserController {
	@Autowired
	private UserProperty userProperty;

	@Autowired
	private IUserService userService;

	@ApiOperation(value = "根据ID查询用户", notes = "根据ID查询用户")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/user/current", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getCurrent() {
		AuthUserDetailVO user = AuthenticationUtil.getUser();

		HttpResult result = HttpResult.ok();
		if (user != null) {
			User data = userService.getDetailById(user.getUser4AuthVO().getId());
			result.setData(data);
		} else {
			result = HttpResult.error(401, "用户没有登录");
		}

		return result;
	}

	@ApiOperation(value = "根据ID查询用户", notes = "根据ID查询用户")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/user/detail", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getById(@RequestParam("id") String id) {
		User user = userService.getDetailById(id);
		HttpResult result = HttpResult.ok();
		result.setData(user);
		return result;
	}

	@ApiOperation(value = "根据条件查询用户", notes = "根据条件查询用户")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/user/list", method = RequestMethod.POST)
	@ResponseBody
	public HttpResult getList(User user) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		if (!StringUtils.isEmpty(user.getUserName())) {
			queryWrapper.like("userName", user.getUserName());
		}

		if (!StringUtils.isEmpty(user.getOrgId())) {
			queryWrapper.eq("orgId", user.getOrgId());
		}

		if (user.getIsDisabled() != null) {
			queryWrapper.eq("isDisabled", user.getIsDisabled());
		}

		queryWrapper.eq("tenantId", AuthenticationUtil.getTenantId());

		List<User> users = userService.getList(queryWrapper);
		HttpResult result = HttpResult.ok();
		result.setData(users);
		return result;
	}

	@ApiOperation(value = "根据条件分页查询用户", notes = "根据条件分页查询用户")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/user/page", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getPage(int current, int size, User user) {
		Page<User> page = new Page<User>(current, size);
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>(user);
		if (!StringUtils.isEmpty(user.getUserName())) {
			queryWrapper.like("userName", user.getUserName());
		}

		if (!StringUtils.isEmpty(user.getOrgId())) {
			queryWrapper.like("orgId", user.getOrgId());
		}

		if (user.getIsDisabled() != null) {
			queryWrapper.eq("isDisabled", user.getIsDisabled());
		}

		queryWrapper.eq("tenantId", AuthenticationUtil.getTenantId());

		IPage<User> users = userService.getPage(page, queryWrapper);

		HttpResult result = HttpResult.ok();
		result.setData(users);
		return result;
	}

	@ApiOperation(value = "新增用户", notes = "新增用户")
	@ApiResponses({ @ApiResponse(code = 200, message = "新增成功") })
	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	@ResponseBody
	@LoggerManage(module = "用户管理", description = "", operate = "新增用户")
	public HttpResult save(@RequestBody UserVo user) {
		String tenantId = AuthenticationUtil.getTenantId();
		String id = UUID.randomUUID().toString();
		user.setId(id);
		HttpResult result;
		try {
			QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
			queryWrapper.eq("loginName", user.getLoginName());
			queryWrapper.eq("tenantId", tenantId);
			if (userService.getOne(queryWrapper) != null) {
				return HttpResult.error("该登录名称已经存在");
			}

			byte[] pwd = userProperty.getDefaultPassword().getBytes();
			String pwdStr = DigestUtils.md5DigestAsHex(pwd);
			user.setPassword(EncodeUtil.encode(pwdStr));
			user.setTenantId(tenantId);
			userService.saveOrUpdateSafely(user);

			result = HttpResult.ok();
		} catch (Exception e) {
			result = HttpResult.error("服务器异常");
		}
		return result;
	}

	@ApiOperation(value = "禁用锁定用户", notes = "禁用锁定用户")
	@ApiResponses({ @ApiResponse(code = 200, message = "操作成功") })
	@RequestMapping(value = "/user/toggle", method = RequestMethod.PUT)
	@ResponseBody
	@LoggerManage(module = "用户管理", description = "", operate = "禁用锁定用户")
	public HttpResult toggle(@RequestBody UserVo user) {
		boolean success = userService.updateById(user);
		HttpResult result = HttpResult.ok();
		result.setSuccess(success);
		return result;
	}

	@ApiOperation(value = "更新用户", notes = "更新用户")
	@ApiResponses({ @ApiResponse(code = 200, message = "更新成功") })
	@RequestMapping(value = "/user/edit", method = RequestMethod.PUT)
	@ResponseBody
	@LoggerManage(module = "用户管理", description = "", operate = "更新用户")
	public HttpResult update(@RequestBody UserVo user) {
		HttpResult result;
		try {
			userService.saveOrUpdateSafely(user);
			result = HttpResult.ok();
		} catch (Exception e) {
			result = HttpResult.error("服务器异常");
		}
		return result;

	}

	@ApiOperation(value = "更新用户密码", notes = "更新用户密码")
	@ApiResponses({ @ApiResponse(code = 200, message = "更新成功") })
	@RequestMapping(value = "/user/updatePwd", method = RequestMethod.PUT)
	@ResponseBody
	@LoggerManage(module = "用户管理", description = "", operate = "更新用户密码")
	public HttpResult updatePwd(@RequestBody UserVo user) {
		HttpResult result;
		try {
			String pwd = user.getPassword();
			if (pwd == null || "".equals(pwd.trim())) {
				pwd = DigestUtils.md5DigestAsHex(userProperty.getDefaultPassword().getBytes());
			}

			user.setPassword(EncodeUtil.encode(pwd));

			userService.saveOrUpdateSafely(user);
			result = HttpResult.ok();
		} catch (Exception e) {
			result = HttpResult.error("服务器异常");
		}
		return result;

	}

	@ApiOperation(value = "根据ID删除用户", notes = "逗号分隔")
	@ApiResponses({ @ApiResponse(code = 200, message = "删除成功") })
	@RequestMapping(value = "/user/del", method = RequestMethod.DELETE)
	@ResponseBody
	@LoggerManage(module = "用户管理", description = "", operate = "删除用户")
	public HttpResult del(@RequestParam("ids") String ids) {

		String[] arr = ids.split(",");
		List<String> list = Arrays.asList(arr);
		boolean success = userService.removeByIds(list);

		HttpResult result = HttpResult.ok();
		result.setSuccess(success);

		return result;
	}

}
