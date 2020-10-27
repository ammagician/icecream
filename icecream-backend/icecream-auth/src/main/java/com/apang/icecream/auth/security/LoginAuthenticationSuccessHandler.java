
package com.apang.icecream.auth.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功处理器.<br>
 *
 * @author andy pang <br>
 * @version 1.0.0 2019年9月5日<br>
 * @since JDK 1.8.0
 */
@Component
public class LoginAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Value("${spring.security.logintype}")
    private String loginType;

    private  static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        if (loginType.equalsIgnoreCase("JSON")) {
            // JSON 模式.
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        } else {
            // VIEW 模式.
            //this.setDefaultTargetUrl(AuthConstants.C_AUTH_SUCCESS_URL);
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
