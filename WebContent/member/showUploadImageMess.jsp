<%@page import="com.sun.xml.internal.bind.v2.model.core.ID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>    
    <%@ page import="com.zifangsky.OnlineFriend.model.member.Login"%>
    <%@ page import="com.zifangsky.OnlineFriend.model.member.UploadFile"%>      
<jsp:useBean id="login" type="com.zifangsky.OnlineFriend.model.member.Login" scope="session"/>
<jsp:useBean id="userImage" type="com.zifangsky.OnlineFriend.model.member.UploadFile" scope="request"/>
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
			<br><jsp:getProperty name="userImage" property="backNews"/>
		</font>
		<font size=3>
			<% if(userImage.isUploadFileOk()){ 
			%>
			<br><br>尊敬的会员：<jsp:getProperty name="login" property="id"/>，您上传的图像是：
			<br><img src=data/userfile/image/<jsp:getProperty name="userImage" property="savedFileName"/> width=180 height=120></img>
			<%}
			else{%>
			<br><a href="member/uploadImage.jsp">点击这里，重新上传</a>
			<%} %>
				
		</font>
	</center>
	

</body>
</html>