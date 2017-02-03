<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.weicent.web.domain.*"%>

<jsp:useBean id="DepartmentsDao" scope="page" class="com.weicent.web.dao.DepartmentsDao" />

<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--开发者QQ：2390201971-->
<html>
	<head>
		<title>修改院系</title>
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
									<strong>修改院系</strong>
								</div>
							</td>
						</tr>
					</table>
					<br>
					<form method="get" action="DepartmentsServlet">
						<table width="80%" border="1" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" bgcolor="CCCCCC" align="center">
							<%
								String id = request.getParameter("id").toString();
								Departments t = DepartmentsDao.getDepartments(id);
							 
								String departmentsName = t.getDepartmentsName();
							 
								String departmentsRemark = t.getDepartmentsRemark();
							 
							%>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">院系名称：</td>
								<td><input type="text" name="departmentsName" value=<%=departmentsName%> /></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">备注：</td>
								<td><input type="text" name="departmentsRemark" value=<%=departmentsRemark%> /></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right"><input type="reset" value="重填" /></td>
								<td><input type="submit" value="确定" /></td>
							</tr>
						</table>
						<input type="hidden" name="action" value="update" /> 
						<input type="hidden" name="id" value="<%=id.trim()%>" />
					</form>
		</table>
	</body>
<h4><jsp:include page="bg_bottom.jsp" flush="true" /></h4>
</html>