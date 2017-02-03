<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
<head>
<title>msg</title>
</head>
<link href="css/css.css" rel="stylesheet" type="text/css">
<body>
	<jsp:include page="bg_top.jsp" flush="true" />
	<table width="1000" border="0" align="center" cellpadding="0"
		bgcolor="#FFFFFF" cellspacing="0">
		<tr>
			<td width="20%" valign="top"><jsp:include page="bg_left.jsp"
					flush="true" /></td>
			<td>
				<table width="80%" height="25" border="0" cellpadding="0"
					align="center" cellspacing="0">
					<tr>
						<td>
							<div align="center">
								<h1>
									<center>
										<%
											String str = (String) session.getAttribute("effectview");
										%>
										<%=str%>
									</center>
								</h1>
							</div></td>
					</tr>
				</table> <br>
		</tr>
	</table>

</body>
<h4><jsp:include page="bg_bottom.jsp" flush="true" /></h4>
</html>

