package com.example.lms.entity.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "library management system")
public class User extends com.example.lms.entity.User {

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
