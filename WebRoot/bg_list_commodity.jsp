<%@ page language="java" import="java.util.*,java.sql.*,com.weicent.web.tool.*"
	pageEncoding="UTF-8"%>
<jsp:useBean id="UsersDao" scope="page" class="com.weicent.web.dao.UsersDao" />

<META http-equiv="Content-Type" content="text/html; charset=UTF-8">

 <script Language="JavaScript">
 function deleteCommodity(id) {
  if(confirm("确定要删除吗？")){
    window.location="CommodityServlet?id="+id+"&action=delete";
	}
  }
 </script>
<html>
	<head>
		<title>商品列表</title>
		<link rel="stylesheet" type="text/css" href="css/css.css">
	</head>
	<body>
		<jsp:include page="bg_top.jsp" flush="true" />
		<table width="1000" border="0" align="center" cellpadding="0" bgcolor="#FFFFFF"
			cellspacing="0">
			<tr>
				<td width="20%" valign="top">
					<jsp:include page="bg_left.jsp" flush="true" />
				</td>
				<td>
					<table width="610" height="25" border="0" cellpadding="0" align="center"
						cellspacing="0" background="image/bg_02.jpg">
						<tr>
							<td>
								<div align="center">
									<strong>商品列表</strong>
								</div>
							</td>
						</tr>
					</table>
					<br>
				<table width="80%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<table align="center" width="80%" border="1" cellpadding="1"
						cellspacing="1" bordercolor="#FFFFFF" bgcolor="CCCCCC">
						<tr bgcolor="#DCDCDC" height="25">
						 
							<td width="15%">
								<div align="center">
									id
								</div>
							</td>
						 
							<td width="15%">
								<div align="center">
									发布者
								</div>
							</td>
						 
							<td width="15%">
								<div align="center">
									商品名称
								</div>
							</td>
						 
							<td width="15%">
								<div align="center">
									商品详情
								</div>
							</td>
						 
							<td width="25%">
								<div align="center">
									操作
								</div>
							</td>
						</tr>
						<%
							//定义四个分页会用到的变量
							int pageSize = 10;
							int pageNow = 1;//默认显示第一页
							int rowCount = 0;//该值从数据库中查询
							int pageCount = 0;//该值是通过pageSize和rowCount
							//接受用户希望显示的页数（pageNow）
							String s_pageNow = request.getParameter("pageNow");
							if (s_pageNow != null) {
								//接收到了pageNow
								pageNow = Integer.parseInt(s_pageNow);
							}
							Connection con = new ConnDAO().getConnection();// 定义数据库连接对象
							try {
								Statement stmt = con.createStatement();
						%>
						<%
							ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM t_commodity");
								if (rs.next()) {
									rowCount = rs.getInt(1);
								}
								//计算pageCount
								if (rowCount % pageSize == 0) {
									pageCount = rowCount / pageSize;
								} else {
									pageCount = rowCount / pageSize + 1;
								}
								String sql = "SELECT * FROM t_commodity "+" ORDER BY "+"commodityAddTime DESC" + " limit " + pageSize * (pageNow - 1) + "," + pageSize;
								System.out.println(sql);
								rs = stmt.executeQuery(sql);
								if (rs != null) {//字段显示数最多5个，是因为内容页放不下，其他字段需在详情看
									while (rs.next()) {
									 
										String str1 = rs.getString(1).length() > 10 ? rs.getString(1).substring(0, 10)+ "..." : rs.getString(1);
																			 
										String str2 = UsersDao.getUsers(String.valueOf(rs.getString(2))).getUsersName().length() > 10 ? 
										UsersDao.getUsers(String.valueOf(rs.getString(2))).getUsersName().substring(0, 10)
										+ "..." : UsersDao.getUsers(String.valueOf(rs.getString(2))).getUsersName();
																			 
										String str4 = rs.getString(4).length() > 10 ? rs.getString(4).substring(0, 10)+ "..." : rs.getString(4);
																			 
										String str5 = rs.getString(5).length() > 10 ? rs.getString(5).substring(0, 10)+ "..." : rs.getString(5);
																			 
																			 
																			 
																			 
																			 
																			 
																			 
																			 
																			 
						%>
						<tr align="center" bgcolor="#FFFFFF">
							 
														<td><%=str1%></td>
							 
														<td><%=str2%></td>
							 
														<td><%=str4%></td>
							 
														<td><%=str5%></td>
							 
							 
							 
							 
							 
							 
							 
							 
							 
							<td>
								<a href="bg_detail_commodity.jsp?id=<%=str1%>">查看</a>
								<a href="bg_edit_commodity.jsp?id=<%=str1%>">编辑</a>
								<a href="javascript:deleteCommodity('<%=str1%>')">删除</a>
							</td>
						</tr>
						<%
							}
								}
								stmt.close();
							} catch (SQLException sqle) {
								out.println(sqle.getMessage());
							} catch (Exception e) {
								out.println(e.getMessage());
							} finally {
								try {
									if (con != null)
										con.close();
								} catch (SQLException sqle) {
									out.println(sqle.getMessage());
								} catch (Exception e) {
									out.println(e.getMessage());
								}
							}
						%>

					</table>
						<table width="80%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr bgcolor="#FFFFFF">
								<td>
								<%
									//上一页
									if (pageNow != 1) {
										out.println("<a href=bg_list_commodity.jsp?pageNow=" + (pageNow - 1)
												+ ">上一页</a>");
									}
									//显示超链接
									for (int i = 1; i <= pageCount; i++) {
										out.println("<a href=bg_list_commodity.jsp?pageNow=" + i + ">[" + i
												+ "]</a>");
									}
									//下一页
									if (pageNow != pageCount) {
										out.println("<a href=bg_list_commodity.jsp?pageNow=" + (pageNow + 1)
												+ ">下一页</a>");
									}
								%>
								</td><!--
								<td width="20%">
									<img src="image/bg-add.gif" width="9" height="9">
									&nbsp;
									<a href="bg_add_commodity.jsp">添加商品</a>
								</td>
							--></tr>
						</table>
					</table>
				</td>
			</tr>
		</table>
	</body>
	<h4><jsp:include page="bg_bottom.jsp" flush="true" /></h4>
</html>