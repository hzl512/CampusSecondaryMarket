<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.weicent.web.domain.*"%>

<jsp:useBean id="ProfessionDao" scope="page" class="com.weicent.web.dao.ProfessionDao" />
<jsp:useBean id="DepartmentsDao" scope="page" class="com.weicent.web.dao.DepartmentsDao" />

<META http-equiv="Content-Type" content="text/html; charset=UTF-8">

<html>
	<head>
		<title>修改专业</title>
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
									<strong>修改专业</strong>
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
							 
								String departmentsID =String.valueOf(t.getDepartmentsID()) ;
							 
								String professionName = t.getProfessionName();
							 
								String professionRemark = t.getProfessionRemark();
								
								Departments model=DepartmentsDao.getDepartments(departmentsID);
								String str=model.getDepartmentsName();
								ArrayList<Departments> list = DepartmentsDao.getDepartmentsListAll("");
							 
							%>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">院系：</td>
								<td>
								<select name="departmentsID">
									<option value="<%=departmentsID%>">请选择</option>
									<%
										for (int i = 0; i < list.size(); i++) {
											Departments temp = (Departments) list.get(i);
											if (String.valueOf(temp.getId()).equals(departmentsID)) {
									%>
									<option value="<%=departmentsID%>" selected="selected"><%=str%></option>
									<%
										} else {
									%>
									<option value="<%=temp.getId()%>"><%=temp.getDepartmentsName()%></option>
									<%
										}
										}
									%>
							</select></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">专业名称：</td>
								<td><input type="text" name="professionName" value=<%=professionName%> /></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">备注：</td>
								<td><input type="text" name="professionRemark" value=<%=professionRemark%> /></td>
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