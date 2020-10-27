
package com.apang.icecream.auth.security;

import java.util.*;

import com.apang.icecream.core.domain.bo.*;
import com.apang.icecream.core.services.IPermissionService;
import com.apang.icecream.core.services.IResourceService;
import com.apang.icecream.core.services.ITenantService;
import com.apang.icecream.core.services.IUserService;
import com.apang.icecream.core.util.ListUtil;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.apang.icecream.auth.constants.AuthConstants;
import com.apang.icecream.auth.domain.vo.AuthUserDetailVO;
import com.apang.icecream.auth.domain.vo.User4AuthVO;

/**
 * 从数据库中获取用户登录信息.<br>
 * @author andy pang <br>
 * @version 1.0.0 2019年8月29日<br>
 * @since JDK 1.8.0
 */
public class User4AuthDetailService implements UserDetailsService {
    /**
     * LOG.
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * IUser4AuthService.
     */
    @Autowired
    private IUserService userService;

    @Autowired
    private ITenantService tenantService;

    @Autowired
    private IResourceService resourceService;

    /**
     * DOZER mapper.
     */
    @Autowired
    Mapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final long startTime = System.currentTimeMillis();
        // 认证用户

        String[] arr = username.split("::::");

        final User user = userService.findByLoginNameAndTenantId(arr[1], arr[0]);
        if (null == user) {
            final String message = "该用户名不存在，请检查。";
            throw new UsernameNotFoundException(message);
        }

        // 检查账号可用情况.
        this.checkUserAvailability(user);

        // 授权资源处理.
        final List<Role> roleList = user.getRoles();
        final List<GrantedAuthority> grantedRoleList =new ArrayList <GrantedAuthority> ();
        final Set<Resource> resourceSet = new HashSet<Resource>();
        for (Role role : roleList) {
            grantedRoleList.add(new SimpleGrantedAuthority(role.getId()));
        }

        resourceSet.addAll(resourceService.getGrantResources(user.getId()));

        final Set<Portal> portalList = new HashSet<Portal>();
        Map<String, Set<Resource>> portalResource = new HashMap<String, Set<Resource>>();
        for (Resource r : resourceSet) {
            if(r.getType() == 1){
                Portal pp = new Portal();
                pp.setId(r.getId());
                pp.setName(r.getName());
                portalList.add(pp);
            }else{
                Set<Resource> resources = portalResource.get(r.getPortalId());
                if(resources == null){
                    resources = new HashSet<Resource>();
                    portalResource.put(r.getPortalId(), resources);
                }
                resources.add(r);
            }
        }

        for (Portal portal : portalList) {
            Set<Resource> resources = portalResource.get(portal.getId());

            List<Portlet> portletList = new ArrayList<Portlet>();
            List<Menu> menuList = new ArrayList<Menu>();
            List<String> permissionList = new ArrayList<String>();
            if(resources != null){
                for(Resource r: resources){
                    Integer type = r.getType();
                    if(type ==2){
                        Portlet p = mapper.map(r, Portlet.class);
                        portletList.add(p);
                    }else if(type ==3 || type ==4){
                        Menu m = mapper.map(r, Menu.class);
                        menuList.add(m);
                    }else if(type ==6){ //此处简单处理，只需要按钮权限就可以
                        permissionList.add(r.getCode());
                    }
                }
            }


            portal.setPortletList(portletList);
            portal.setMenuList(ListUtil.buildMenuTree(menuList));
            portal.setPermissionList(permissionList);
        }

        final User4AuthVO user4AuthVO = mapper.map(user, User4AuthVO.class);
		final AuthUserDetailVO authUserDetailVO = new AuthUserDetailVO(user4AuthVO, grantedRoleList, new ArrayList<Portal>(portalList), new ArrayList<Resource>(resourceSet));

        final long endTime  = System.currentTimeMillis();
        if (logger.isInfoEnabled()) {
            logger.info("登录构建User,耗时（毫秒）：" + String.valueOf(endTime-startTime));
        }
        return authUserDetailVO;
    }

    /**
     * 检查账号可用情况.
     * @param user 实例.
     */
    private void checkUserAvailability(User user) {
        if (user.getIsDisabled() == AuthConstants.C_USER_Disabled_VALUE) {
            final String message = "用户被禁用，请联系管理员";
            throw new DisabledException(message);
        }

        //租户不存在门户时，不能登录
        Tenant tenant = tenantService.getDetailById(user.getTenantId());

        if(tenant.getPortals().size() == 0){
            final String message = "租户没有任何门户的权限，请联系管理员";
            throw new DisabledException(message);
        }
    }
}
