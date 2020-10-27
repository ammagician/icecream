/*
 * Copyright 2019-2050 Gdczkj.Inc All Rights Reserved.
 */
package com.apang.icecream.core.domain.bo;

import com.apang.icecream.core.base.BaseDto;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.List;

@TableName("s_resource")
public class Portal  extends BaseDto {
	private static final long serialVersionUID = 1L;

	@TableId("id")
    private String id;

    private String code;

    private String name;

    private String remarks;

	@TableField(exist = false)
	private List<Portlet> portletList;  //所有可见portlet

	@TableField(exist = false)
	private List<Menu> menuList;  //所有可见菜单

	@TableField(exist = false)
	private List<String> permissionList; //所有被授权权限的权限代码

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<Portlet> getPortletList() {
		return portletList;
	}

	public void setPortletList(List<Portlet> portletList) {
		this.portletList = portletList;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public List<String> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<String> permissionList) {
		this.permissionList = permissionList;
	}
}
