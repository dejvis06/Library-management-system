package com.example.lms.datasource_config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("mongo")
public class MongoConfig implements DataSourceConfig {

	@Override
	public void setup() {
		System.err.println("configuring mongo database.");
	}

}
