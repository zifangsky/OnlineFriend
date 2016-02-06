<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="com.zifangsky.OnlineFriend.model.member.Login"%>
<%@ page import="com.zifangsky.OnlineFriend.model.article.Article"%>
<jsp:useBean id="login" type="com.zifangsky.OnlineFriend.model.member.Login" scope="session"/>
<jsp:useBean id="toModifyArticle" type="com.zifangsky.OnlineFriend.model.article.Article" scope="request"/>
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
			<form action="ArticleModify" method="post">
				<table>
					<caption>带<font color=red> * </font>号项为必填项</caption>
					<tr><td align=left><input type=hidden name="articleId" value=<jsp:getProperty name="toModifyArticle" property="id"/>></td></tr>
					<tr><td align=left><input type=hidden name="author" value=<jsp:getProperty name="toModifyArticle" property="author"/>></td></tr>
					<tr><td align=left>标题：<input type=text name="title" size=40 maxlength=40 value=<jsp:getProperty name="toModifyArticle" property="title"/>><font color=red> *</font></td></tr>
					<tr><td align=left>作者：<font color=#00c73a><jsp:getProperty name="login" property="id"/></font></td></tr>
				</table>	
				<table>
					<tr><td align=left>正文：</td></tr>
					<tr>
						<td align=left><TextArea name="content" rows="20" cols="80"><jsp:getProperty name="toModifyArticle" property="content"/></TextArea></td>
					</tr>
					<tr><td align=center><input type=submit value="修改"></td></tr>
				</table>
			</form>
		</center>
	</font>
</body>
</html>