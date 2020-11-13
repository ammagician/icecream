package com.apang.icecream.core.domain.bo;

import com.apang.icecream.core.base.BaseDto;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Timestamp;

/**
 * 登录日志实体
 *
 * @author andy.pang
 * @since 2019-09-09
 */
@TableName("s_operate_log")
public class OperateLog extends BaseDto {

	private static final long serialVersionUID = 1L;

	@TableId(value="id",type= IdType.AUTO)
	private Integer Id;

	private String visitor;

	@TableField("ipAddress")
	private String ipAddress;

	@TableField("url")
	private String url;

	@TableField("methodType")
	private String methodType;

	@TableField("moduleName")
	private String moduleName;

	@TableField("operateName")
	private String operateName;

	private String descript;

	@TableField("visitTime")
	private Timestamp visitTime;

	@TableField("sessionID")
	private String sessionID;

	@TableField("tenantId")
	private Integer tenantId;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getVisitor() {
		return visitor;
	}

	public void setVisitor(String visitor) {
		this.visitor = visitor;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethodType() {
		return methodType;
	}

	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public Timestamp getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(Timestamp visitTime) {
		this.visitTime = visitTime;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	@Override
	public String toString() {
		return "OperateLog{" +
				"Id='" + Id + '\'' +
				", visitor='" + visitor + '\'' +
				", ipAddress='" + ipAddress + '\'' +
				", url='" + url + '\'' +
				", methodType='" + methodType + '\'' +
				", moduleName='" + moduleName + '\'' +
				", operateName='" + operateName + '\'' +
				", descript='" + descript + '\'' +
				", visitTime=" + visitTime +
				", sessionID='" + sessionID + '\'' +
				", tenantId='" + tenantId + '\'' +
				'}';
	}
}
