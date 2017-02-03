<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.weicent.web.domain.Users"%>

<jsp:useBean id="UsersDao" scope="page" class="com.weicent.web.dao.UsersDao" />
<jsp:useBean id="ProfessionDao" scope="page" class="com.weicent.web.dao.ProfessionDao" />
<jsp:useBean id="DepartmentsDao" scope="page" class="com.weicent.web.dao.DepartmentsDao" />

<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--开发者QQ：2390201971-->
<html>
	<head>
		<title>Users详情</title>
		<link rel="stylesheet" type="text/css" href="css/css.css">
	</head>
	<body>
		<jsp:include page="bg_top.jsp" flush="true" />
		<table width="1000" border="0" align="center" cellpadding="0"
			bgcolor="#FFFFFF" cellspacing="0">
			<tr>
				<td width="20%" valign="top">
				<jsp:include page="bg_left.jsp" flush="true" />
				</td>
				<td>
					<table width="610" height="25" border="0" cellpadding="0"
						align="center" cellspacing="0" background="image/bg_02.jpg">
						<tr>
							<td>
								<div align="center">
									<strong>用户详情</strong>
								</div>
							</td>
						</tr>
					</table>
					<br>
					<form method="get" action="UsersServlet">
						<table width="80%" border="1" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" bgcolor="CCCCCC" align="center">
							<%
								String id = request.getParameter("id").toString();
								Users t = UsersDao.getUsers(id);
							 
								String usersGrade = String.valueOf(t.getUsersGrade());
							 
								String usersName = t.getUsersName();
							 
								String usersPwd = t.getUsersPwd();
							 
								String usersAddTime = t.getUsersAddTime();
							 
								String usersNickName = t.getUsersNickName();
							 
								String usersPhone = t.getUsersPhone();
							 
								String usersQQ = t.getUsersQQ();
							 
								String departmentsID = DepartmentsDao.getDepartments(String.valueOf(t.getDepartmentsID())).getDepartmentsName();
							 
								String professionID = ProfessionDao.getProfession(String.valueOf(t.getProfessionID())).getProfessionName();
							 
							%>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">年级：</td>
								<td><%= usersGrade %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">用户账号：</td>
								<td><%= usersName %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">用户密码：</td>
								<td><%= usersPwd %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">注册时间：</td>
								<td><%= usersAddTime %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">用户昵称：</td>
								<td><%= usersNickName %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">联系电话：</td>
								<td><%= usersPhone %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">联系QQ：</td>
								<td><%= usersQQ %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">院系：</td>
								<td><%= departmentsID %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">专业：</td>
								<td><%= professionID %></td>
							</tr>
							 
						</table>
					</form>
		</table>
	</body>
<h4><jsp:include page="bg_bottom.jsp" flush="true" /></h4>
</html>