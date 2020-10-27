package com.apang.icecream.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({ "classpath:user.properties" })
public class UserProperty {

	@Value("${defaultPassword}")
	private String defaultPassword;

	public String getDefaultPassword() {
		return defaultPassword;
	}
}
