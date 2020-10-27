package com.apang.icecream.systemmanager.controller;


import java.sql.Timestamp;

import com.apang.icecream.core.services.ILoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.apang.icecream.core.domain.bo.LoginLog;
import com.apang.icecream.core.base.HttpResult;

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
public class LoginLogController {

	@Autowired
	private ILoginLogService loginOutLogService;

	@ApiOperation(value = "根据条件分页查询", notes = "根据条件分页查询")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/loginlog/page", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getPage(int current, int size, String userName, Long start, Long end) {
		OrderItem order = new OrderItem();
		order.setColumn("loginTime");
		order.setAsc(false);

		Page<LoginLog> page = new Page<LoginLog>(current, size);
		page.addOrder(order);

		LoginLog log = new LoginLog();

		QueryWrapper<LoginLog> queryWrapper = new QueryWrapper<LoginLog>(log);

		if (start != null && end != null) {
			queryWrapper.between("loginTime", new Timestamp(start), new Timestamp(end));

		}
		if (!StringUtils.isEmpty(userName)) {
			queryWrapper.like("visitor", userName);
		}

		IPage<LoginLog> logs = loginOutLogService.page(page, queryWrapper);

		HttpResult result = HttpResult.ok();
		result.setData(logs);
		return result;
	}

}
