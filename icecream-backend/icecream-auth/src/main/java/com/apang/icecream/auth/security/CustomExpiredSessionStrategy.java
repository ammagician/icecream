
package com.apang.icecream.auth.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import com.apang.icecream.core.services.ILoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Session失效策略.<br>
 *
 * @author andy pang <br>
 * @version 1.0.0 2019年9月5日<br>
 * @since JDK 1.8.0
 */
@Component
public class CustomExpiredSessionStrategy implements SessionInformationExpiredStrategy {
    private ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private ILoginLogService loginLogService;

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
		SessionInformation info = event.getSessionInformation();
		String sessionId = info.getSessionId();
		if (!StringUtils.isEmpty(sessionId)) {
			UserCount.remove();
			loginLogService.logoutBySessionId(info.getSessionId());
		}

        final Map<String, Object> map = new HashMap<>(16);
        map.put("code", 0);
		map.put("msg", "当前用户会话失效，您被迫下线。" + info.getLastRequest());

        final String json = objectMapper.writeValueAsString(map);
        event.getResponse().setContentType("application/json;charset=UTF-8");
        event.getResponse().getWriter().write(json);
    }
}
