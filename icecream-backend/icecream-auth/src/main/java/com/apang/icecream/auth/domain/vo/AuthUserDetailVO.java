
package com.apang.icecream.auth.domain.vo;

import java.util.List;

import com.apang.icecream.core.domain.bo.Portal;
import com.apang.icecream.core.domain.bo.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class AuthUserDetailVO extends User {
    /**
     * 登录用户信息.
     */
    private User4AuthVO user4AuthVO;

	/**
	 * 授权Role列表.
	 */
	private List<Portal> portalList;

	/**
	 * 资源列表.
	 */
	private List<Resource> resourceList;

    /**
     * 授权Role列表.
     */
    private List<GrantedAuthority> grantedRoleList;

    /**
     * 构造函数.
     *
     * @param user4AuthVO 用户信息.
     * @param grantedRoleList 认证.
     * @param resources   资源.
     */
    public AuthUserDetailVO(User4AuthVO user4AuthVO,
							List<GrantedAuthority> grantedRoleList, List<Portal> portalList, List<Resource> resources) {
        super(user4AuthVO.getLoginName(), user4AuthVO.getPassword(), grantedRoleList);
        this.setUser4AuthVO(user4AuthVO);
        this.setResourceList(resources);
        this.setGrantedRoleList(grantedRoleList);
		this.setPortalList(portalList);
    }

    /**
     * get.
     *
     * @return User4AuthVO.
     */
    public User4AuthVO getUser4AuthVO() {
        return user4AuthVO;
    }

    /**
     * set.
     *
     * @param user4AuthVO 用户.
     */
    public void setUser4AuthVO(User4AuthVO user4AuthVO) {
        this.user4AuthVO = user4AuthVO;
    }

	public List<Resource> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}

	/**
	 * get.
	 * 
	 * @return Role信息列表.
	 */
    public List<GrantedAuthority> getGrantedRoleList() {
        return grantedRoleList;
    }

    /**
     * set.
     * @param grantedRoleList 角色列表.
     */
    public void setGrantedRoleList(List<GrantedAuthority> grantedRoleList) {
        this.grantedRoleList = grantedRoleList;
    }

	public List<Portal> getPortalList() {
		return portalList;
	}

	public void setPortalList(List<Portal> portalList) {
		this.portalList = portalList;
	}
}
