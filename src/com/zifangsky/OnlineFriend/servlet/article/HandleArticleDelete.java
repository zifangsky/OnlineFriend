package com.zifangsky.OnlineFriend.servlet.article;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zifangsky.OnlineFriend.model.article.ErrorMessage;
import com.zifangsky.OnlineFriend.model.member.Login;
import com.zifangsky.OnlineFriend.util.DbConn;
import com.zifangsky.OnlineFriend.util.StringUtil;

public class HandleArticleDelete extends HttpServlet{
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		HttpSession session = request.getSession(true);
		Login loginBean = (Login) session.getAttribute("login");
		
		if(loginBean == null){
			response.sendRedirect("login.jsp");
			return;
		}
		else{
			String author = request.getParameter("author");
			String id = loginBean.getId();
			//检验登录用户和文章作者是否是同一人
			if(author.equals(id))
				continueDoPost(request,response);  			
			else {
				ErrorMessage errorMessage = new ErrorMessage();
				request.setAttribute("error", errorMessage);
				errorMessage.setBackNews("您没有权限删除该文章");
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("article/error.jsp");
				dispatcher.forward(request, response);	
			}
		}			
	}

	private void continueDoPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
	
		//获取待删除文章的 id
		String articleId = StringUtil.xssEncode(request.getParameter("articleId"));
		try {
			Connection connection = DbConn.getConnection();
			//从数据库中删除
			PreparedStatement pStatement = connection.prepareStatement("delete from article where id=?");
			pStatement.setString(1, articleId);
	
			pStatement.executeUpdate();
			
		} catch (Exception e) {
			
		}
			
		RequestDispatcher dispatcher = request.getRequestDispatcher("ArticleShowList");
		dispatcher.forward(request, response);	
	}


}
