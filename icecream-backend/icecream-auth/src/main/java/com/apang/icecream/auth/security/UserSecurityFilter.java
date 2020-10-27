
package com.apang.icecream.auth.security;

import com.apang.icecream.auth.exception.OffLineException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 用户认证Filter.<br>
 *
 * @author andy pang <br>
 * @version 1.0.0 2019年9月4日<br>
 * @since JDK 1.8.0
 */
public class UserSecurityFilter extends AbstractSecurityInterceptor implements Filter {
    private static Logger logger = LoggerFactory.getLogger(UserSecurityFilter.class);

    private FilterInvocationSecurityMetadataSource securityMetadataSource;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        invoke(fi,request, response);
    }

    private void invoke(FilterInvocation fi, ServletRequest request, ServletResponse response) throws ServletException, IOException {
        InterceptorStatusToken token = null;
        String reqUrl = fi.getRequestUrl().substring(1);
        try {
            token = super.beforeInvocation(fi);
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } catch (OffLineException e) {
            logger.error("会话已过期，请重新登陆:" + e.getMessage());
            if (isAjaxRequest(fi.getRequest())) {
                fi.getResponse().setHeader("session status", "timeout1");
            } else {
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();

                StringBuilder builder = new StringBuilder();
                builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");
                builder.append("alert(\"登录超时，请重新登录\");");
                builder.append("window.top.location.href=\"");
                builder.append("/login/index");
                builder.append("\";</script>");
                out.print(builder.toString());

                out.close();
            }
        } catch (AccessDeniedException e) {
            logger.error("获取授权信息异常：" + e.getMessage());
            if (isAjaxRequest(fi.getRequest())) {
                fi.getResponse().setStatus(403);
            } else {
                throw e;
            }
        } catch (Exception e) {
            logger.error(reqUrl + "处理异常", e);
            throw new ServletException(e);
        } finally {
            super.afterInvocation(token, null);
        }
    }

    /**
     * Ajax请求判断.
     * @param request 请求.
     * @return true/false.
     */
    private boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with"));
    }

    public Class<? extends Object> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource) {
        this.securityMetadataSource = securityMetadataSource;
    }
}
