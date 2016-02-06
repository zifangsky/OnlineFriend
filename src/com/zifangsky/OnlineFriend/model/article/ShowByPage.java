package com.zifangsky.OnlineFriend.model.article;

import com.sun.rowset.CachedRowSetImpl;

public class ShowByPage {
	int pageSize = 10;  //每页显示的记录数
	int pageAllCount = 0;  //分页后的总页数
	int showPage = 1;  //当前显示的是第几页
	StringBuffer presentPageResult;  //显示当前页内容
	
	/**
	 * 获取每页显示的记录数
	 * */
	public int getPageSize() {
		return pageSize;
	}
	
	/**
	 * 设置每页显示的记录数
	 * */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	/**
	 * 获取分页后的总页数
	 * */
	public int getPageAllCount() {
		return pageAllCount;
	}
	
	/**
	 * 设置分页后的总页数
	 * */
	public void setPageAllCount(int pageAllCount) {
		this.pageAllCount = pageAllCount;
	}
	
	/**
	 * 获取当前显示的是第几页
	 * */
	public int getShowPage() {
		return showPage;
	}
	
	/**
	 * 设置当前显示的是第几页
	 * */
	public void setShowPage(int showPage) {
		this.showPage = showPage;
	}
	
	/**
	 * 获取当前显示页的内容
	 * */
	public StringBuffer getPresentPageResult() {
		return presentPageResult;
	}
	
	/**
	 * 设置当前显示页的内容
	 * */
	public void setPresentPageResult(StringBuffer presentPageResult) {
		this.presentPageResult = presentPageResult;
	}
}
