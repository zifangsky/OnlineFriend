package com.zifangsky.OnlineFriend.servlet.member;

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

import com.zifangsky.OnlineFriend.model.member.Login;
import com.zifangsky.OnlineFriend.model.member.MemberInform;
import com.zifangsky.OnlineFriend.util.DbConn;

public class HandleShowLoginedMember extends HttpServlet{

	public void init(ServletConfig config)throws ServletException{
		super.init(config);
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		HttpSession session = request.getSession(true);
		Login loginBean = (Login) session.getAttribute("login");
		
		if(loginBean == null){
			response.sendRedirect("login.jsp");
			return;
		}
		else
			continueDoGet(request, response);  //显示登录的成员信息
	}

	private void continueDoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		Login loginBean = (Login) session.getAttribute("login");
		
		String id = loginBean.getId();
		MemberInform memberInformBean = new MemberInform();
		request.setAttribute("loginedInform", memberInformBean);
		
		Connection connection = DbConn.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select id,email,phone,message,pic from member where id=?");
			preparedStatement.setString(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				memberInformBean.setBackNews("个人信息：");
				memberInformBean.setSelectOk(true);
				memberInformBean.setId(resultSet.getString(1));
				memberInformBean.setEmail(resultSet.getString(2));
				memberInformBean.setPhone(resultSet.getString(3));
				memberInformBean.setMessage(resultSet.getString(4));
				memberInformBean.setPic(resultSet.getString(5));
			}
			else
				memberInformBean.setBackNews("未查到任何信息。。。");
		
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			memberInformBean.setBackNews("未查到任何信息。。。");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/index.jsp");
		dispatcher.forward(request, response);
	}
}
