package com.apang.icecream.auth.security;

import com.apang.icecream.auth.constants.AuthConstants;
import com.apang.icecream.auth.utils.AuthenticationUtil;
import com.apang.icecream.core.base.LoggerManage;
import com.apang.icecream.core.domain.bo.OperateLog;
import com.apang.icecream.core.services.IOperateLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.UUID;

@Component
@Aspect
public class OperateLogAop {
	private PathMatcher pathMatcher = new AntPathMatcher();

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private IOperateLogService operateLogService;

	@Around("execution(* com.apang.icecream..*.controller.*.*(..)) && @annotation(loggerManage)")
	// 环绕通知,切点表达式表示匹配 controller包下任意返回值,任意类,任意方法,任意参数
	public Object around(ProceedingJoinPoint pjp, LoggerManage loggerManage) {
		String requestUrl = request.getRequestURI();
		String method = request.getMethod();

		if (!(pathMatcher.match(AuthConstants.C_ACCESS_ALLOW, requestUrl)
				|| pathMatcher.match(AuthConstants.C_OAUTH_OPEN_API, requestUrl)
				|| pathMatcher.match(AuthConstants.C_OAUTH_AUTHORIZE, requestUrl)
				|| pathMatcher.match(AuthConstants.C_SWAGGER_PREFIX, requestUrl)
				|| pathMatcher.match(AuthConstants.C_SWAGGER_PREFIX_SUB, requestUrl))
				&& !AuthConstants.METHOD_GET.equalsIgnoreCase(method)) {
			saveOperateLog(createLog(request, loggerManage));
		}
		try {
			Object result = pjp.proceed();
			return result;
		} catch (Throwable throwable) {
			throwable.printStackTrace();
			throw new RuntimeException(throwable);
		}
	}

	private OperateLog createLog(HttpServletRequest request, LoggerManage loggerManage) {
		OperateLog log = new OperateLog();
		//log.setId(UUID.randomUUID().toString());
		log.setIpAddress(AuthenticationUtil.getIpAddress(request));
		log.setVisitTime(new Timestamp(System.currentTimeMillis()));
		log.setSessionID(request.getSession().getId());
		log.setVisitor(AuthenticationUtil.getUser().getUsername());
		log.setUrl(request.getRequestURI());
		log.setMethodType(request.getMethod().toUpperCase());
		if (loggerManage != null) {
			log.setModuleName(loggerManage.module());
			log.setOperateName(loggerManage.operate());
		}
		return log;
	}

	private void saveOperateLog(OperateLog log) {
		operateLogService.save(log);
	}
}
