<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.weicent.web.domain.*"%>

<jsp:useBean id="UpdateDao" scope="page" class="com.weicent.web.dao.UpdateDao" />

<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	List<String> forcedList=new ArrayList<String>();
	forcedList.add(0,"否");
	forcedList.add(1,"是");
 %>
<!--开发者QQ：2390201971-->
<html>
	<head>
		<title>修改应用更新</title>
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
									<strong>修改应用更新</strong>
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
							 
								String forcedUpdate = String.valueOf(t.getForcedUpdate());
							 
								String description = t.getDescription();
							 
								String url = t.getUrl();
							 
								String createTime = t.getCreateTime();
							 
								String size = t.getSize();
								
								String apkSize = String.valueOf(t.getApkSize());
							 
							%>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">版本名称：</td>
								<td><input type="text" name="versionName" value=<%=versionName%> /></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">版本号：</td>
								<td><input type="text" name="versionNumber" value=<%=versionNumber%> /></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">是否强制更新：</td>
								<td>
								   <select name="forcedUpdate">
										<option value="<%=forcedUpdate%>">请选择</option>
										<%
											for(int i=0;i<forcedList.size();i++){
											String s=forcedList.get(i).toString();
										%>
						 				<%
												if (forcedUpdate.equals(String.valueOf(i))) {
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
								<td><textarea name="description" style="width:200px;height:80px"><%=description%></textarea></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">下载链接：</td>
								<td><textarea name="url" style="width:200px;height:50px"><%=url%></textarea></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">更新包大小：</td>
								<td><input type="text" name="size" value=<%=size%> /></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right"><input type="reset" value="重填" /></td>
								<td><input type="submit" value="确定" /></td>
							</tr>
						</table>
						<input type="hidden" name="createTime" value="<%=createTime%>" /> 
						<input type="hidden" name="apkSize" value="<%=apkSize%>" /> 
						<input type="hidden" name="action" value="update" /> 
						<input type="hidden" name="id" value="<%=id.trim()%>" />
					</form>
		</table>
	</body>
<h4><jsp:include page="bg_bottom.jsp" flush="true" /></h4>
</html>