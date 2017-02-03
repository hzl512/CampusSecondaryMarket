<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.weicent.web.domain.Category"%>

<jsp:useBean id="CategoryDao" scope="page" class="com.weicent.web.dao.CategoryDao" />

<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--开发者QQ：2390201971-->
<html>
	<head>
		<title>Category详情</title>
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
									<strong>Category详情</strong>
								</div>
							</td>
						</tr>
					</table>
					<br>
					<form method="get" action="CategoryServlet">
						<table width="80%" border="1" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" bgcolor="CCCCCC" align="center">
							<%
								String id = request.getParameter("id").toString();
								Category t = CategoryDao.getCategory(id);
							 
								String categoryName = t.getCategoryName();
							 
								String categoryRemark = t.getCategoryRemark();
							 
							%>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">categoryName：</td>
								<td><%= categoryName %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">categoryRemark：</td>
								<td><%= categoryRemark %></td>
							</tr>
							 
						</table>
					</form>
		</table>
	</body>
<h4><jsp:include page="bg_bottom.jsp" flush="true" /></h4>
</html>