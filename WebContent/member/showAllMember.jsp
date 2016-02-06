<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    <%@ page import="com.zifangsky.OnlineFriend.model.member.ShowByPage"%>
<jsp:useBean id="showAllMember" type="com.zifangsky.OnlineFriend.model.member.ShowByPage" scope="session"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<%@ include file="head_member.txt"%>
</head>
<body>	
	<center>
		<font size=4 color=blue>
			<br>会员的详细信息：
		</font>
		<font size=3>			
			<table border=2>
				<tr>
					<th>会员名</th>
					<th>email</th>
					<th>电话</th>
					<th>简历和交友标准</th>
					<th>用户照片</th>
				</tr>
				<jsp:getProperty name="showAllMember" property="presentPageResult" />
			</table>	
			<br>每页最多显示 <font color=red><jsp:getProperty name="showAllMember" property="pageSize" /></font> 条信息
			<br>当前显示第 <font color=red><jsp:getProperty name="showAllMember" property="showPage" /></font> 页，共有
			<font color=red><jsp:getProperty name="showAllMember" property="pageAllCount"/></font> 页
			
			<br>单击“上一页”或“下一页”按钮查看信息
			<table>
				<tr>
					<td>
						<form action="helpShowMember" method="post">
							<input type="hidden" value=<%=showAllMember.getShowPage()-1 %> name="showPage" size=5>
							<input type="submit" value="上一页">
						</form>	
					
					</td>
					<td>
						<form action="helpShowMember" method="post">
							<input type="hidden" value=<%=showAllMember.getShowPage()+1 %> name="showPage" size=5>
							<input type="submit" value="下一页">
						</form>	
					
					</td>
					<td>
						<form action="helpShowMember" method="post">
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