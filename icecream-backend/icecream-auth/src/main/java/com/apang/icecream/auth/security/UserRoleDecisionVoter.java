
package com.apang.icecream.auth.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.apang.icecream.core.domain.bo.Resource;
import com.apang.icecream.core.services.IResourceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;

import com.apang.icecream.auth.constants.AuthConstants;
import com.apang.icecream.auth.domain.vo.AuthUserDetailVO;
import com.apang.icecream.auth.domain.vo.User4AuthVO;
import com.apang.icecream.auth.utils.AuthenticationUtil;
import com.apang.icecream.config.EnvProperty;

/**
 * 投票器.<br>
 *
 * @author andy pang <br>
 * @version 1.0.0 2019年9月4日<br>
 * @since JDK 1.8.0
 */
public class UserRoleDecisionVoter implements AccessDecisionVoter<Object> {

	@Autowired
	private EnvProperty envProperty;

    /**
     * LOG.
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * IResource4AuthService.
     */
    @Autowired
    private IResourceService resourceService;

    /**
     * Matcher.
     */
    private PathMatcher pathMatcher = new AntPathMatcher();
    /**
     * 不允许访问的url清单.（逗号隔开）
     */
    private String excludeUrl;

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> collection) {
        int result = ACCESS_DENIED;
        if (!(object instanceof FilterInvocation)) {
            return result;
        }

        String loginName = authentication.getName();
        FilterInvocation filterInvocation = (FilterInvocation) object;
        String requestUrl = filterInvocation.getRequestUrl();

		String referer = filterInvocation.getRequest().getHeader("Referer");

        // 默认许可访问的处理.
        if (accessGranted4Allow(loginName, requestUrl)) return ACCESS_GRANTED;

        // openAPI的访问处理，授权由oauth负责.
        if (accessGranted4OpenAPI(loginName, requestUrl)) return ACCESS_GRANTED;

        // Swagger的处理.
		if (envProperty.isDevMode() && accessGranted4Swagger(requestUrl, referer))
			return ACCESS_GRANTED;

        // 检查是否具有访问资源的权限.
        if (accessGranted4Resource(requestUrl)) return ACCESS_GRANTED;

        if (logger.isInfoEnabled()) {
            logger.info("授权检查：" + requestUrl +"，该资源没有在授权资源清单中.");
        }

        return result;
    }

    /**
     * 检查是否具有访问资源的权限.
     * @param requestUrl 资源.
     * @return true/false.
     */
    private boolean accessGranted4Resource(String requestUrl) {
        final AuthUserDetailVO authUserDetailVO = AuthenticationUtil.getUser();
        if (null != authUserDetailVO && requestUrl.contains(AuthConstants.C_WET_PREFIX)) {
            // 检查是否属于资源授权清单中的资源（按钮级别的资源.）
            Resource r = new Resource();
            r.setType(Integer.valueOf(AuthConstants.C_RESOURCE_TYPE));
            QueryWrapper queryWrapper = new QueryWrapper(r);

            final List<Resource> buttonResourceList =  resourceService.list(queryWrapper);
            final Set<String> buttonResources  =   new HashSet<String>();
            for(Resource resource4Auth: buttonResourceList) {
                if (null != resource4Auth.getUrl()) {
                    buttonResources.add(resource4Auth.getUrl());
                }
            }
            if (!contain(buttonResources, requestUrl)) {
                return true;
            }

            // 检查该资源对应的角色是否具有该资源的权限.
            final User4AuthVO user4AuthVO = authUserDetailVO.getUser4AuthVO();
            if (null != user4AuthVO && user4AuthVO.getLoginName().length() > 0) {
                final Set<String> urlAuthorities = this.getGrantedURL(authUserDetailVO);
                if (contain(urlAuthorities, requestUrl)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 默认许可访问的处理.
     * @param loginName 登录名称.
     * @param requestUrl 资源.
     * @return true/false.
     */
    private boolean accessGranted4Allow(String loginName, String requestUrl) {
        if(!StringUtils.isEmpty(loginName) && (pathMatcher.match(AuthConstants.C_ACCESS_ALLOW, requestUrl))) {
            return true;
        }
        return false;
    }

    /**
     * openAPI的处理.
     * @param loginName 登录名称.
     * @param requestUrl 资源.
     * @return true/false.
     */
    private boolean accessGranted4OpenAPI(String loginName, String requestUrl) {
        if(!StringUtils.isEmpty(loginName) && (pathMatcher.match(AuthConstants.C_OAUTH_OPEN_API, requestUrl))
        || (pathMatcher.match(AuthConstants.C_OAUTH_AUTHORIZE, requestUrl))) {
            return true;
        }
        return false;
    }

    /**
     * Swagger访问授权.
     * @param referer referer.
     * @param requestUrl 资源.
     * @return true/false。
     */
    private boolean accessGranted4Swagger(String requestUrl, String referer) {
        if(pathMatcher.match(AuthConstants.C_SWAGGER_PREFIX, requestUrl)
            ||pathMatcher.match(AuthConstants.C_SWAGGER_PREFIX_SUB, requestUrl)
            ||pathMatcher.match(AuthConstants.C_SWAGGER_RES, requestUrl)
				|| (!StringUtils.isEmpty(referer) && referer.endsWith(AuthConstants.C_SWAGGER_URL))) {
            return true;
        }
        return false;
    }

    /**
     * 判断url是否在授权列表中.
     *
     * @param urlAuthorities 授权列表.
     * @param url            url.
     * @return true/false.
     */
    private boolean contain(Set<String> urlAuthorities, String url) {
        if (urlAuthorities.contains(url)) {
            return true;
        }

        for (String key : urlAuthorities) {
            if (pathMatcher.match(key, url)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查当前url是否属于“不允许访问清单”的列表中.
     * @param url 当前url.
     * @return true/false.
     */
    private boolean match(String url){
        final String[] expUrlList = excludeUrl.split(",");
        for(String exUrl : expUrlList){
            if(exUrl.contains(url)||pathMatcher.match(exUrl, url)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取授权的URL.
     * @param userDetailVO 认证用户.
     * @return 授权的URL列表.
     */
    private Set<String> getGrantedURL(AuthUserDetailVO userDetailVO){
        Set<String>  urlAuthorities  =   new  HashSet<String>();
        final List<Resource> resourceList = userDetailVO.getResourceList();
        for  (Resource resource4AuthVO : resourceList) {
            if(resource4AuthVO.getUrl() != null) {
                urlAuthorities.add(resource4AuthVO.getUrl());
            }
        }
        return urlAuthorities;
    }

    /**
     * get.
     * @return 不允许访问的url清单.
     */
    public String getExcludeUrl() {
        return excludeUrl;
    }

    /**
     * set.
     * @param excludeUrl 不允许访问的url清单.
     */
    public void setExcludeUrl(String excludeUrl) {
        this.excludeUrl = excludeUrl;
    }
}
