package com.apang.icecream.core.domain.bo;

import com.apang.icecream.core.base.BaseDto;
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
@TableName("s_login_log")
public class LoginLog  extends BaseDto {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    private String visitor;

    @TableField("ipAddress")
    private String ipAddress;

    @TableField("loginTime")
    private Timestamp loginTime;

    @TableField("logoutTime")
    private Timestamp logoutTime;

    @TableField("sessionID")
    private String sessionID;

    @TableField("tenantId")
    private String tenantId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public Timestamp getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Timestamp logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String toString() {
        return "LoginLog{" +
                "id='" + id + '\'' +
                ", visitor='" + visitor + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", loginTime=" + loginTime +
                ", logoutTime=" + logoutTime +
                ", sessionID='" + sessionID + '\'' +
                ", tenantId='" + tenantId + '\'' +
                '}';
    }
}
