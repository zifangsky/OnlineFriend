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
			<form action="helpShowMember" method="post">
				<br>分页显示全体成员：
				<input type="hidden" value="1" name="showPage" size=5>
				<input type="submit" value="显示">
			</form>
			<form action="helpShowMember" method="get">
				<br>输入要查找的成员名：
				<input type="text" name="selectedId" size=5>
				<input type="submit" value="显示">
			</form>
		</center>
	</font>
</body>
</html>