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
@TableName("s_org")
public class Org extends BaseDto {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @TableField("parentId")
    private String parentId;

	@TableField(exist = false)
	private String parentName;

    private String name;

    private String code;

    private Integer type;

    @TableField("orderNo")
    private Integer orderNo;

    private String remarks;

	@TableField(exist = false)
	private List<Org> children = new ArrayList<Org>();

    @TableField("tenantId")
	private String tenantId;

    public String getId() {
        return id;
    }

    public void setId(String Id) {
        this.id = Id;
    }
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public List<Org> getChildren() {
		return children;
	}

	public void setChildren(List<Org> children) {
		this.children = children;
	}

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String toString() {
        return "Org{" +
                "id='" + id + '\'' +
                ", parentId='" + parentId + '\'' +
                ", parentName='" + parentName + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", type=" + type +
                ", orderNo=" + orderNo +
                ", remarks='" + remarks + '\'' +
                ", children=" + children +
                ", tenantId='" + tenantId + '\'' +
                '}';
    }
}
