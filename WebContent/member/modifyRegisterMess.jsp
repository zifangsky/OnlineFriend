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
			<form action="helpModifyRegisterMess" name="" method="post">
				<table>
					<tr><td>新的电子邮箱：</td><td><input type=text name="newEmail"></td></tr>
					<tr><td>新的联系电话：</td><td><input type=text name="newPhone"></td></tr>
				</table>	
				<table>
					<tr><td>新的简历和交友标准：</td></tr>
					<tr>
						<td><TextArea name="newMessage" rows="6" cols="30"></TextArea></td>
					</tr>
					<tr><td><input type=submit value="提交修改"></td></tr>
					<tr><td><input type=reset value="重置"></td></tr>
				</table>
			</form>
		</center>
	</font>

</body>
</html>