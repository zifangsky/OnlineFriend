package com.zifangsky.OnlineFriend.model.member;

public class Login {
	private String id,password,backNews;
	private boolean loginSuccess = false;  //是否已登录
	
	public String getId() {
		return id;
	}
	public void setId(String id) {		
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBackNews() {
		return backNews;
	}
	public void setBackNews(String backNews) {
		this.backNews = backNews;
	}
	public boolean isLoginSuccess() {
		return loginSuccess;
	}
	public void setLoginSuccess(boolean loginSuccess) {
		this.loginSuccess = loginSuccess;
	}
}
