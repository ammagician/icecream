package com.apang.icecream.systemmanager.controller;


import com.apang.icecream.core.domain.bo.Portal;
import com.apang.icecream.systemmanager.domain.bo.UserSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.apang.icecream.auth.utils.AuthenticationUtil;
import com.apang.icecream.systemmanager.services.IUserSettingService;
import com.apang.icecream.core.base.HttpResult;
import com.apang.icecream.core.base.LoggerManage;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author andy.pang
 * @since 2019-09-16
 */
@RestController
@RequestMapping("/api/sysmgr")
public class UserSettingController {
	@Autowired
	private IUserSettingService userSettingService;

	@ApiOperation(value = "根据ID查询用户设置", notes = "根据ID查询用户设置")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/user/setting", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getById() {
		String userId = AuthenticationUtil.getUserId();
		HttpResult result = HttpResult.ok();
		if (!StringUtils.isEmpty(userId)) {
			UserSetting setting = userSettingService.getById(userId);
			List<Portal> portals = AuthenticationUtil.getUser().getPortalList();
			if(setting == null && portals.size() > 0){
				setting = new UserSetting();
				setting.setId(Integer.parseInt(userId));
				setting.setCurrentPortal(portals.get(0).getId());
				setting.setTheme("{\"navTheme\":\"dark\",\"primaryColor\":\"#52C41A\",\"layout\":\"sidemenu\",\"contentWidth\":\"Fluid\",\"fixedHeader\":true,\"autoHideHeader\":false,\"fixSiderbar\":false,\"colorWeak\":false}");
			}
			result.setData(setting);
		} else {
			result = HttpResult.error(401, "用户没有登录");
		}

		return result;
	}

	@ApiOperation(value = "更新用户设置", notes = "更新用户设置")
	@ApiResponses({ @ApiResponse(code = 200, message = "更新成功") })
	@RequestMapping(value = "/user/setting", method = RequestMethod.PUT)
	@ResponseBody
	@LoggerManage(module = "用户信息管理", description = "", operate = "更新用户个人设置")
	public HttpResult update(@RequestBody UserSetting setting) {
		String userId = AuthenticationUtil.getUserId();
		HttpResult result = HttpResult.ok();
		if (!StringUtils.isEmpty(userId)) {
			setting.setId(Integer.parseInt(userId));
			boolean success = userSettingService.saveOrUpdate(setting);
			result.setData(success);
			result.setMsg(success ? "成功" : "失败");
		} else {
			result = HttpResult.error(401, "用户没有登录");
		}

		return result;
	}
}
