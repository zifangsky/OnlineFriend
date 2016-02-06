<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<%@ include file="head_member.txt"%>
</head>
<body>
	<font size=3>
		<center>
			<form action="helpModifyPassword" name="" method="post">
				<table>
				<tr><th>修改密码：</th></tr>
					<tr><td>当前密码：</td><td><input type=password name="oldPassword"></td></tr>
					<tr><td>新密码：</td><td><input type=password name="newPassword"></td></tr>							
				</table>	
				<br><input type="submit" value="提交">
			</form>
		</center>
	</font>
</body>
</html>