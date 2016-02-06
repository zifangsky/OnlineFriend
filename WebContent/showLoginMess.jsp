<%@page import="com.sun.xml.internal.bind.v2.model.core.ID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
    <%@ page import="com.zifangsky.OnlineFriend.model.member.Login"%>
<jsp:useBean id="login" type="com.zifangsky.OnlineFriend.model.member.Login" scope="session"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<%@ include file="head.txt"%>
</head>
<body>	
	<center>
		<font size=4 color=blue>
			<br><jsp:getProperty name="login" property="backNews"/>
		</font>
		<font size=3>
			<% if(login.isLoginSuccess()){ %>
			<br><br>欢迎回来，尊敬的会员：<jsp:getProperty name="login" property="id"/>
			<%}
			else{%>
			<br><a href="login.jsp">点击这里，重新登录</a>
			<%} %>
				
		</font>
	</center>
	

</body>
</html>