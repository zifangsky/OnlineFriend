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
			<br>更新会员头像
			<br>请选择要上传的图片文件：
			<form action="helpUploadImage" method="post" enctype="multipart/form-data">				
				<input type=file name="picture" size=40>				
				<br><input type="submit" value="上传">
			</form>
		</center>
	</font>
</body>
</html>