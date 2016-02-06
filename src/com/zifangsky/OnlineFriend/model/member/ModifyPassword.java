package com.zifangsky.OnlineFriend.model.member;

public class ModifyPassword {
	private String newPassword,backNews;
	private boolean isModifyPasswordOk = false;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getBackNews() {
		return backNews;
	}

	public void setBackNews(String backNews) {
		this.backNews = backNews;
	}

	public boolean isModifyPasswordOk() {
		return isModifyPasswordOk;
	}

	public void setModifyPasswordOk(boolean isModifyPasswordOk) {
		this.isModifyPasswordOk = isModifyPasswordOk;
	}
}
