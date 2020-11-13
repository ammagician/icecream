
package com.apang.icecream.core.domain.bo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 认证用户实体.<br>
 * @author andy pang <br>
 * @version 1.0.0 2019年8月29日<br>
 * @since JDK 1.8.0
 */
@TableName("s_tenant_portal")
public class TenantPortal {
    private static final long serialVersionUID = 1L;

    @TableField("tenantId")
    private Integer tenantId;

    @TableField("portalId")
    private Integer portalId;

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(String Integer) {
        this.tenantId = tenantId;
    }

    public Integer getPortalId() {
        return portalId;
    }

    public void setPortalId(Integer portalId) {
        this.portalId = portalId;
    }
}
