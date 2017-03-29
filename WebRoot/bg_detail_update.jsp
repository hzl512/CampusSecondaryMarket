<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.weicent.web.domain.Update"%>

<jsp:useBean id="UpdateDao" scope="page" class="com.weicent.web.dao.UpdateDao" />

<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--开发者QQ：2390201971-->
<html>
	<head>
		<title>应用更新详情</title>
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
									<strong>应用更新详情</strong>
								</div>
							</td>
						</tr>
					</table>
					<br>
					<form method="get" action="UpdateServlet">
						<table width="80%" border="1" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" bgcolor="CCCCCC" align="center">
							<%
								String id = request.getParameter("id").toString();
								Update t = UpdateDao.getUpdate(id);
							 
								String versionName = t.getVersionName();
							 
								String versionNumber = t.getVersionNumber();
							 
								String forcedUpdate = t.getForcedUpdate()==0?"否":"是";
							 
								String description = t.getDescription();
							 
								String url = t.getUrl();
							 
								String createTime = t.getCreateTime();
							 
								String size = t.getSize();
							 
							%>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">版本名称：</td>
								<td><%= versionName %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">版本号：</td>
								<td><%= versionNumber %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">是否强制更新：</td>
								<td><%= forcedUpdate %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">更新描述：</td>
								<td><%= description %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">下载链接：</td>
								<td><%= url %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">创建时间：</td>
								<td><%= createTime %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">更新包大小：</td>
								<td><%= size %></td>
							</tr>
							 
						</table>
					</form>
		</table>
	</body>
<h4><jsp:include page="bg_bottom.jsp" flush="true" /></h4>
</html>