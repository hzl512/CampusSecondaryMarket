<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.weicent.web.domain.*"%>

<jsp:useBean id="DepartmentsDao" scope="page" class="com.weicent.web.dao.DepartmentsDao" />

<META http-equiv="Content-Type" content="text/html; charset=UTF-8">

<html>
	<head>
		<title>添加专业</title>
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
									<strong>添加专业</strong>
								</div>
							</td>
						</tr>
					</table>
					<br>
				<form method="get" action="ProfessionServlet">
					<table width="80%" border="1" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" bgcolor="CCCCCC" align="center" >
				 			<%
								ArrayList<Departments> list = DepartmentsDao.getDepartmentsListAll("");
							%>
					<tr bgcolor="#FFFFFF">
						<td align="right">院系：</td>
						<td>
								<select name="departmentsID" >
										<option >
											请选择
										</option>
										<%
											for (int i = 0; i < list.size(); i++) {
												Departments temp = (Departments) list.get(i);
										%>
										<option value="<%=temp.getId()%>"><%=temp.getDepartmentsName()%></option>
										<%
											}
										%>

									</select>
						</td>
					</tr>
				 
					<tr bgcolor="#FFFFFF">
						<td align="right">专业名称：</td>
						<td><input type="text" name="professionName" /></td>
					</tr>
				 
					<tr bgcolor="#FFFFFF">
						<td align="right">备注：</td>
						<td><input type="text" name="professionRemark" /></td>
					</tr>
				 
					<tr bgcolor="#FFFFFF">
						<td align="right"><input type="reset" value="重填" /></td>
						<td><input type="submit" value="确定" /></td>
					</tr>
					</table>
					<input type="hidden" name="action" value="add" />
				</form>
				</tr>
			</table>
	</body>
<h4><jsp:include page="bg_bottom.jsp" flush="true" /></h4>	
</html>
