<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.weicent.web.domain.*"%>
<jsp:useBean id="CategoryDao" scope="page" class="com.weicent.web.dao.CategoryDao" />

<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	List<String> statusList=new ArrayList<String>();
	statusList.add(0,"上架");
	statusList.add(1,"下架");
	statusList.add(2,"违规");
	List<String> bargainList=new ArrayList<String>();
	bargainList.add(0,"可小刀");
	bargainList.add(1,"不可刀");
	
	ArrayList<Category> list = CategoryDao.getCategoryListAll("");
 %>
<html>
	<head>
		<title>添加商品</title>
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
									<strong>添加商品</strong>
								</div>
							</td>
						</tr>
					</table>
					<br>
				<form method="get" action="CommodityServlet">
					<table width="80%" border="1" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" bgcolor="CCCCCC" align="center" >
				 
					<tr bgcolor="#FFFFFF">
						<td align="right">usersID：</td>
						<td><input type="text" name="usersID" /></td>
					</tr>
				 
					<tr bgcolor="#FFFFFF">
						<td align="right">商品名称：</td>
						<td><input type="text" name="commodityName" /></td>
					</tr>
				 
					<tr bgcolor="#FFFFFF">
						<td align="right">商品详情：</td>
						<td><input type="text" name="commodityDetail" /></td>
					</tr>
				 				 
					<tr bgcolor="#FFFFFF">
						<td align="right">商品图片：</td>
						<td>
						<input name="commodityImageUrl" type="file" >
						</td>
					</tr>
					
					<tr bgcolor="#FFFFFF">
						<td align="right">交易地点：</td>
						<td><input type="text" name="commodityAddress" /></td>
					</tr>
				 
					<tr bgcolor="#FFFFFF">
						<td align="right">价格：</td>
						<td><input type="text" name="commodityPrice" /></td>
					</tr>
				 
					<tr bgcolor="#FFFFFF">
						<td align="right">分类：</td>
						<td>
							<select name="categoryID" >
								<option >请选择</option>
									<%
										for (int i = 0; i < list.size(); i++) {
											Category temp = (Category) list.get(i);
									%>
									<option value="<%=temp.getId()%>"><%=temp.getCategoryName()%></option>
									<%
										}
									%>
							</select>
						</td>
					</tr>
				 
					<tr bgcolor="#FFFFFF">
						<td align="right">讲价：</td>
						<td>
							<select name="commodityBargain">
								<option value="<%=0%>">请选择</option>
								<%
									for(int i=0;i<bargainList.size();i++){
										String s=bargainList.get(i).toString();
								 %>
								 <option value="<%=i%>"><%=s%></option>
								 <%
								 	}
								 %>
							 </select>
						</td>
					</tr>
				 
					<tr bgcolor="#FFFFFF">
						<td align="right">联系电话：</td>
						<td><input type="text" name="commodityPhone" /></td>
					</tr>
				 
					<tr bgcolor="#FFFFFF">
						<td align="right">QQ：</td>
						<td><input type="text" name="commodityQQ" /></td>
					</tr>
				 
					<tr bgcolor="#FFFFFF">
						<td align="right">状态：</td>
						<td>
							<select name="commodityStatus">
								<option value="<%=0%>">请选择</option>
								<%
									for(int i=0;i<statusList.size();i++){
										String s=statusList.get(i).toString();
								 %>
								 <option value="<%=i%>"><%=s%></option>
								 <%
								 	}
								 %>
							 </select>
						</td>
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
