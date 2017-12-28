package edu.jennifer.hotel.model;

import com.google.gson.Gson;

public class User {

	private String id;
	private String username;
	private String password;
	private String realName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	

	public String toJson(){
		Gson g = new Gson();
		return g.toJson(this);
	}
}
