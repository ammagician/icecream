
package com.apang.icecream.auth.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 加密处理工具.<br>
 *
 * @author andy pang <br>
 * @version 1.0.0 2019年8月30日<br>
 * @since JDK 1.8.0
 */
public final class EncodeUtil {
    /**
     * 加密.
     * @param password 原始密码
     * @return 加密后的密码
     */
    public static String encode(String password) {
        return processEncoding(password);
    }

    /**
     * 密码匹配判断.
     * @param rawPassword 原始密码
     * @param encodedPassword 加密密码
     * @return true/false.
     */
    public static boolean matches(CharSequence rawPassword, String encodedPassword){
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder ();
        return encoder.matches(rawPassword, encodedPassword);
    }
    /**
     * 处理加密过程.
     * @param password 原始密码.
     * @return 处理后的密码.
     */
    public static String processEncoding(String password) {
        final BCryptPasswordEncoder  encoder = new BCryptPasswordEncoder ();
        return encoder.encode(password);
    }
}
