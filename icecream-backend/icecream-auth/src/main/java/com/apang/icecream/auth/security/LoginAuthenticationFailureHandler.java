
package com.apang.icecream.auth.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败处理器.<br>
 *
 * @author andy pang <br>
 * @version 1.0.0 2019年9月5日<br>
 * @since JDK 1.8.0
 */
@Component
public class LoginAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Value("${spring.security.logintype}")
    private String loginType;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (loginType.equalsIgnoreCase("JSON")) {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(500);
            response.getWriter().write(
                    objectMapper.writeValueAsString(exception.getMessage() + "用户名或密码存在错误，请检查后再次登录"));
        } else {
            response.setContentType("text/html;charset=UTF-8");
            //this.setDefaultFailureUrl(AuthConstants.C_AUTH_FAIL_URL);
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
