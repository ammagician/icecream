package com.apang.icecream.core.domain.bo;

import com.apang.icecream.core.base.BaseDto;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.UUID;

@TableName("s_role_resource")
public class Permission  extends BaseDto {
	private static final long serialVersionUID = 1L;

	@TableId("id")
	private String id;

	@TableField("roleId")
	private String roleId;
	@TableField("resId")
	private String resId;
	@TableField("portalId")
	private String portalId;  //方便查询

	/**
	 * 类型分为：1：门户 2：portlet 3：目录 4：菜单 5：页面 6：按钮
	 */
	private Integer type;

	public Permission(String roleId, String resId, String portalId, Integer type) {
		this.id = UUID.randomUUID().toString();
		this.roleId = roleId;
		this.portalId = portalId;
		this.resId = resId;
		this.type = type;
	}

	public Permission() {
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public String getPortalId() {
		return portalId;
	}

	public void setPortalId(String portalId) {
		this.portalId = portalId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
