
package com.apang.icecream.auth.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apang.icecream.auth.constants.AuthConstants;
import com.apang.icecream.auth.security.UserCount;
import com.apang.icecream.auth.utils.Tools;
import com.apang.icecream.core.services.ILoginLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.apang.icecream.core.domain.bo.LoginLog;
import com.apang.icecream.auth.domain.vo.AuthUserDetailVO;
import com.apang.icecream.auth.utils.AuthenticationUtil;
import com.apang.icecream.core.base.HttpResult;

/**
 * 自定义登录认证.<br>
 *
 * @author andy pang <br>
 * @version 1.0.0 2019年9月8日<br>
 * @since JDK 1.8.0
 */


@RestController
@RequestMapping(value = "/api/auth/allow")
public class CustomLoginController {
    /**
     * LOG.
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

	@Autowired
	private ILoginLogService loginLogService;

    /**
     * 登陆action处理.
     * @param map 参数.
     * @param request  请求.
     * @return true/false.
     */
    @RequestMapping(value="/login",method= RequestMethod.POST)
    @ResponseBody
	public HttpResult login(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        final String userName = (String) map.get(AuthConstants.C_PARAMETER_USERNAME);
        final String password = (String) map.get(AuthConstants.C_PARAMETER_PASSWORD);
        final String tenant = (String) map.get(AuthConstants.C_PARAMETER_TENANT);
        
        
        // 密码对称解密
        // TODO.
		HttpResult result;
        try {
            logger.info("---开始进行登陆认证操作---");
            final long startTime = System.currentTimeMillis();
			final Authentication authentication = new UsernamePasswordAuthenticationToken(tenant + "::::" + userName, password);
            authenticationManager.authenticate(authentication);
            final long endTime = System.currentTimeMillis();
            logger.info("---登陆认证操作结束，登录认证时间(毫秒)：" + String.valueOf(endTime - startTime));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final HttpSession session = request.getSession();
            session.setAttribute(AuthConstants.C_SPRING_SECURITY_CONTEXT, SecurityContextHolder.getContext());
			result = HttpResult.ok("用户登陆认证成功。");
			result.setData(authentication.hashCode());
			UserCount.add();
			saveLoginLog(request, userName, tenant);
        } catch (AuthenticationException ex) {
			String msg = ex.getMessage();
			logger.info(msg);
			result = HttpResult.error("用户登陆失败--->" + msg);
        }
        return result;
    }

    /**
	 * 获取在线人数.
	 * 
	 * @param request 请求.
	 * @param respone 响应.
	 * @return 在线人数.
	 */
	@RequestMapping(value = "/onlineCount")
	@ResponseBody
	public HttpResult onlineCount(HttpServletRequest request, HttpServletResponse respone) {
		final HttpResult result = HttpResult.ok("用户登出成功。");
		result.setData(UserCount.now());

		return result;
	}

	/**
	 * 登出处理.
	 * 
	 * @param request 请求.
	 * @param respone 响应.
	 * @return 登出结果.
	 */
    @RequestMapping(value="/logout")
    @ResponseBody
    public HttpResult logout(HttpServletRequest request, HttpServletResponse respone) {
		AuthUserDetailVO user = AuthenticationUtil.getUser();
		if (user != null) {
			UserCount.remove();
			saveLogoutLog(user.getUsername(), user.getUser4AuthVO().getTenantId());
		}

        final HttpSession session = request.getSession(false);
        if (session != null) {
            logger.debug("Invalidating session: " + session.getId());
            session.invalidate();
        }
        final SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(null);

        SecurityContextHolder.clearContext();
        final HttpResult result = HttpResult.ok("用户登出成功。");

        return result;
    }
    /**
     * 刷新登录校验码
     * @param request 请求
     * @param respone 响应
     */
    @RequestMapping(value="/loginCode")
    public void getValidateCode(HttpServletRequest request, HttpServletResponse respone) {
        this.reSetValidateCode(request, respone);
    }

    /**
     * 校验验证码
     * @param request 请求
     * @param code 验证码
     * @return json
     */
    @RequestMapping(value="/validateCode")
    @ResponseBody
    public HttpResult validateCode(HttpServletRequest request, @RequestParam("code") String code) {
        HttpResult result = new HttpResult();
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        Map<String, Object> verifyMap = (Map<String, Object>) session.getAttribute(AuthConstants.VALIDATE_CODE_SESSION_KEY);
        if (null != verifyMap) {
            Date now = new Date();
            Date oldTime = (Date) verifyMap.get("time");
            String oldCode = (String) (verifyMap.get("code")==null?"":verifyMap.get("code"));
            //校验验证码时间不超过2分钟
            long minute = (now.getTime() - oldTime.getTime()) / 60*1000;
            if (minute < AuthConstants.CODE_REFRESH_MINUTE) {
                if(null != oldCode && code.trim().equalsIgnoreCase(oldCode.trim())) {
                    result.setSuccess(true);
                } else {
                    result.setSuccess(false);
                    result.setMsg("验证码填写有误，请重新输入。");
                }
            } else {
                result.setSuccess(false);
                result.setMsg("验证码时间过期，请重新输入。");
            }
        }
        return result;
    }

    /**
     * 生成验证码
     * @param request  请求
     * @param respone 响应
     */
    private void reSetValidateCode(HttpServletRequest request, HttpServletResponse respone) {
        //设置验证码
        String chekCode = (Math.random()+"").substring(2, 6);
        Map<String, Object> verifyMap = new HashMap<String, Object>(2);
        verifyMap.put("time", new Date());
        verifyMap.put("code", chekCode);
        HttpSession session = request.getSession();
        session.removeAttribute(AuthConstants.VALIDATE_CODE_SESSION_KEY);
        session.setAttribute(AuthConstants.VALIDATE_CODE_SESSION_KEY, verifyMap);
        char[] code = chekCode.toCharArray();
        try {
            Tools.image.createValidateImage(respone.getOutputStream(), code);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

	private void saveLoginLog(HttpServletRequest request, String userName, String tenant) {
		saveLogoutLog(userName, tenant);

		LoginLog log = new LoginLog();
		log.setId(UUID.randomUUID().toString());
		log.setIpAddress(AuthenticationUtil.getIpAddress(request));
		log.setLoginTime(new Timestamp(System.currentTimeMillis()));
		log.setSessionID(request.getSession().getId());
		log.setVisitor(userName);
		log.setTenantId(tenant);
		loginLogService.save(log);
	}

	private void saveLogoutLog(String userName, String tenant) {
        LoginLog log = new LoginLog();
        log.setVisitor(userName);
        log.setTenantId(tenant);
        log.setLogoutTime(null);
        QueryWrapper<LoginLog> queryWrapper = new QueryWrapper<LoginLog>(log);

        List<LoginLog> logList = loginLogService.list(queryWrapper);
		for (LoginLog logOut : logList) {
            logOut.setLogoutTime(new Timestamp(System.currentTimeMillis()));
		}
        loginLogService.updateBatchById(logList);
	}
}
