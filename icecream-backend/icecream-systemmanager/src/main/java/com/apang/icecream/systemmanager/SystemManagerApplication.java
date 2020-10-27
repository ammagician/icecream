/*
 * Copyright 2019-2050 apangkj.Inc All Rights Reserved.
 */
package com.apang.icecream.systemmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动类.<br>
 * 
 * @author andy pang <br>
 * @version 1.0.0 2019年8月29日<br>
 * @since JDK 1.8.0
 */

@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = { "com.apang.icecream" }, exclude = SecurityFilterAutoConfiguration.class)
public class SystemManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(SystemManagerApplication.class, args);
	}
}
