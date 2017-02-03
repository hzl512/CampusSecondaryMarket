<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.weicent.web.domain.*"%>

<jsp:useBean id="ManageDao" scope="page" class="com.weicent.web.dao.ManageDao" />

<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--开发者QQ：2390201971-->
<html>
	<head>
		<title>修改管理员</title>
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
									<strong>修改管理员</strong>
								</div>
							</td>
						</tr>
					</table>
					<br>
					<form method="get" action="ManageServlet">
						<table width="80%" border="1" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" bgcolor="CCCCCC" align="center">
							<%
								String id = request.getParameter("id").toString();
								Manage t = ManageDao.getManage(id);
							 
								String manageName = t.getManageName();
							 
								String managePassWord = t.getManagePassWord();
							 
								String manageRemark = t.getManageRemark();
							 
							%>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">管理员账号：</td>
								<td><input type="text" name="manageName" value=<%=manageName%> /></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">管理员密码：</td>
								<td><input type="text" name="managePassWord" value=<%=managePassWord%> /></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">备注：</td>
								<td><input type="text" name="manageRemark" value=<%=manageRemark%> /></td>
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