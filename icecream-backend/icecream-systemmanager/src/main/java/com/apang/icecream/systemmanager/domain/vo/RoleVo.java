
package com.apang.icecream.systemmanager.domain.vo;

import com.apang.icecream.core.domain.bo.Permission;
import com.apang.icecream.core.domain.bo.Resource;
import com.apang.icecream.core.domain.bo.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andy.pang
 * @since  2019-09-06
 */
public class RoleVo extends Role {

	private String portalId;

	private List<Resource> portalList = new ArrayList<Resource>();

	private List<Permission> permissions = new ArrayList<Permission>();

	public List<Resource> getPortalList() {
		return portalList;
	}

	public void setPortalList(List<Resource> portalList) {
		this.portalList = portalList;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public String getPortalId() {
		return portalId;
	}

	public void setPortalId(String portalId) {
		this.portalId = portalId;
	}
}
