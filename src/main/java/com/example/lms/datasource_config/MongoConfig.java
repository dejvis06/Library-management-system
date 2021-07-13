package com.example.lms.datasource_config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import com.example.lms.entity.User;
import com.example.lms.repository.UserRepository;
import com.example.lms.service.mysql.UserService;

@Configuration
@Profile("mongo")
public class MongoConfig {

	@Autowired
	private MongoDataSourceProperties dataSourceProperties;

	private com.example.lms.repository.mongo.UserRepository userRepository;

	@Bean
	@Primary
	public MongoProperties mongoProperties() {

		MongoProperties mongoProperties = new MongoProperties();
		mongoProperties.setDatabase(dataSourceProperties.getDatabase());
		mongoProperties.setHost(dataSourceProperties.getHost());
		mongoProperties.setPort(Integer.valueOf(dataSourceProperties.getPort()));
		mongoProperties.setUsername(dataSourceProperties.getUsername());
		mongoProperties.setPassword(dataSourceProperties.getPassword().toCharArray());

		return mongoProperties;
	}

	// establishes connection
	@Bean
	public MongoDatabaseFactory mongoDatabaseFactory(MongoProperties mongoProperties) {
		return new SimpleMongoClientDatabaseFactory(mongoProperties().getUri());
	}

	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoDatabaseFactory(mongoProperties()));
	}

	@Bean
	public UserRepository userMongoRepository() {
		return userRepository;
	}

}
