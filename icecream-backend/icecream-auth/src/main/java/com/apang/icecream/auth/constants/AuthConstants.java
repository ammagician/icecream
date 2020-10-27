
package com.apang.icecream.auth.constants;
/**
 * 认证常量定义.<br>
 *
 * @author andy pang <br>
 * @version 1.0.0 2019年8月30日<br>
 * @since JDK 1.8.0
 */
public final class AuthConstants {
    /**
     * WEB服务的前缀.
     */
    public final static String C_WET_PREFIX = "/api";

    /**
     * userName.
     */
	public final static String C_PARAMETER_USERNAME = "userName";

    /**
     * password.
     */
    public final static String C_PARAMETER_PASSWORD = "password";

    /**
     * tenant.
     */
    public final static String C_PARAMETER_TENANT = "tenant";

    /**
     * 认证服务URI.
     */
    public final static String C_AUTH_URI = "/auth/login";

    /**
     * 认证失败URL.
     */
    public final static String C_AUTH_FAIL_URL = "/login/logout";


    /**
     * 认证成功URL.
     */
    public final static String C_AUTH_SUCCESS_URL = "/login/success";

    /**
     * Spring安全变量定义.
     */
    public final static String C_SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";

    /**
     * 默认许可.
     */
    public final static String C_ACCESS_ALLOW = "/api/**/allow/**";

    /**
     * open-api,对外接口.
     */
    public final static String C_OAUTH_OPEN_API = "/api/**/openApi/**";

    /**
     * open-api,对外接口.
     */
    public final static String C_OAUTH_AUTHORIZE = "/oauth/oauthorize/**";

    /**
     * swagger**开头
     */
    public final static String C_SWAGGER_PREFIX = "/swagger**";

    /**
     * swagger**开头的子目录.
     */
    public final static String C_SWAGGER_PREFIX_SUB = "/swagger**/**";

	/**
	 * swagger页面
	 */
	public final static String C_SWAGGER_URL = "/swagger-ui.html";

	/**
	 * swagger依赖
	 */
	public final static String C_SWAGGER_RES = "/webjars/springfox-swagger-ui/**";

    /**
     * 验证码过期时间（分钟）
     */
    public final static int CODE_REFRESH_MINUTE = 3;

    /**
     * 验证码session键
     */
    public final static String VALIDATE_CODE_SESSION_KEY = "validate_code_session_key";

    /**
     * 按钮级别的资源类型.
     */
	public final static String C_RESOURCE_TYPE = "6";


    /**
     * 用户锁定标识:1为锁定.
     */
    public final static long C_USER_LOCKED_VALUE = 1;

    /**
     * 用户不可用标识：1为不可用..
     */
    public final static long C_USER_Disabled_VALUE = 1;

    /**
     * HTTP GET方法.
     */
    public final static String METHOD_GET = "GET";
}
