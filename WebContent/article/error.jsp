<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    <%@ page import="com.zifangsky.OnlineFriend.model.article.ErrorMessage"%>
<jsp:useBean id="error" type="com.zifangsky.OnlineFriend.model.article.ErrorMessage" scope="request"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<%@ include file="head_article.txt"%>
</head>
<body>	
	<center>
		<br><font size=5 color=blue><jsp:getProperty name="error" property="backNews" /></font>
	</center>
</body>
</html>