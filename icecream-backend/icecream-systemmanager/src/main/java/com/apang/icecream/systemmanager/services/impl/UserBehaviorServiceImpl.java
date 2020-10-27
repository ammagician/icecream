package com.apang.icecream.systemmanager.services.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.apang.icecream.systemmanager.domain.bo.UserBehavior;
import com.apang.icecream.systemmanager.domain.vo.ReturnView;
import com.apang.icecream.systemmanager.mapper.UserBehaviorMapper;
import com.apang.icecream.systemmanager.services.IUserBehaviorService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author andy.pang
 * @since 2019-09-16
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserBehaviorServiceImpl extends ServiceImpl<UserBehaviorMapper, UserBehavior>
		implements IUserBehaviorService {

	@Autowired
	private SqlSessionFactory sessionFactory;

	private UserBehaviorMapper getMapper() {
		UserBehaviorMapper mapper = sessionFactory.openSession().getMapper(UserBehaviorMapper.class);
		return mapper;
	}


	@Override
	public int getViewCount() {
		return getMapper().getViewCount();
	}

	@Override
	public String getCityCount() {
		List<Map<String, Object>> list = getMapper().getCityCount();
		return JSONObject.toJSONString(list);
	}

	@Override
	public String getProvinceCount() {
		List<Map<String, Object>> list = getMapper().getProvinceCount();
		return JSONObject.toJSONString(list);
	}

	@Override
	public String getBrowserCount() {
		List<Map<String, Object>> list = getMapper().getBrowserCount();
		return JSONObject.toJSONString(list);
	}

	@Override
	public String getPlatformCount() {
		List<Map<String, Object>> list = getMapper().getPlatformCount();
		return JSONObject.toJSONString(list);
	}

	@Override
	public String getTimeCount() {
		List<Map<String, Object>> list = getMapper().getTimeCount(new Date());
		return JSONObject.toJSONString(list);
	}

	@Override
	public String getDayCount(int days) {
		List<Map<String, Object>> list = getMapper().getDayCount(days);
		return JSONObject.toJSONString(list);
	}

	@Override
	public String getTodayViewCount() {
		int todayViewCount = getMapper().getTodayViewCount();
		int todayUserCount = getMapper().getTodayUserCount();
		int todayIpCount = getMapper().getTodayIpCount();
		ReturnView returnView1 = new ReturnView("今日", todayViewCount, todayUserCount, todayIpCount);

		int yestodayViewCount = getMapper().getYestodayViewCount();
		int yestodayUserCount = getMapper().getYestodayUserCount();
		int yestodayIpCount = getMapper().getYestodayIpCount();
		ReturnView returnView2 = new ReturnView("昨日", yestodayViewCount, yestodayUserCount, yestodayIpCount);

		int yestodayNowViewCount = getMapper().getYestodayNowViewCount();
		int yestodayNowUserCount = getMapper().getYestodayNowUserCount();
		int yestodayNowIpCount = getMapper().getYestodayNowIpCount();
		ReturnView returnView3 = new ReturnView("昨日此时", yestodayNowViewCount, yestodayNowUserCount, yestodayNowIpCount);

		int averageViewCount = getMapper().getAverageViewCount();
		int averageUserCount = getMapper().getAverageUserCount();
		int averageIpCount = getMapper().getAverageIpCount();
		ReturnView returnView4 = new ReturnView("每日平均", averageViewCount, averageUserCount, averageIpCount);

		int topViewCount = getMapper().getTopViewCount();
		int topUserCount = getMapper().getTopUserCount();
		int topIpCount = getMapper().getTopIpCount();
		ReturnView returnView5 = new ReturnView("历史峰值", topViewCount, topUserCount, topIpCount);

		List<ReturnView> list = new ArrayList<>();
		list.add(returnView1);
		list.add(returnView2);
		list.add(returnView3);
		list.add(returnView4);
		list.add(returnView5);
		return JSONObject.toJSONString(list);
	}

	/*
	 * ip数量统计
	 * 
	 */
	@Override
	public int getIpCount(String signStr) {
		return getMapper().getIpCount(signStr);
	}

	/*
	 * 访客数统计
	 */
	@Override
	public int getUVCount(String signStr) {
		return getMapper().getUVCount(signStr);
	}

	/*
	 * 浏览量统计
	 */
	@Override
	public int getPVCount(String signStr) {
		return getMapper().getPVCount(signStr);
	}

	@Override
	public IPage<Map<String, Object>> getPageView(int page, int pageSize) {
		int length = getMapper().getPageViewCount();
		Page<Map<String, Object>> pageData = new Page<Map<String, Object>>(page, pageSize, length);
		if (length > 0) {
			List<Map<String, Object>> list = getMapper().getPageView((page - 1) * pageSize, pageSize);
			pageData.setRecords(list);
		}

		return pageData;
	}

	@Override
	public String getTViewCount() {
		List<Map<String, Object>> list = getMapper().getTViewCount();
		return JSONObject.toJSONString(list);
	}

	@Override
	public String getSystemCount(String name) {
		List<Map<String, Object>> list = getMapper().getSystemCount(name);
		return JSONObject.toJSONString(list);
	}

	@Override
	public String getVisitPageCount() {
		List<Map<String, Object>> list = getMapper().getVisitPageCount();
		calcPer(list);

		return JSONObject.toJSONString(list);
	}

	@Override
	public String getVisitTimeCount() {
		List<Map<String, Object>> list = getMapper().getVisitTimeCount();
		calcPer(list);
		return JSONObject.toJSONString(list);
	}

	private void calcPer(List<Map<String, Object>> list) {
		long sum = 0;
		for (Map<String, Object> item : list) {
			if (item.get("NAME").equals("当前汇总")) {
				sum = (long) item.get("VIEWCOUNT");
			}
		}

		for (Map<String, Object> item : list) {
			long v = (long) item.get("VIEWCOUNT");
			item.put("PER", new DecimalFormat("0.00").format(100f * v / sum) + "%");
		}
	}

//	@Override
//	public Pageable<Map<String, Object>> getCityCountTop(QueryFilter filter, int page, int pageSize) {
//		this.validatePageSize(pageSize);
//		int length = getMapper().getCityCountTopCount(filter);
//		page = DaoUtils.realPage(page, pageSize, length);
//		return length == 0 ? new Pageable<Map<String, Object>>(page, pageSize, length, Collections.emptyList())
//				: new Pageable<Map<String, Object>>(page, pageSize, length,
//						getMapper().getCityCountTop(filter, new Page((page - 1) * pageSize, pageSize)));
//	}

//	@Override
//	public List<Map<Object, Object>> getquery(String cookiename, QueryFilter filter) {
//		// TODO Auto-generated method stub
//		return getMapper().getquery(cookiename, filter);
//	}
//
//	@Override
//	public List<Map<Object, Object>> getpublicquery(QueryFilter filter) {
//		// TODO Auto-generated method stub
//		return getMapper().getpublicquery(filter);
//	}

	@Override
	public List<Map<Object, Object>> getkeywords(String num, String cname) {
		// TODO Auto-generated method stub
		return getMapper().getkeywords(num, cname);
	}

	@Override
	public List<Map<String, Object>> getCityWord() {
		return getMapper().getCityWord();
	}

	@Override
	public List<Map<Object, Object>> getkeywordsall(String num) {
		// TODO Auto-generated method stub
		return getMapper().getkeywordsall(num);
	}

	@Override
	public List<Map<Object, Object>> singleKeywords(String name, String id) {
		// TODO Auto-generated method stub
		return getMapper().singleKeywords(name, id);
	}

}
