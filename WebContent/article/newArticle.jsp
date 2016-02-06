<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="com.zifangsky.OnlineFriend.model.member.Login"%>
<jsp:useBean id="login" type="com.zifangsky.OnlineFriend.model.member.Login" scope="session"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<base href="<%=basePath%>">
	<%@ include file="head_article.txt"%>
</head>
<body>
	<font size=3><br>
		<center>
			<form action="ArticleAdd" name="" method="post">
				<table>
					<caption>带<font color=red> * </font>号项为必填项</caption>
					<tr><td align=left>标题：<input type=text name="title" size=40 maxlength=40><font color=red> *</font></td></tr>
					<tr><td align=left>作者：<font color=#00c73a><jsp:getProperty name="login" property="id"/></font></td></tr>
				</table>	
				<table>
					<tr><td align=left>正文：</td></tr>
					<tr>
						<td align=left><TextArea name="content" rows="20" cols="80"></TextArea></td>
					</tr>
					<tr><td align=center><input type=submit value="发表"></td></tr>
				</table>
			</form>
		</center>
	</font>
</body>
</html>