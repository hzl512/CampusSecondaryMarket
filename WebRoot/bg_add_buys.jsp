<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	List<String> statusList=new ArrayList<String>();
	statusList.add(0,"上架");
	statusList.add(1,"下架");
	statusList.add(2,"违规");
 %>
<html>
	<head>
		<title>添加求购1</title>
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
									<strong>添加求购</strong>
								</div>
							</td>
						</tr>
					</table>
					<br>
				<form method="get" action="BuysServlet">
					<table width="80%" border="1" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" bgcolor="CCCCCC" align="center" >
				 
					<tr bgcolor="#FFFFFF">
						<td align="right">usersID：</td>
						<td><input type="text" name="usersID" /></td>
					</tr>
				 
					<tr bgcolor="#FFFFFF">
						<td align="right">商品名称：</td>
						<td><input type="text" name="buysName" /></td>
					</tr>

					<tr bgcolor="#FFFFFF">
						<td align="right">商品详情：</td>
						<td><input type="text" name="buysDetail" /></td>
					</tr>
				 				 
					<tr bgcolor="#FFFFFF">
						<td align="right">期望价格：</td>
						<td><input type="text" name="buysPrice" /></td>
					</tr>
									 
					<tr bgcolor="#FFFFFF">
						<td align="right">交易地点：</td>
						<td><input type="text" name="buysAddress" /></td>
					</tr>
					
					<tr bgcolor="#FFFFFF">
						<td align="right">联系电话：</td>
						<td><input type="text" name="buysPhone" /></td>
					</tr>
				 
					<tr bgcolor="#FFFFFF">
						<td align="right">状态：</td>
						<td>
						<select name="buysStatus">
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
						<td align="right">联系QQ：</td>
						<td><input type="text" name="buysQQ" /></td>
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
