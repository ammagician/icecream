/*
 * Copyright 2019-2050 Gdczkj.Inc All Rights Reserved.
 */
package com.apang.icecream.core.domain.bo;

import com.apang.icecream.core.base.BaseDto;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.ArrayList;
import java.util.List;

/**
 * 认证角色实体.<br>
 * @author andy pang <br>
 * @version 1.0.0 2019年8月29日<br>
 * @since JDK 1.8.0
 */
@TableName("s_role")
public class Role  extends BaseDto {
    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;
    private String code;
    private String name;
    private String remarks;

    @TableField("tenantId")
    private String tenantId;

    @TableField(exist = false)
	private List<Permission> permissions;

    @TableField(exist = false)
    List<String> portals;

    public List<String> getPortals() {
        return portals;
    }

    public void setPortals(List<String> portals) {
        this.portals = portals;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

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

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public List<String> getPortalsByPermission() {
        List<String> portals = new ArrayList<String>();
        if(null == permissions){
            return portals;
        }

        for(Permission p : permissions){
            if(p.getType() == 1){
                portals.add(p.getResId());
            }
        }


        return portals;
    }
}
