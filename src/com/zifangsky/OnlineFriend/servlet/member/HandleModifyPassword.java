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
import com.zifangsky.OnlineFriend.model.member.ModifyPassword;
import com.zifangsky.OnlineFriend.util.DbConn;
import com.zifangsky.OnlineFriend.util.StringUtil;

public class HandleModifyPassword extends HttpServlet{
	
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
			modifyPassword(request,response);
		}		
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doPost(request, response);
	}

	private void modifyPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		Login loginBean = (Login) session.getAttribute("login");
		
		String id = loginBean.getId();  //获取已登用户录的id
		ModifyPassword passwordBean = new ModifyPassword();
		request.setAttribute("password", passwordBean);
		//获取输入的参数
		String oldPassword = StringUtil.xssEncode(request.getParameter("oldPassword").trim());
		String newPassword = StringUtil.xssEncode(request.getParameter("newPassword").trim());
		
		//开始更新
		Connection connection = DbConn.getConnection();
		try {
			//判断旧密码是否正确
			PreparedStatement pStatementQuery = connection.prepareStatement("select id from member where id=? and password =?");
			pStatementQuery.setString(1, id);
			pStatementQuery.setString(2, oldPassword);
			ResultSet resultSet = pStatementQuery.executeQuery();
			
			if(resultSet.next()){
				//如果正确则更新密码
				PreparedStatement pStatementUpdate = connection.prepareStatement("update member set password=? where id=?");
				pStatementUpdate.setString(1, newPassword);
				pStatementUpdate.setString(2, id);
				int num = pStatementUpdate.executeUpdate();
				//执行成功返回1
				if(num == 1){
					passwordBean.setBackNews("密码更新成功");
					passwordBean.setNewPassword(newPassword);
					passwordBean.setModifyPasswordOk(true);
					session.invalidate();  //更新密码后消除session，需要重新登录
				}
				else
					passwordBean.setBackNews("密码更新失败");			
			}
			else
				passwordBean.setBackNews("密码更新失败");			
			connection.close();
		} catch (Exception e) {
			passwordBean.setBackNews("密码更新失败");			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/showNewPasswordMess.jsp");
		dispatcher.forward(request, response);
	}
}
