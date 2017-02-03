<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.weicent.web.domain.Profession"%>

<jsp:useBean id="ProfessionDao" scope="page" class="com.weicent.web.dao.ProfessionDao" />
<jsp:useBean id="DepartmentsDao" scope="page" class="com.weicent.web.dao.DepartmentsDao" />

<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<html>
	<head>
		<title>Profession详情</title>
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
									<strong>专业详情</strong>
								</div>
							</td>
						</tr>
					</table>
					<br>
					<form method="get" action="ProfessionServlet">
						<table width="80%" border="1" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" bgcolor="CCCCCC" align="center">
							<%
								String id = request.getParameter("id").toString();
								Profession t = ProfessionDao.getProfession(id);
							 
								String departmentsID =DepartmentsDao.getDepartments(String.valueOf(t.getDepartmentsID())).getDepartmentsName()  ;
							 
								String professionName = t.getProfessionName();
							 
								String professionRemark = t.getProfessionRemark();
							 
							%>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">院系：</td>
								<td><%= departmentsID %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">专业名称：</td>
								<td><%= professionName %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">备注：</td>
								<td><%= professionRemark %></td>
							</tr>
							 
						</table>
					</form>
		</table>
	</body>
<h4><jsp:include page="bg_bottom.jsp" flush="true" /></h4>
</html>