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
</head>
<body>
	<font size=3><br>
		<center>
			<form action="helpRegister" name="" method="post">
				<table>
					<caption>带<font color=red> * </font>号项为必填项</caption>
					<tr><td align=left>会员名称：</td><td><input type=text name="id"><font color=red> *</font></td></tr>
					<tr><td align=left>设置密码：</td><td><input type=password name="password"><font color=red> *</font></td></tr>
					<tr><td align=left>电子邮箱：</td><td><input type=text name="email"></td></tr>
					<tr><td align=left>联系电话：</td><td><input type=text name="phone"></td></tr>
				</table>	
				<table>
					<tr><td align=left>输入您的简历和交友标准：</td></tr>
					<tr>
						<td align=left><TextArea name="message" rows="6" cols="30"></TextArea></td>
					</tr>
					<tr><td align=center><input type=submit name="submit" value="注册"></td></tr>
				</table>
			</form>
		</center>
	</font>

</body>
</html>