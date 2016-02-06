package com.zifangsky.OnlineFriend.model.member;

import java.util.List;

public class UploadFile {
	private String backNews = "";
	private boolean isUploadFileOk = false;
	private String savedFileName;

	public String getSavedFileName() {
		return savedFileName;
	}

	public void setSavedFileName(String savedFileName) {
		this.savedFileName = savedFileName;
	}

	public String getBackNews() {
		return backNews;
	}

	public void setBackNews(String backNews) {
		this.backNews = backNews;
	}
	
	public boolean isUploadFileOk() {
		return isUploadFileOk;
	}

	public void setUploadFileOk(boolean isUploadFileOk) {
		this.isUploadFileOk = isUploadFileOk;
	}
}
