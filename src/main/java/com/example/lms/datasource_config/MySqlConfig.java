package com.example.lms.datasource_config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("mysql")
public class MySqlConfig implements DataSourceConfig {

	@Override
	public void setup() {
		System.err.println("configuring mysql database.");
	}

}
