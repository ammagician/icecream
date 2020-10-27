
package com.apang.icecream.systemmanager.domain.vo;

import com.apang.icecream.core.domain.bo.Tenant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andy.pang
 * @since  2019-09-06
 */
public class TenantVo extends Tenant {

	private List<String> portals = new ArrayList<String>();

	public List<String> getPortals() {
		return portals;
	}

	public void setPortals(List<String> portals) {
		this.portals = portals;
	}
}
