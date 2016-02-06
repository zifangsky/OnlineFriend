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
<%@ include file="head.txt"%>
<script type="text/javascript">
	//刷新验证码
	function changeImg(){
		document.getElementById("validateCodeImg").src="helpDrawValidateCode?t=" + Math.random();
	}
</script>
</head>
<body>
	<font size=3>
		<center>
			<form action="helpLogin" name="" method="post">
				<table>
				<tr><th>请您登陆：</th></tr>
					<tr><td align=left>会员名称：</td><td><input type=text name="id"></td></tr>
					<tr><td align=left>输入密码：</td><td><input type=password name="password"></td></tr>				
				</table>	
				<br>验证码：<input type="text" name="validateCode" style="width:50px">
				<img alt="看不清？换一张" src="helpDrawValidateCode" id="validateCodeImg" onclick="changeImg()">							
				<br><input type="submit" value="提交">
			</form>
		</center>
	</font>
</body>
</html>