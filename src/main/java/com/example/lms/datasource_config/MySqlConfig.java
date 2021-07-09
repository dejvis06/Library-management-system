package com.example.lms.datasource_config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.example.lms.entity.Book;
import com.example.lms.service.ServiceInterface;
import com.example.lms.service.UserInterface;
import com.example.lms.service.mysql.BookService;
import com.example.lms.service.mysql.UserService;

@Configuration
@Profile("mysql")
public class MySqlConfig implements DataSourceConfig {

	@Autowired
	private DataSourceProperties dataSourceProperties;

	@Bean
	@Override
	@SuppressWarnings("unchecked")
	public DataSource configDataSource() {

		System.err.println("entered mysql configurations. properties: " + dataSourceProperties.toString());
		DataSourceBuilder<DataSource> dataSourceBuilder = (DataSourceBuilder<DataSource>) DataSourceBuilder.create();

		dataSourceBuilder.driverClassName(dataSourceProperties.getDriverClassName());
		dataSourceBuilder.url(dataSourceProperties.getUrl());
		dataSourceBuilder.username(dataSourceProperties.getUsername());
		dataSourceBuilder.password(dataSourceProperties.getPassword());

		return dataSourceBuilder.build();
	}

	@Primary
	@Bean
	public UserInterface userService() {
		return new UserService();
	}

	@Bean
	public ServiceInterface<Book> returnBookMongoService() {
		return new BookService();
	}
	
}
