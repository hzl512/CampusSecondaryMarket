<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	List<String> forcedList=new ArrayList<String>();
	forcedList.add(0,"否");
	forcedList.add(1,"是");
 %>
<!--开发者QQ：2390201971-->
<html>
	<head>
		<title>添加应用更新</title>
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
									<strong>添加应用更新</strong>
								</div>
							</td>
						</tr>
					</table>
					<br>
				<form method="get" action="UpdateServlet">
					<table width="80%" border="1" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" bgcolor="CCCCCC" align="center" >
				 
					<tr bgcolor="#FFFFFF">
						<td align="right">版本名称：</td>
						<td><input type="text" name="versionName" /></td>
					</tr>
				 
					<tr bgcolor="#FFFFFF">
						<td align="right">版本号：</td>
						<td><input type="text" name="versionNumber" /></td>
					</tr>
				 
					<tr bgcolor="#FFFFFF">
						<td align="right">是否强制更新：</td>
						<td>
							<select name="forcedUpdate" >
								<option value="0">请选择</option>
									<%
										for (int i = 0; i < forcedList.size(); i++) {
											String s = forcedList.get(i);
									%>
						 				<%
												if (i==0) {
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
						<td align="right">更新描述：</td>
						<td>
						<textarea  name="description" style="width:200px;height:80px;" >1：2：3：</textarea>
					</td>
					</tr>
					<tr bgcolor="#FFFFFF">
						<td align="right">下载链接前缀：</td>
						<td><textarea  name="url_top" style="width:300px;height:50px;">http://192.168.1.226:8080/CampusSecondaryMarket/files/</textarea></td>
					</tr>
					<tr bgcolor="#FFFFFF">
						<td align="right">上传文件：</td>
						<td><input  name="url" type="file" /></td>
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
