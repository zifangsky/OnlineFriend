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
import com.sun.rowset.CachedRowSetImpl;
import com.zifangsky.OnlineFriend.model.member.Login;
import com.zifangsky.OnlineFriend.model.member.MemberInform;
import com.zifangsky.OnlineFriend.model.member.ShowByPage;
import com.zifangsky.OnlineFriend.util.DbConn;
import com.zifangsky.OnlineFriend.util.StringUtil;

public class HandleShowMember extends HttpServlet{
	private CachedRowSetImpl rowSet = null;
	
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
		else
			continueDoPost(request,response);  //显示全体成员信息
	
	}

	private void continueDoPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		ShowByPage showByPageBean = null;
		//因为涉及到翻页问题，需要这个bean长期存在，scope设为session
		try {
			showByPageBean = (ShowByPage) session.getAttribute("showAllMember");
			if(showByPageBean == null){
				showByPageBean = new ShowByPage();
				session.setAttribute("showAllMember", showByPageBean);
			}
		} catch (Exception e) {
			showByPageBean = new ShowByPage();
			session.setAttribute("showAllMember", showByPageBean);
		}
		
		showByPageBean.setPageSize(3);  //每页显示3条数据
		int showPage = Integer.parseInt(StringUtil.xssEncode(request.getParameter("showPage")));
		if(showPage > showByPageBean.getPageAllCount())
			showPage = 1;
		else if(showPage < 1)
			showPage = showByPageBean.getPageAllCount();
		showByPageBean.setShowPage(showPage);
		
		Connection connection = DbConn.getConnection();
		try {
			PreparedStatement pStatement = connection.prepareStatement("select id,email,phone,message,pic from member");
			ResultSet resultSet = pStatement.executeQuery();
			rowSet = new CachedRowSetImpl();
			rowSet.populate(resultSet);			
			rowSet.last();  //指向结果集的末尾
			int m = rowSet.getRow();  //总行数
			int n = showByPageBean.getPageSize();  //每页显示的记录数
			if(m%n ==0)
				showByPageBean.setPageAllCount(m/n);
			else
				showByPageBean.setPageAllCount(m/n + 1);
			
			int pageSize = showByPageBean.getPageSize();
			showByPageBean.setPresentPageResult(StringUtil.showMember(showPage, pageSize, rowSet));
			
			resultSet.close();
			connection.close();
		} catch (Exception e) {

		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/showAllMember.jsp");
		dispatcher.forward(request, response);
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		HttpSession session = request.getSession(true);
		Login loginBean = (Login) session.getAttribute("login");
		
		if(loginBean == null)
			response.sendRedirect("login.jsp");
		else
			continueDoGet(request, response);  //显示指定成员信息
	}

	private void continueDoGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		//指定成员的id
		String id = StringUtil.xssEncode(request.getParameter("selectedId"));
		MemberInform memberInformBean = new MemberInform();
		request.setAttribute("memberInform", memberInformBean);
		
		Connection connection = DbConn.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select id,email,phone,message,pic from member where id=?");
			preparedStatement.setString(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				memberInformBean.setBackNews("查询到的会员信息：");
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
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/showLookedMember.jsp");
		dispatcher.forward(request, response);
	}	
}
