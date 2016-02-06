package com.zifangsky.OnlineFriend.model.member;

public class MemberInform {
	private String id,email,phone,message,pic,backNews;  //显示成员信息
	private boolean isSelectOk = false;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getBackNews() {
		return backNews;
	}

	public void setBackNews(String backNews) {
		this.backNews = backNews;
	}
	
	public boolean isSelectOk() {
		return isSelectOk;
	}

	public void setSelectOk(boolean isSelectOk) {
		this.isSelectOk = isSelectOk;
	}

}
