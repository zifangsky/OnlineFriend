package com.zifangsky.OnlineFriend.servlet.member;

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

import com.zifangsky.OnlineFriend.model.member.Login;
import com.zifangsky.OnlineFriend.model.member.ModifyMessage;
import com.zifangsky.OnlineFriend.util.DbConn;
import com.zifangsky.OnlineFriend.util.StringUtil;

public class HandleModifyRegisterMess extends HttpServlet{
	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{		
		HttpSession session = request.getSession(true);
		Login loginBean = (Login) session.getAttribute("login");
		if(loginBean == null){
			response.sendRedirect("login.jsp");
			return;
		}
		else
			modifyRegisterMess(request,response);	
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doPost(request, response);
	}

	private void modifyRegisterMess(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		Login loginBean = (Login) session.getAttribute("login");
		
		String id = loginBean.getId();  //获取已登用户录的id
		ModifyMessage modifyMessage = new ModifyMessage();
		request.setAttribute("modifyMess", modifyMessage);
		
		//获取输入参数
		String newEmail = StringUtil.xssEncode(request.getParameter("newEmail").trim());
		String newPhone = StringUtil.xssEncode(request.getParameter("newPhone").trim());
		String newMessage = StringUtil.xssEncode(request.getParameter("newMessage"));
		
		//开始更新
		Connection connection = DbConn.getConnection();
		try {
			PreparedStatement pStatement = connection.prepareStatement("update member set email=?,phone=?,message=? where id=?");
			pStatement.setString(1, newEmail);
			pStatement.setString(2, newPhone);
			pStatement.setString(3, newMessage);
			pStatement.setString(4, id);
			
			int num = pStatement.executeUpdate();
			if(num == 1){
				modifyMessage.setBackNews("修改信息成功");
				modifyMessage.setModifyRegisterMessageOk(true);
				modifyMessage.setNewEmail(newEmail);
				modifyMessage.setNewPhone(newPhone);
				modifyMessage.setNewMessage(newMessage);
			}
			else
				modifyMessage.setBackNews("更新失败，信息填写不完整或信息中含有非法字符");
			connection.close();
		} catch (Exception e) {
			modifyMessage.setBackNews("信息更新失败");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/showModifyRegisterMess.jsp");
		dispatcher.forward(request, response);		
	}
}
