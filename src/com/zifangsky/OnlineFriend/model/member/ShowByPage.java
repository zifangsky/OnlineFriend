package com.zifangsky.OnlineFriend.model.member;

import com.sun.rowset.CachedRowSetImpl;

public class ShowByPage {
	int pageSize = 10;  //每页显示的记录数
	int pageAllCount = 0;  //分页后的总页数
	int showPage = 1;  //当前显示页
	StringBuffer presentPageResult;  //显示当前页内容

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageAllCount() {
		return pageAllCount;
	}
	public void setPageAllCount(int pageAllCount) {
		this.pageAllCount = pageAllCount;
	}
	public int getShowPage() {
		return showPage;
	}
	public void setShowPage(int showPage) {
		this.showPage = showPage;
	}
	public StringBuffer getPresentPageResult() {
		return presentPageResult;
	}
	public void setPresentPageResult(StringBuffer presentPageResult) {
		this.presentPageResult = presentPageResult;
	}
}
