package com.apang.icecream.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix="jdbc")
@PropertySource({"classpath:druid.properties"})
public class DataSourceProperty {

    @Value("${jdbc.driverClass}")
    private String driverClass;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.initialPoolSize}")
    private int initialPoolSize;

    @Value("${jdbc.maxPoolSize}")
    private int maxPoolSize;

    @Value("${jdbc.minPoolSize}")
    private int minPoolSize;

	@Value("${jdbc.dialect}")
	private String dialect;

	public String getDriverClass() {
		return driverClass;
	}


	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public int getInitialPoolSize() {
		return initialPoolSize;
	}

	public int getMaxPoolSize() {
		return maxPoolSize;
	}

	public int getMinPoolSize() {
		return minPoolSize;
	}


	public String getUrl() {
		return url;
	}

	public String getDialect() {
		return dialect;
	}

}
