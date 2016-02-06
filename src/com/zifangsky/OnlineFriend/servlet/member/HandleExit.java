package com.zifangsky.OnlineFriend.servlet.member;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zifangsky.OnlineFriend.model.member.Login;

public class HandleExit extends HttpServlet{
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");
		if(login == null){
			response.sendRedirect("login.jsp");
			return;
		}
		else{
			//注销
			session.invalidate();  //销毁session对象
			response.sendRedirect("index.jsp");  //重定向到首页
			return;
		}		
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doPost(request, response);
	}
}
