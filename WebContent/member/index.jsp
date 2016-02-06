<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    <%@ page import="com.zifangsky.OnlineFriend.model.member.MemberInform"%>
<jsp:useBean id="loginedInform" type="com.zifangsky.OnlineFriend.model.member.MemberInform" scope="request"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<%@ include file="head_member.txt"%>
</head>
<body>	
	<center>
		<font size=4 color=blue>
			<br><jsp:getProperty name="loginedInform" property="backNews" />
		</font>
		<% if(loginedInform.isSelectOk()){ %>
			<font size=3>			
				<br>会员名：<jsp:getProperty name="loginedInform" property="id" />
				<br>邮箱：<jsp:getProperty name="loginedInform" property="email" />
				<br>电话：<jsp:getProperty name="loginedInform" property="phone" />
				<br>简历和交友标准：<jsp:getProperty name="loginedInform" property="message" />
				<br>照片：<img src=data/userfile/image/<jsp:getProperty name="loginedInform" property="pic" /> width=100 height=100></img>

			</font>
		<% } 
		else{%>
		<br><br><a href="login.jsp">点击这里，重新登录</a>
		<%} %>
	</center>
</body>
</html>