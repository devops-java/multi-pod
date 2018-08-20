package com.spring.nginxbootmysql;

public class User {

	private Integer userId;
	private String email;
	private String password;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public User(Integer userId, String email, String password) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		char ch='a';
		short s=10;
		int x=20;
		long l=30l;
		float f1=20.3f;
		double d1= 23.40d;
		boolean flag1=true;
		boolean flag2=false;
		byte b1=12;
		
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

}
