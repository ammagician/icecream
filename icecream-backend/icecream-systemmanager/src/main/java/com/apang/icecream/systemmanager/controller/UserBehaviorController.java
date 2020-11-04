package com.apang.icecream.systemmanager.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.apang.icecream.systemmanager.domain.bo.UserBehavior;
import com.apang.icecream.systemmanager.services.IUserBehaviorService;
import com.apang.icecream.core.base.HttpResult;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 用户行为分析
 *
 * @author andy.pang
 * @since 2019-09-06
 */
@RestController
@RequestMapping("/api/sysmgr")
public class UserBehaviorController {

	@Autowired
	private IUserBehaviorService userBehaviorService;

	@ApiOperation(value = "提交用户行为信息", notes = "提交用户行为信息")
	@ApiResponses({ @ApiResponse(code = 200, message = "更新成功") })
	@RequestMapping(value = "/allow/behavior", method = RequestMethod.POST)
	@ResponseBody
	public HttpResult update(@RequestBody List<UserBehavior> behaviors) {
		HttpResult result = HttpResult.ok();

		boolean success = userBehaviorService.saveBatch(behaviors);
		result.setData(success);
		result.setMsg(success ? "成功" : "失败");

		return result;
	}

	@ApiOperation(value = "查询实时访问数据", notes = "查询实时访问数据")
	@ApiResponses({ @ApiResponse(code = 200, message = "更新成功") })
	@RequestMapping(value = "/getTimeCount", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getTimeCount() {
		HttpResult result = HttpResult.ok();

		String data = userBehaviorService.getTimeCount();
		result.setData(JSONObject.parse(data));

		return result;
	}

	@ApiOperation(value = "查询访问概况", notes = "查询访问概况")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/getTodayViewCount", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getTodayViewCount() {
		HttpResult result = HttpResult.ok();

		String data = userBehaviorService.getTodayViewCount();
		result.setData(JSONObject.parse(data));

		return result;
	}

	@ApiOperation(value = "查询N天前访问情况", notes = "查询N天前访问情况")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/getdaycount", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getdaycount(int day) {
		HttpResult result = HttpResult.ok();

		String data = userBehaviorService.getDayCount(day);
		result.setData(JSONObject.parse(data));

		return result;
	}

	@ApiOperation(value = "分页查询受访页面", notes = "分页查询受访页面")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/getPageView", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getPageView(int current, int size) {
		IPage<Map<String, Object>> pageData = userBehaviorService.getPageView(current, size);
		HttpResult result = HttpResult.ok();
		result.setData(pageData);
		return result;
	}

	@ApiOperation(value = "系统环境", notes = "系统环境")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/getSystemCount", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getSystemCount(String name) {
		Map<String, String> namesMap = new HashMap<String, String>();
		namesMap.put("browser", "browserName");
		namesMap.put("device", "device");
		namesMap.put("screen", "screen");
		namesMap.put("ipSupplier", "ipSupplier");

		String data = userBehaviorService.getSystemCount(namesMap.get(name));
		HttpResult result = HttpResult.ok();
		result.setData(JSONObject.parse(data));
		return result;
	}

	@ApiOperation(value = "忠诚度,访问页数", notes = "忠诚度,访问页数")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/getVisitPageCount", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getVisitPageCount() {
		String data = userBehaviorService.getVisitPageCount();
		HttpResult result = HttpResult.ok();
		result.setData(JSONObject.parse(data));
		return result;
	}

	@ApiOperation(value = "忠诚度,访问时长", notes = "忠诚度,访问时长")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/getVisitTimeCount", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getVisitTimeCount() {
		String data = userBehaviorService.getVisitTimeCount();
		HttpResult result = HttpResult.ok();
		result.setData(JSONObject.parse(data));
		return result;
	}

	@ApiOperation(value = "用户分布", notes = "用户分布")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/getProvinceCount", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getProvinceCount() {
		String data = userBehaviorService.getProvinceCount();
		HttpResult result = HttpResult.ok();
		result.setData(JSONObject.parse(data));
		return result;
	}


}
