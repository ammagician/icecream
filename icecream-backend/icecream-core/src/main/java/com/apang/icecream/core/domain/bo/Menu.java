/*
 * Copyright 2019-2050 Gdczkj.Inc All Rights Reserved.
 */
package com.apang.icecream.core.domain.bo;

import com.apang.icecream.core.base.BaseDto;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;


@TableName("s_resource")
public class Menu  extends BaseDto {
	private static final long serialVersionUID = 1L;

	@TableId(value="id",type= IdType.AUTO)
    private String id;
	@TableField("parentId")
    private String parentId;
	@TableField("portalId")
	private String portalId;

    private String name;

    private String code;

    private String url;

    private String icon;

    private String type;

    private String remarks;
	@TableField("orderNo")
	private String orderNo;

	@TableField("relationiD")
	private String relationiD;

	@TableField(exist = false)
	private List<Menu> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getPortalId() {
		return portalId;
	}

	public void setPortalId(String portalId) {
		this.portalId = portalId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public List<Menu> getChildren() {
		return "4".equals(type) ? null : children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	public String getRelationiD() {
		return relationiD;
	}

	public void setRelationiD(String relationiD) {
		this.relationiD = relationiD;
	}
}
