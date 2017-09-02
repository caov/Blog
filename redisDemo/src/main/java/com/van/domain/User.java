package com.van.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User{
	
	@Id 
	private int id;
    
    private String username;
    
    private String password;
    
    private int age;
    
    public User() {
    	
    }
    
	public User(int id,String username, String password, int age) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
