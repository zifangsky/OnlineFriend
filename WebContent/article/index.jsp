<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    <%@ page import="com.zifangsky.OnlineFriend.model.article.ShowByPage"%>
<jsp:useBean id="showAllTitle" type="com.zifangsky.OnlineFriend.model.article.ShowByPage" scope="session"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<%@ include file="head_article.txt"%>
</head>
<body>	
	<center>
		<font size=3>
			<table border=0 cellspacing=5>
				<tr>
					<td align=right>
						<form action="article/newArticle.jsp" method="get">
							<input type="submit" value="新文章">
						</form>				
					</td>
				</tr>
				<jsp:getProperty name="showAllTitle" property="presentPageResult" />
			</table>
			<br>每页最多显示 <font color=red><jsp:getProperty name="showAllTitle" property="pageSize" /></font> 条信息
			<br>当前显示第 <font color=red><jsp:getProperty name="showAllTitle" property="showPage" /></font> 页，共有
			<font color=red><jsp:getProperty name="showAllTitle" property="pageAllCount"/></font> 页
			
			<br>单击“上一页”或“下一页”按钮查看信息
			<table>
				<tr>
					<td>
						<form action="ArticleShowList" method="get">
							<input type="hidden" value=<%=showAllTitle.getShowPage()-1 %> name="showPage" size=5>
							<input type="submit" value="上一页">
						</form>	
					
					</td>
					<td>
						<form action="ArticleShowList" method="get">
							<input type="hidden" value=<%=showAllTitle.getShowPage()+1 %> name="showPage" size=5>
							<input type="submit" value="下一页">
						</form>	
					
					</td>
					<td>
						<form action="ArticleShowList" method="post">
							输入页码：<input type="text" name="showPage" size=5>
							<input type="submit" value="提交">
						</form>		
					</td>
				</tr>		
			</table>		
		</font>
	</center>
</body>
</html>