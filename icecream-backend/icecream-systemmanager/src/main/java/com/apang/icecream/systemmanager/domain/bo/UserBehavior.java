package com.apang.icecream.systemmanager.domain.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.apang.icecream.core.base.BaseDto;

/**
 * <p>
 * 
 * </p>
 *
 * @author andy.pang
 * @since 2019-09-09
 */
@TableName("s_behavior_log")
public class UserBehavior extends BaseDto {

	@TableId(value="id",type= IdType.AUTO)
	private Integer id;

    private String visitor;

	@TableField("browserName")
	private String browserName;
	@TableField("browserVersion")
	private String browserVersion;
	@TableField("browserPlatform")
	private String browserPlatform;
	private String screen;
	private String device;

	@TableField("accessIp")
	private String accessIp;
	@TableField("accessCname")
	private String accessCname;
	@TableField("accessCity")
	private String accessCity;
	@TableField("ipSupplier")
	private String ipSupplier;
	@TableField("fromUrl")
	private String fromUrl;
	@TableField("accessUrl")
	private String accessUrl;
	@TableField("accessTimeIn")
	private String accessTimeIn;
	@TableField("accessTimeOut")
	private String accessTimeOut;
	@TableField("accessTime")
	private String accessTime;
	@TableField("actionName")
	private String actionName;
	@TableField("actionTime")
	private String actionTime;

	private int type = 1;

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		id = id;
	}

	public String getVisitor() {
		return visitor;
	}

	public void setVisitor(String visitor) {
		this.visitor = visitor;
	}

	public String getBrowserName() {
		return browserName;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	public String getBrowserVersion() {
		return browserVersion;
	}

	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}

	public String getBrowserPlatform() {
		return browserPlatform;
	}

	public void setBrowserPlatform(String browserPlatform) {
		this.browserPlatform = browserPlatform;
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getAccessIp() {
		return accessIp;
	}

	public void setAccessIp(String accessIp) {
		this.accessIp = accessIp;
	}

	public String getAccessCname() {
		return accessCname;
	}

	public void setAccessCname(String accessCname) {
		this.accessCname = accessCname;
	}

	public String getAccessCity() {
		return accessCity;
	}

	public void setAccessCity(String accessCity) {
		this.accessCity = accessCity;
	}

	public String getIpSupplier() {
		return ipSupplier;
	}

	public void setIpSupplier(String ipSupplier) {
		this.ipSupplier = ipSupplier;
	}

	public String getFromUrl() {
		return fromUrl;
	}

	public void setFromUrl(String fromUrl) {
		this.fromUrl = fromUrl;
	}

	public String getAccessUrl() {
		return accessUrl;
	}

	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}

	public String getAccessTimeIn() {
		return accessTimeIn;
	}

	public void setAccessTimeIn(String accessTimeIn) {
		this.accessTimeIn = accessTimeIn;
	}

	public String getAccessTimeOut() {
		return accessTimeOut;
	}

	public void setAccessTimeOut(String accessTimeOut) {
		this.accessTimeOut = accessTimeOut;
	}

	public String getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(String accessTime) {
		this.accessTime = accessTime;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getActionTime() {
		return actionTime;
	}

	public void setActionTime(String actionTime) {
		this.actionTime = actionTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "UserOperate [Id=" + id + ", visitor=" + visitor + ", browserName=" + browserName + ", browserVersion="
				+ browserVersion + ", browserPlatform=" + browserPlatform + ", screen=" + screen + ", device=" + device
				+ ", accessIp=" + accessIp + ", accessCname=" + accessCname + ", accessCity=" + accessCity
				+ ", ipSupplier=" + ipSupplier + ", fromUrl=" + fromUrl + ", accessUrl=" + accessUrl + ", accessTimeIn="
				+ accessTimeIn + ", accessTimeOut=" + accessTimeOut + ", accessTime=" + accessTime + ", actionName="
				+ actionName + ", actionTime=" + actionTime + ", type=" + type + "]";
	}

}
