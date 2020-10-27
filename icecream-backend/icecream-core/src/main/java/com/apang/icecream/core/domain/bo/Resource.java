package com.apang.icecream.core.domain.bo;

import com.apang.icecream.core.base.BaseDto;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author andy.pang
 * @since 2019-09-09
 */
@TableName("s_resource")
public class Resource extends BaseDto {
	private static final long serialVersionUID = 1L;

    @TableId("id")
    private String Id;

    @TableField("parentId")
    private String parentId;

	@TableField(exist = false)
	private String parentName;

	@TableField("portalId")
	private String portalId;

	@TableField(exist = false)
	private String portalName;

    private String name;

    private String code;

	private String icon;

    private String url;

    /**
	 * 类型分为：1：门户 2：portlet 3：目录 4：菜单 5：页面 6：按钮
	 */
    private Integer type;

    private String remarks;

	@TableField("orderNo")
	private Integer orderNo;

	@TableField("relationId")
	private String relationId;   //关联了哪个资源，比如菜单关联页面

	@TableField(exist = false)
	private List<Resource> children = new ArrayList<Resource>();

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getPortalId() {
		return portalId;
	}

	public void setPortalId(String portalId) {
		this.portalId = portalId;
	}

	public String getPortalName() {
		return portalName;
	}

	public void setPortalName(String portalName) {
		this.portalName = portalName;
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public List<Resource> getChildren() {
		return children;
	}

	public void setChildren(List<Resource> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Resources [Id=" + Id + ", parentId=" + parentId + ", parentName=" + parentName + ", portalId="
				+ portalId + ", portalName=" + portalName + ", name=" + name + ", code=" + code + ", icon=" + icon
				+ ", url=" + url + ", type=" + type + ", remarks=" + remarks + ", orderNo=" + orderNo + ", relationId="
				+ relationId + "]";
	}

}
