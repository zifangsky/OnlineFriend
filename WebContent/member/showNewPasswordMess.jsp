<%@page import="com.sun.xml.internal.bind.v2.model.core.ID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
    <%@ page import="com.zifangsky.OnlineFriend.model.member.ModifyPassword"%>
<jsp:useBean id="password" type="com.zifangsky.OnlineFriend.model.member.ModifyPassword" scope="request"/>
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
			<br><jsp:getProperty name="password" property="backNews"/>
		</font>
		<font size=3>
			<% if(password.isModifyPasswordOk()){ %>
			<br><br>修改密码成功，新密码是：<jsp:getProperty name="password" property="newPassword"/>
			<br><a href="login.jsp">点击这里，重新登录</a>
			<%}
			else{%>
			<br><a href="member/modifyPassword.jsp">点击这里，重新修改</a>
			<%} %>
				
		</font>
	</center>
	

</body>
</html>