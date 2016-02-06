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

import com.zifangsky.OnlineFriend.model.article.Article;
import com.zifangsky.OnlineFriend.util.DbConn;
import com.zifangsky.OnlineFriend.util.StringUtil;

public class HandleArticleShowContent extends HttpServlet{
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
		
		String articleId = StringUtil.xssEncode(request.getParameter("id"));
		Article article = new Article();
		request.setAttribute("article", article);
		
		Connection connection = DbConn.getConnection();
		try {
			PreparedStatement pStatement = connection.prepareStatement("select id,title,author,content from article where id=?");
			pStatement.setString(1, articleId);
			ResultSet resultSet = pStatement.executeQuery();
			if(resultSet.next()){
				article.setId(resultSet.getInt(1));
				article.setTitle(resultSet.getString(2));
				article.setAuthor(resultSet.getString(3));
				article.setContent(resultSet.getString(4));
			}
			pStatement.close();
			connection.close();
		} catch (Exception e) {

		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("article/showArticle.jsp");
		dispatcher.forward(request, response);	
	}
}
