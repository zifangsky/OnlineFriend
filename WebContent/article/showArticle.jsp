<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <%@ page import="com.zifangsky.OnlineFriend.model.article.Article"%>
<jsp:useBean id="article" type="com.zifangsky.OnlineFriend.model.article.Article" scope="request"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<base href="<%=basePath%>">
	<%@ include file="head_article.txt"%>
</head>
<body>	
	<center><br>		
		<table border=0 cellspacing=10>
			<tr>
				<td align=center><font size=5><jsp:getProperty name="article" property="title" /></font></td>
			</tr>
			<tr>	
				<td align=left><font size=2><font color=#cea000>来源：</font><font color=#00c73a><jsp:getProperty name="article" property="author" /></font></font></td>
			</tr>
			<tr>	
				<td align=left><font size=3><jsp:getProperty name="article" property="content" /></font></td>						
			</tr>
			<tr>
				<td align=right>
						<form action="ArticleModify" method="get">
							<input type="hidden" name="articleId" value=<jsp:getProperty name="article" property="id" />>
							<input type="hidden" name="author" value=<jsp:getProperty name="article" property="author" />>
							<input type="submit" value="修改">
						</form>					
				</td>
				<td align=right>
						<form action="ArticleDelete" method="post">
							<input type="hidden" name="articleId" value=<jsp:getProperty name="article" property="id" />>
							<input type="hidden" name="author" value=<jsp:getProperty name="article" property="author" />>
							<input type="submit" value="删除">
						</form>					
				</td>
			</tr>
		</table>	
	</center>
</body>
</html>