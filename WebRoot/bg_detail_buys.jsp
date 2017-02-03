<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.weicent.web.domain.Buys"%>

<jsp:useBean id="BuysDao" scope="page" class="com.weicent.web.dao.BuysDao" />
<jsp:useBean id="UsersDao" scope="page" class="com.weicent.web.dao.UsersDao" />

<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<html>
	<head>
		<title>求购详情</title>
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
									<strong>求购详情</strong>
								</div>
							</td>
						</tr>
					</table>
					<br>
					<form method="get" action="BuysServlet">
						<table width="80%" border="1" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" bgcolor="CCCCCC" align="center">
							<%
								String id = request.getParameter("id").toString();
								Buys t = BuysDao.getBuys(id);
							 
								String usersID = UsersDao.getUsers(String.valueOf(t.getUsersID())).getUsersName();
							 
								String buysName = t.getBuysName();
							 
								String buysPrice = t.getBuysPrice();
							 
								String buysAddress = t.getBuysAddress();
							 
								String buysDetail = t.getBuysDetail();
							 
								String buysPhone = t.getBuysPhone();
							 
								String buysAddTime = t.getBuysAddTime();
							 
								String buysStatus ="";
								if(t.getBuysStatus()==0){
									buysStatus="上架";
								}else if(t.getBuysStatus()==1){
									buysStatus="下架";
								}else{
									buysStatus="违规";
								}
							 
								String buysQQ = String.valueOf(t.getBuysQQ());
							 
							%>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">发布者：</td>
								<td><%= usersID %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">商品名称：</td>
								<td><%= buysName %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">商品详情：</td>
								<td><%= buysDetail %></td>
							</tr>
														 
							<tr bgcolor="#FFFFFF">
								<td align="right">期望价格：</td>
								<td><%= buysPrice %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">交易地点：</td>
								<td><%= buysAddress %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">联系电话：</td>
								<td><%= buysPhone %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">添加时间：</td>
								<td><%= buysAddTime %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">状态：</td>
								<td><%= buysStatus %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">联系QQ：</td>
								<td><%= buysQQ %></td>
							</tr>
							 
						</table>
					</form>
		</table>
	</body>
<h4><jsp:include page="bg_bottom.jsp" flush="true" /></h4>
</html>