package com.zifangsky.OnlineFriend.model.member;

public class ModifyMessage {
	private String newEmail,newPhone,newMessage,backNews;
	private boolean isModifyRegisterMessageOk = false;

	public String getNewEmail() {
		return newEmail;
	}

	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}

	public String getNewPhone() {
		return newPhone;
	}

	public void setNewPhone(String newPhone) {
		this.newPhone = newPhone;
	}

	public String getNewMessage() {
		return newMessage;
	}

	public void setNewMessage(String newMessage) {
		this.newMessage = newMessage;
	}

	public String getBackNews() {
		return backNews;
	}

	public void setBackNews(String backNews) {
		this.backNews = backNews;
	}

	public boolean isModifyRegisterMessageOk() {
		return isModifyRegisterMessageOk;
	}

	public void setModifyRegisterMessageOk(boolean isModifyRegisterMessageOk) {
		this.isModifyRegisterMessageOk = isModifyRegisterMessageOk;
	}	
}
