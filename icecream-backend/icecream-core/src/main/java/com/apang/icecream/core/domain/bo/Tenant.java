package com.apang.icecream.core.domain.bo;

import com.apang.icecream.core.base.BaseDto;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author andy.pang
 * @since 2019-09-09
 */
@TableName("s_tenant")
public class Tenant extends BaseDto {

    private static final long serialVersionUID = 1L;

    @TableId(value="id",type= IdType.AUTO)
    private Integer id;

    private String name;

    @TableField("rootOrg")
    private String rootOrg;

    @TableField(exist = false)
    private String rootOrgCode;

    private String remarks;

    @TableField("ukey")
    private String uKey;

    @TableField(exist = false)
    List<String> portals;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRootOrg() {
        return rootOrg;
    }

    public void setRootOrg(String rootOrg) {
        this.rootOrg = rootOrg;
    }

    public String getRootOrgCode() {
        return rootOrgCode;
    }

    public void setRootOrgCode(String rootOrgCode) {
        this.rootOrgCode = rootOrgCode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<String> getPortals() {
        return portals;
    }

    public void setPortals(List<String> portals) {
        this.portals = portals;
    }


    public String getuKey() {
        return uKey;
    }

    public void setuKey(String uKey) {
        this.uKey = uKey;
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", rootOrg='" + rootOrg + '\'' +
                ", rootOrgCode='" + rootOrgCode + '\'' +
                ", remarks='" + remarks + '\'' +
                ", portals=" + portals +
                '}';
    }
}
