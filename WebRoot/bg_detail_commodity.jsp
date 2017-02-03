<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.weicent.web.domain.Commodity"%>

<jsp:useBean id="CommodityDao" scope="page" class="com.weicent.web.dao.CommodityDao" />
<jsp:useBean id="CategoryDao" scope="page" class="com.weicent.web.dao.CategoryDao" />
<jsp:useBean id="UsersDao" scope="page" class="com.weicent.web.dao.UsersDao" />

<META http-equiv="Content-Type" content="text/html; charset=UTF-8">

<html>
	<head>
		<title>商品详情</title>
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
									<strong>商品详情</strong>
								</div>
							</td>
						</tr>
					</table>
					<br>
					<form method="get" action="CommodityServlet">
						<table width="80%" border="1" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" bgcolor="CCCCCC" align="center">
							<%
								String id = request.getParameter("id").toString();
								Commodity t = CommodityDao.getCommodity(id);
							 
								String commodityPhone = String.valueOf(t.getCommodityPhone());
							 
								String commodityQQ = String.valueOf(t.getCommodityQQ());
							 
								String commodityAddTime = t.getCommodityAddTime();
							 
							 	String commodityStatus ="";
								if(t.getCommodityStatus()==0){
									commodityStatus="上架";
								}else if(t.getCommodityStatus()==1){
									commodityStatus="下架";
								}else{
									commodityStatus="违规";
								}
								
								String commodityViews = String.valueOf(t.getCommodityViews());
							 
								String usersID =UsersDao.getUsers(String.valueOf(t.getUsersID())).getUsersName();
							 
								String commodityImageUrl = t.getCommodityImageUrl();
							 
								String commodityName = t.getCommodityName();
							 
								String commodityDetail = t.getCommodityDetail();
							 
								String commodityAddress = t.getCommodityAddress();
							 
								String commodityPrice = t.getCommodityPrice();
							 
								String categoryID =CategoryDao.getCategory(String.valueOf(t.getCategoryID())).getCategoryName() ;
							 
								String commodityBargain = "";
								if(t.getCommodityBargain()==0){
									commodityBargain="可小刀";
								}else{
									commodityBargain="不小刀";
								}
							%>
							  
							<tr bgcolor="#FFFFFF">
								<td align="right">用户：</td>
								<td><%= usersID %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">商品名称：</td>
								<td><%= commodityName %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">商品详情：</td>
								<td><%= commodityDetail %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">商品图片：</td>
								<td>
								<input  type="image" src="<%=commodityImageUrl%>" width="140" height="126" />
								</td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">交易地点：</td>
								<td><%= commodityAddress %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">商品价格：</td>
								<td><%= commodityPrice %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">分类：</td>
								<td><%= categoryID %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">讲价：</td>
								<td><%= commodityBargain %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">联系电话：</td>
								<td><%= commodityPhone %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">QQ：</td>
								<td><%= commodityQQ %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">添加时间：</td>
								<td><%= commodityAddTime %></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">浏览次数：</td>
								<td><%= commodityViews %></td>
							</tr>
							
							<tr bgcolor="#FFFFFF">
								<td align="right">状态：</td>
								<td><%= commodityStatus %></td>
							</tr>
							 
						</table>
					</form>
		</table>
	</body>
<h4><jsp:include page="bg_bottom.jsp" flush="true" /></h4>
</html>