<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
	<head>
		<title>主页</title>
		<style type="text/css">
</style>
	</head>
	<link href="css/css.css" rel="stylesheet" type="text/css">
	<body>
		<br>
		<br>
		<br>
		<center>
			<h1>
				<font color="#FFFFFF">校园二手市场管理系统</font>
			</h1>
					<br>
			<form method="get" action="ManageServlet">
				<table width="55%" border="1" cellpadding="1" cellspacing="1"
					bordercolor="#FFFFFF" bgcolor="CCCCCC">
					<tr bgcolor="#FFFFFFF">
						<td align="right">
							账 号：
						</td>
						<td>
							<input type="text" name="name" />
						</td>
					</tr>
					<tr bgcolor="#FFFFFFF">
						<td align="right">
							密 码：
						</td>
						<td>
							<input type="password" name="pwd" />
						</td>
					</tr>
					<tr bgcolor="#FFFFFFF">
						<td align="right">
							<input type="reset" name="nook" value="重置">
						</td>
						<td>
							<input type="submit" name="ok" value="进入" />
						</td>
					</tr>
				</table>
				<input type="hidden" name="action" value="login" />
			</form>
		</center>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
	</body>
	<h4><jsp:include page="bg_bottom.jsp" flush="true" /></h4>
</html>

