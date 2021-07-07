package com.example.lms.service;

public interface ServiceInterface<T> {

	public T save(T object);

	public T find(int id);

	public void delete(int id);
}
