package com.apang.icecream.systemmanager.services;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.apang.icecream.systemmanager.domain.bo.UserBehavior;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author andy.pang
 * @since 2019-09-16
 */
public interface IUserBehaviorService extends IService<UserBehavior> {

	int getViewCount();

	String getCityCount();

	String getProvinceCount();

	String getBrowserCount();

	String getPlatformCount();

	/**
	 * 获得各时段网站的访问量
	 * 
	 * @return
	 * @throws BusinessAccessException
	 */
	@Transactional(readOnly = true)
	public String getTimeCount();

	/**
	 * 获得某天网站的流量统计情况
	 * 
	 * @param days 距离今天的天数
	 * @return
	 */
	@Transactional(readOnly = true)
	public String getDayCount(int days);

	String getTodayViewCount();

	String getTViewCount();

	@Transactional(readOnly = true)
	int getIpCount(String signStr);

	@Transactional(readOnly = true)
	int getUVCount(String signStr);

	@Transactional(readOnly = true)
	int getPVCount(String signStr);

	/**
	 * 统计网站页面访问排行
	 * 
	 * @return
	 * @throws BusinessAccessException
	 */
	@Transactional(readOnly = true)
	public IPage<Map<String, Object>> getPageView(int page, int pageSize);

	/**
	 * 根据name获得访问量等统计数据
	 * 
	 * @param name
	 * @return
	 * @throws BusinessAccessException
	 */
	@Transactional(readOnly = true)
	public String getSystemCount(String name);

	/**
	 * 获得忠诚度相关的访问页数
	 * 
	 * @return
	 * @throws BusinessAccessException
	 */
	@Transactional(readOnly = true)
	public String getVisitPageCount();

	/**
	 * 获得忠诚度相关的访问时间
	 * 
	 * @return
	 * @throws BusinessAccessException
	 */
	@Transactional(readOnly = true)
	public String getVisitTimeCount();

	/**
	 * 统计城市访问数前十
	 * 
	 * @return
	 * @throws BusinessAccessException
	 */
	// @Transactional(readOnly = true)
	// public Pageable<Map<String, Object>> getCityCountTop(QueryFilter queryFilter,
	// int page, int pageSize);

	/*
	 * 获得个人查询热词
	 */
	// @Transactional(readOnly = true)
	// public List<Map<Object, Object>> getquery(String cookiename, QueryFilter
	// queryFilter);

	/*
	 * 获得整体查询热词
	 */
	// @Transactional(readOnly = true)
	// public List<Map<Object, Object>> getpublicquery(QueryFilter queryFilter);

	@Transactional(readOnly = true)
	public List<Map<Object, Object>> getkeywords(String num, String cname);

	/**
	 * 获得统计各省份搜索热词
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getCityWord();

	@Transactional(readOnly = true)
	public List<Map<Object, Object>> getkeywordsall(String num);

	@Transactional(readOnly = true)
	public List<Map<Object, Object>> singleKeywords(String name, String id);

}
