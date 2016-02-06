package com.zifangsky.OnlineFriend.servlet.member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zifangsky.OnlineFriend.model.member.Login;
import com.zifangsky.OnlineFriend.util.DbConn;
import com.zifangsky.OnlineFriend.util.StringUtil;

public class HandleLogin extends HttpServlet{
	private String backNews = "";  //登录状态返回信息
	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		
		//获取验证码
		String validateCode = StringUtil.xssEncode(request.getParameter("validateCode").trim());
		Object checkcode = session.getAttribute("checkcode");
		//将输入的验证码中的小写字母转换成大写，再和验证码生成时保存在session中的字符串比较		
		if(checkcode != null && checkcode.equals(StringUtil.convertToCapitalString(validateCode))){
			session.removeAttribute("checkcode");
			continueDoPost(request,response);
		}
		else{			
			response.sendRedirect("login.jsp");
			return;
		}
			
	}
	
	private void continueDoPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Login loginBean = null;
		HttpSession session = request.getSession(true);
		try {
			loginBean = (Login) session.getAttribute("login");
			if(loginBean == null){
				loginBean = new Login();
				session.setAttribute("login", loginBean);
			}
		} catch (Exception e) {
			loginBean = new Login();
			session.setAttribute("login", loginBean);
		}
		
		//获取登录的参数
		String id = StringUtil.xssEncode(request.getParameter("id").trim());
		String password = StringUtil.xssEncode(request.getParameter("password").trim());
		
		boolean loginOk = loginBean.isLoginSuccess();
		
		if(loginOk&&(id.equals(loginBean.getId())&&(password.equals(loginBean.getPassword())) )){
			backNews = "尊敬的会员："+id+",您已经登录，无需重复登录！";
			loginBean.setBackNews(backNews);
		}
		else{
			boolean chkLength = id.length()>0 && password.length()>0;  //判断用户名和密码是否为空
			if(chkLength){
				//连接数据库进行查询验证登录是否正确
				Connection connection = DbConn.getConnection();
				try {
					PreparedStatement pStatement = connection.prepareStatement("select id from member where id=? and password =?");
					pStatement.setString(1, id);
					pStatement.setString(2, password);
					ResultSet resultSet = pStatement.executeQuery();

					if(resultSet.next()){
						backNews = "登录成功";
						loginBean.setId(id);
						loginBean.setPassword(password);
						loginBean.setLoginSuccess(true);
						loginBean.setBackNews(backNews);
					}
					else{
						backNews = "您输入的用户名不存在，或密码不匹配";
						loginBean.setId(id);
						loginBean.setLoginSuccess(false);
						loginBean.setBackNews(backNews);
					}
					connection.close();
				} catch (SQLException e) {
					backNews = "您输入的用户名不存在，或密码不匹配";
					loginBean.setId(id);
					loginBean.setLoginSuccess(false);
					loginBean.setBackNews(backNews);
				}			
			}
			else{
				backNews = "用户名和密码不能为空";
				loginBean.setId(id);
				loginBean.setLoginSuccess(false);
				loginBean.setBackNews(backNews);
			}
		}	
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("showLoginMess.jsp");
		dispatcher.forward(request, response);
	}

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		doPost(request, response);
	}
}
