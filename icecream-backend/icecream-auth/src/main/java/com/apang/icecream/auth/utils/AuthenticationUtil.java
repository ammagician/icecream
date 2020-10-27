
package com.apang.icecream.auth.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.apang.icecream.auth.domain.vo.AuthUserDetailVO;
import com.apang.icecream.core.domain.bo.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

/**
 * 认证信息工具类<br>
 *
 * @author andy pang <br>
 * @version 1.0.0 2019年9月2日<br>
 * @since JDK 1.8.0
 */
public final class AuthenticationUtil {
    /**
     * 获取认证实例.
     * @return 实例.
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取有权限的资源.
     * @return 资源列表.
     */
    public static Collection<? extends GrantedAuthority> getAllPermission(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return authorities;
    }

    /**
     * 是否具有权限的判断.
     * @param permission 资源.
     * @return true/false.
     */
    public static boolean hasPermission(String permission){
        if(StringUtils.isEmpty(permission)){
            return false;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        boolean hasPermission = false;
        for(GrantedAuthority grantedAuthority : authorities){
            String authority = grantedAuthority.getAuthority();
            if(authority.equals(permission)){
                hasPermission =true;
            }
        }
        return hasPermission;
    }

    /**
     * 获取用户信息.
     * @return 用户实例.
     */
    public static AuthUserDetailVO getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object user = authentication.getPrincipal();
		if (user instanceof String) {
			return null;
		}
		return (AuthUserDetailVO) user;
    }

	/**
	 * 获取用户Id.
	 * 
	 * @return 用户Id.
	 */
	public static String getUserId() {
		AuthUserDetailVO userDetailVO = AuthenticationUtil.getUser();
		if (userDetailVO == null) {
			return null;
		}
        return userDetailVO.getUser4AuthVO().getId();
	}

    /**
     * 获取用户Id.
     *
     * @return 用户Id.
     */
    public static String getTenantId() {
        AuthUserDetailVO userDetailVO = AuthenticationUtil.getUser();
        if (userDetailVO == null) {
            return null;
        }
        return userDetailVO.getUser4AuthVO().getTenantId();
    }

    /**
     * 获取角色信息.
     * @return 角色列表.
     */
    public static List<GrantedAuthority>  getGrantedRoleList() {
        final List<GrantedAuthority> grantedList =new ArrayList<GrantedAuthority>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final AuthUserDetailVO authUserDetailVO =  (AuthUserDetailVO) authentication.getPrincipal();
        return authUserDetailVO.getGrantedRoleList();
    }

    /**
     * 获取资源列表.
     * @return 资源列表.
     */
    public static List<Resource>  getGrantedResourceList() {
        final List<GrantedAuthority> grantedList =new ArrayList<GrantedAuthority>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final AuthUserDetailVO authUserDetailVO =  (AuthUserDetailVO) authentication.getPrincipal();
        return authUserDetailVO.getResourceList();
    }

    /**
     * 获取当前登录用户的资源列表.
     * @return 资源列表.
     */
    public static List<Resource> getAuthResourceList() {
        List<Resource> resource4AuthVOList = new ArrayList<Resource>();
        final AuthUserDetailVO userDetail = getUser();
        if (null != userDetail) {
            resource4AuthVOList = userDetail.getResourceList();
            if (null == resource4AuthVOList) {
                resource4AuthVOList =  new ArrayList<Resource>();
            }
        }
        return resource4AuthVOList;
    }

    /**
     * 注销.
     */
    public static void logout() {
        SecurityContextHolder.clearContext();
    }

	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
