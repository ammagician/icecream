package com.apang.icecream.core.domain.bo;

import com.apang.icecream.core.base.BaseDto;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.UUID;

@TableName("s_role_resource")
public class Permission  extends BaseDto {
	private static final long serialVersionUID = 1L;

	@TableId(value="id",type= IdType.AUTO)
	private Integer id;

	@TableField("roleId")
	private Integer roleId;
	@TableField("resId")
	private Integer resId;
	@TableField("portalId")
	private Integer portalId;  //方便查询

	/**
	 * 类型分为：1：门户 2：portlet 3：目录 4：菜单 5：页面 6：按钮
	 */
	private Integer type;

	public Permission(Integer roleId, Integer resId, Integer portalId, Integer type) {
		//this.id = UUID.randomUUID().toString();
		this.roleId = roleId;
		this.portalId = portalId;
		this.resId = resId;
		this.type = type;
	}

	public Permission() {
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getResId() {
		return resId;
	}

	public void setResId(Integer resId) {
		this.resId = resId;
	}

	public Integer getPortalId() {
		return portalId;
	}

	public void setPortalId(Integer portalId) {
		this.portalId = portalId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
