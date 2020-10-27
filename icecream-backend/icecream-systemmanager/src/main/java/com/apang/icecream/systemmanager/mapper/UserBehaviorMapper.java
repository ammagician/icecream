package com.apang.icecream.systemmanager.mapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.apang.icecream.systemmanager.domain.bo.UserBehavior;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author andy.pang
 * @since 2019-09-16
 */
public interface UserBehaviorMapper extends BaseMapper<UserBehavior> {

	int getViewCount();

	List<Map<String, Object>> getCityCount();

	List<Map<String, Object>> getProvinceCount();

	List<Map<String, Object>> getBrowserCount();

	List<Map<String, Object>> getPlatformCount();

	List<Map<String, Object>> getTViewCount();

	/**
	 * 获得各时段网站的访问量
	 * 
	 * @param theDate
	 * @return
	 */
	List<Map<String, Object>> getTimeCount(@Param("theDate") Date theDate);

	/**
	 * 获得某天网站的流量统计情况
	 * 
	 * @param days 距离今天的天数
	 * @return
	 */
	List<Map<String, Object>> getDayCount(@Param("days") int days);

	/**
	 * ip数量统计
	 * 
	 * @param signStr
	 * @return
	 */
	int getIpCount(@Param("signStr") String signStr);

	/**
	 * 浏览量统计
	 * 
	 * @param signStr
	 * @return
	 */
	int getPVCount(@Param("signStr") String signStr);

	/**
	 * 访客数统计
	 * 
	 * @param signStr
	 * @return
	 */
	int getUVCount(@Param("signStr") String signStr);

	int getTodayViewCount();

	int getTodayUserCount();

	int getTodayIpCount();

	int getYestodayUserCount();

	int getYestodayViewCount();

	int getYestodayIpCount();

	int getYestodayNowUserCount();

	int getYestodayNowViewCount();

	int getYestodayNowIpCount();

	int getAverageUserCount();

	int getAverageViewCount();

	int getAverageIpCount();

	int getTopViewCount();

	int getTopUserCount();

	int getTopIpCount();

	HashMap<String, Object> getTodayHourViewCount();

	HashMap<String, Object> getYestodayHourViewCount();

	HashMap<String, Object> getLastWeekHourViewCount();

	/**
	 * 统计网站页面访问排行分页集合
	 * 
	 * @param page
	 * @return
	 */
	List<Map<String, Object>> getPageView(@Param("from") int from, @Param("size") int size);

	/**
	 * 获得统计网站页面访问排行条数
	 * 
	 * @return
	 */
	// @MapKey("count")
	int getPageViewCount();

	/**
	 * 根据name获得访问量等统计数据
	 * 
	 * @param name
	 * @return
	 */
	List<Map<String, Object>> getSystemCount(@Param("name") String name);

	/**
	 * 获得忠诚度相关的访问页数
	 * 
	 * @return
	 */
	List<Map<String, Object>> getVisitPageCount();

	/**
	 * 获得忠诚度相关的访问时间
	 * 
	 * @return
	 */
	List<Map<String, Object>> getVisitTimeCount();

	/**
	 * 统计城市访问数排行分页集合
	 * 
	 * @param page
	 * @return
	 */
	// List<Map<String, Object>> getCityCountTop(@Param("query") QueryFilter arg0,
	// @Param("page") Page page);

	/**
	 * 获得统计城市访问数排行条数
	 * 
	 * @param arg0
	 * @return
	 */
	// @MapKey("count")
	// int getCityCountTopCount(@Param("query") QueryFilter arg0);

	// List<Map<Object, Object>> getquery(@Param("cookiename") String cookiename,
	// @Param("query") QueryFilter filter);

	// List<Map<Object, Object>> getpublicquery(@Param("query") QueryFilter filter);

	List<Map<Object, Object>> getkeywords(@Param("num") String num, @Param("cname") String cname);

	/**
	 * 获得统计各省份搜索热词
	 * 
	 * @return
	 */
	List<Map<String, Object>> getCityWord();

	List<Map<Object, Object>> getkeywordsall(String num);

	List<Map<Object, Object>> singleKeywords(@Param("name") String name, @Param("id") String id);

}
