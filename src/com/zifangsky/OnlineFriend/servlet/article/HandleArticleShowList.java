package com.zifangsky.OnlineFriend.servlet.article;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.rowset.CachedRowSetImpl;
import com.zifangsky.OnlineFriend.model.article.ShowByPage;
import com.zifangsky.OnlineFriend.util.DbConn;
import com.zifangsky.OnlineFriend.util.StringUtil;

public class HandleArticleShowList extends HttpServlet{
	private CachedRowSetImpl rowSet = null;

	public void init(ServletConfig config)throws ServletException{
		super.init(config);
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		//查看文章不需要登录
		continueDoGet(request,response);		
	}

	private void continueDoGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);		
		ShowByPage showByPageBean = null;
		
		try {
			showByPageBean = (ShowByPage)session.getAttribute("showAllTitle");
			if(showByPageBean == null){
				showByPageBean = new ShowByPage();
				session.setAttribute("showAllTitle", showByPageBean);
			}
		} catch (Exception e) {
			showByPageBean = new ShowByPage();
			session.setAttribute("showAllTitle", showByPageBean);
		}
		
		showByPageBean.setPageSize(10);  //每页显示10篇文章
		
		String showPageString = StringUtil.xssEncode(request.getParameter("showPage"));
		int showPage = 1;  //初始默认显示第一页
		if(showPageString != null)  //第一次从首页提交的时候，showPage参数不存在
			showPage = Integer.parseInt(showPageString);

		if(showPage > showByPageBean.getPageAllCount()) 
			showPage = 1;
		else if(showPage < 1)
			showPage = showByPageBean.getPageAllCount();
		showByPageBean.setShowPage(showPage);
		
		//连接数据库进行查询文章列表
		Connection connection = DbConn.getConnection();
		try {
			PreparedStatement pStatement = connection.prepareStatement("select id,title from article");
			ResultSet resultSet = pStatement.executeQuery();
			rowSet = new CachedRowSetImpl();
			rowSet.populate(resultSet);
			rowSet.last();
			int m = rowSet.getRow();  //总行数
			int n = showByPageBean.getPageSize();  //每页显示记录
			if(m % n == 0)
				showByPageBean.setPageAllCount(m / n);
			else 
				showByPageBean.setPageAllCount(m / n + 1);
			
			showByPageBean.setPresentPageResult(StringUtil.showArticleTitle(showPage, showByPageBean.getPageSize(), rowSet));
			
			resultSet.close();
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("article/index.jsp");
		dispatcher.forward(request, response);
		
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doGet(request, response);		
	}
	
}
