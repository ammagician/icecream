package com.apang.icecream.systemmanager.controller;


import java.sql.Timestamp;

import com.apang.icecream.core.services.IOperateLogService;
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
import com.apang.icecream.core.domain.bo.OperateLog;
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
public class OperateLogController {

	@Autowired
	private IOperateLogService opeLogService;

	@ApiOperation(value = "根据条件分页查询", notes = "根据条件分页查询")
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功") })
	@RequestMapping(value = "/operatelog/page", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getPage(int current, int size, String userName, Long start, Long end) {
		OrderItem order = new OrderItem();
		order.setColumn("visitTime");
		order.setAsc(false);
		Page<OperateLog> page = new Page<OperateLog>(current, size);
		page.addOrder(order);

		OperateLog log = new OperateLog();

		QueryWrapper<OperateLog> queryWrapper = new QueryWrapper<OperateLog>(log);
		if (start != null && end != null) {
			queryWrapper.between("visitTime", new Timestamp(start), new Timestamp(end));
		}
		
		if (!StringUtils.isEmpty(userName)) {
			queryWrapper.like("visitor", userName);
		}
		IPage<OperateLog> logs = opeLogService.page(page, queryWrapper);

		HttpResult result = HttpResult.ok();
		result.setData(logs);
		return result;
	}

}
