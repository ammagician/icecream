
package com.apang.icecream.auth.controller;

import com.apang.icecream.core.base.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.apang.icecream.auth.domain.vo.AuthUserDetailVO;
import com.apang.icecream.auth.utils.AuthenticationUtil;

/**
 * 授权资源访问.<br>
 *
 * @author andy pang <br>
 * @version 1.0.0 2019年9月12日<br>
 * @since JDK 1.8.0
 */
@RestController
@RequestMapping(value = "api/auth/resource")
public class AuthResourceController {
    /**
     * LOG.
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 获取用户基本信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/userUI", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult userUI() {
		HttpResult result = HttpResult.ok("获取用户UI信息。");
		AuthUserDetailVO user = AuthenticationUtil.getUser();
		user.getUser4AuthVO().setPassword("");
		if (user != null) {
			result.setData(user);
		} else {
			result.setSuccess(false);
			result.setMsg("用户未登陆");
		}

		return result;
	}

}
