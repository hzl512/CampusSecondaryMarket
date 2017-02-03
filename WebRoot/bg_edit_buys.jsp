<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.weicent.web.domain.*"%>

<jsp:useBean id="BuysDao" scope="page" class="com.weicent.web.dao.BuysDao" />
<%
	List<String> statusList=new ArrayList<String>();
	statusList.add(0,"上架");
	statusList.add(1,"下架");
	statusList.add(2,"违规");
 %>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<html>
	<head>
		<title>修改求购</title>
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
									<strong>修改求购</strong>
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
							 
								String usersID = String.valueOf(t.getUsersID());
							 
								String buysName = t.getBuysName();
							 
								String buysPrice = t.getBuysPrice();
							 
								String buysAddress = t.getBuysAddress();
							 
								String buysDetail = t.getBuysDetail();
							 
								String buysPhone = t.getBuysPhone();
							 
								String buysAddTime = t.getBuysAddTime();
							 
								String buysStatus = String.valueOf(t.getBuysStatus());
							 
								String buysQQ = String.valueOf(t.getBuysQQ());
							 
							%>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">商品名称：</td>
								<td><input type="text" name="buysName" value=<%=buysName%> /></td>
							</tr>
							 							 
							<tr bgcolor="#FFFFFF">
								<td align="right">商品详情：</td>
								<td><input type="text" name="buysDetail" value=<%=buysDetail%> /></td>
							</tr>
							
							<tr bgcolor="#FFFFFF">
								<td align="right">期望价格：</td>
								<td><input type="text" name="buysPrice" value=<%=buysPrice%> /></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">交易地点：</td>
								<td><input type="text" name="buysAddress" value=<%=buysAddress%> /></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">联系电话：</td>
								<td><input type="text" name="buysPhone" value=<%=buysPhone%> /></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">状态：</td>
								<td>
									<select name="buysStatus">
										<option value="<%=buysStatus%>">请选择</option>

										<%
											for(int i=0;i<statusList.size();i++){
											String s=statusList.get(i).toString();
										%>
						 				<%
												if (buysStatus.equals(String.valueOf(i))) {
										%>
										<option value="<%=i%>" selected="selected"><%=s%> </option>
												<%
													}else{
												%>
										<option value="<%=i%>" ><%=s%> </option>
											<%
												}
											%>
										 <%
										 }
										 %>
						  			</select>
								</td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">联系QQ：</td>
								<td><input type="text" name="buysQQ" value=<%=buysQQ%> /></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right"><input type="reset" value="重填" /></td>
								<td><input type="submit" value="确定" /></td>
							</tr>
						</table>
						<input type="hidden" name="action" value="update" /> 
						<input type="hidden" name="id" value="<%=id.trim()%>" />
						<input type="hidden" name="usersID" value=<%=usersID%> />
						<input type="hidden" name="buysAddTime" value=<%=buysAddTime%> />
					</form>
		</table>
	</body>
<h4><jsp:include page="bg_bottom.jsp" flush="true" /></h4>
</html>