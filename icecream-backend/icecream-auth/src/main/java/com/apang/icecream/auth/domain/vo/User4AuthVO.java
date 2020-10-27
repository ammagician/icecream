
package com.apang.icecream.auth.domain.vo;

import com.apang.icecream.core.domain.bo.Org;

import java.io.Serializable;

public class User4AuthVO implements Serializable {
    private String id;
    private String loginName;
	private String password;
    private String userName;
    private long isDisabled;
	private String avatar;
	private String tenantId;

    private Org Org;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public long getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(long isDisabled) {
        this.isDisabled = isDisabled;
    }

	public Org getOrg() {
		return Org;
	}

	public void setOrg(Org org) {
		Org = org;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
