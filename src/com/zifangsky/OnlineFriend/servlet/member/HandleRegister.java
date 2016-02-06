package com.zifangsky.OnlineFriend.servlet.member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zifangsky.OnlineFriend.model.member.Register;
import com.zifangsky.OnlineFriend.util.DbConn;
import com.zifangsky.OnlineFriend.util.StringUtil;

public class HandleRegister extends HttpServlet{
	private String backNews = "",pic = "public.jpg";  //pic为图片信息
	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		Register registerBean = new Register();
		request.setAttribute("register", registerBean);
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		//获取注册信息
		String id = StringUtil.xssEncode(request.getParameter("id").trim());
		String password = StringUtil.xssEncode(request.getParameter("password").trim());
		String email = StringUtil.xssEncode(request.getParameter("email").trim());
		String phone = StringUtil.xssEncode(request.getParameter("phone").trim());
		String message = StringUtil.xssEncode(request.getParameter("message"));
		
		boolean isSuccess = false;  //判断注册信息是否符合规定
		if(StringUtil.isNotEmpty(id) &&StringUtil.isNotEmpty(password)){
			isSuccess = true;
			//判断id是否符合标准
			for(int i=0;i<id.length();i++){
				char c = id.charAt(i);
				if(!((c>='a'&&c<='z') || (c>='A'&&c<='Z') || (c>='0'&&c<='9'))){
					isSuccess = false;
					break;
				}
			}
		}
				
		//向 mysql 中注册用户			
		try {	
			if(isSuccess){
				Connection conn = DbConn.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement("insert into member(id,password,email,phone,message,pic) values(?,?,?,?,?,?)");
				preparedStatement.setString(1, id);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, email);
				preparedStatement.setString(4, phone);
				preparedStatement.setString(5, message);
				preparedStatement.setString(6, pic);
				
				//执行成功返回行数大于0
				int num = preparedStatement.executeUpdate();
				if(num != 0){
					backNews = "注册成功";
					registerBean.setBackNews(backNews);
					registerBean.setId(id);
					registerBean.setPassword(password);
					registerBean.setEmail(email);
					registerBean.setPhone(phone);
					registerBean.setMessage(message);
					registerBean.setRegisterSuccess(true);
				}
				preparedStatement.close();
				conn.close();
			}
			else{
				backNews = "信息填写不完整或者名字中有非法字符";
				registerBean.setBackNews(backNews);
			}			
		} catch (SQLException e) {
			backNews = "该ID已被使用，请更换ID";
			registerBean.setBackNews(backNews);
		}
				
		RequestDispatcher dispatcher = request.getRequestDispatcher("showRegisterMess.jsp");
		dispatcher.forward(request, response);	
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		doPost(request, response);
	}
}
