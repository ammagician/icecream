package com.apang.icecream.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({ "classpath:env.properties" })
public class EnvProperty {
	private final String DEV = "dev";

	@Value("${mode}")
	private String mode;

	public String getMode() {
		return mode;
	}

	public boolean isDevMode() {
		return DEV.equalsIgnoreCase(mode);
	}

}
