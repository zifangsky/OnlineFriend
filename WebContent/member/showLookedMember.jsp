<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>    <%@ page import="com.zifangsky.OnlineFriend.model.member.MemberInform"%>
<jsp:useBean id="memberInform" type="com.zifangsky.OnlineFriend.model.member.MemberInform" scope="request"/>
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
			<br><jsp:getProperty name="memberInform" property="backNews" />
		</font>
		<% if(memberInform.isSelectOk()){ %>
			<font size=3>			
				<table border=2>
					<tr>
						<th>会员名</th>
						<th>email</th>
						<th>电话</th>
						<th>简历和交友标准</th>
						<th>用户照片</th>
					</tr>
					<tr>
						<td><jsp:getProperty name="memberInform" property="id" /></td>
						<td><jsp:getProperty name="memberInform" property="email" /></td>
						<td><jsp:getProperty name="memberInform" property="phone" /></td>
						<td><jsp:getProperty name="memberInform" property="message" /></td>
						<td><img src=data/userfile/image/<jsp:getProperty name="memberInform" property="pic" /> width=100 height=100></img></td>
					</tr>
					
				</table>	
			</font>
		<% } 
		else{%>
		<br><br><a href="member/choiceLookType.jsp">点击这里，重新查询</a>
		<%} %>
	</center>
</body>
</html>