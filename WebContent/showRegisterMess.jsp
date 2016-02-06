<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    <%@ page import="com.zifangsky.OnlineFriend.model.member.Register"%>
<jsp:useBean id="register" type="com.zifangsky.OnlineFriend.model.member.Register" scope="request"/>
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
			<br><jsp:getProperty name="register" property="backNews"/>
		</font>
		<% if(register.isRegisterSuccess()){ %>
			<font size=3>
				<table>
					<tr><td align=left>注册的会员名称：</td><td><jsp:getProperty name="register" property="id"/></td></tr>
					<tr><td align=left>注册的电子邮箱：</td><td><jsp:getProperty name="register" property="email"/></td></tr>
					<tr><td align=left>注册的联系电话：</td><td><jsp:getProperty name="register" property="phone"/></td></tr>
				</table>	
				<table>
					<tr><td align=left>您的简历和交友标准：</td></tr>
					<tr>
						<td align=left><TextArea name="message" rows="6" cols="30"><jsp:getProperty name="register" property="message"/></TextArea></td>
					</tr>
				</table>
			</font>
		<%}
			else{%>
			<br><a href="register.jsp">点击这里，重新注册</a>
			<%} %>
	</center>
	

</body>
</html>