<%@ page contentType="text/html; charset=UTF-8"%>
<%
	if (session.getAttribute("loginname") == null) {
		out
				.print("<script language=javascript>alert('您已经与服务器断开连接，请重新登录！');window.location.href='index.jsp';</script>");
	} else {
		String loginname = session.getAttribute("loginname").toString();
%>
<table width="1000" height="66" border="0" align="center" cellpadding="0"
	cellspacing="0" bgcolor="#9A0E0D">
	<tr>
		<td valign="top">
			<table width="1000" border="0" align="center">
				<tr>
					<td width="25" height="10">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td width="1000" valign="middle" align="left">
						<font color="#FFFFFF">
							<h2>
								&nbsp;&nbsp;&nbsp;&nbsp;校园二手市场管理系统
							</h2> </font>
					</td>
					<td width="15" height="25">
						&nbsp;
					</td>
					<td width="347" valign="bottom" align="right">
						<!--<font color="#FFFFFF"> <a href="#" class="a2">返回首页</a> </font>
					--></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<table width="1000" border="0" align="center" cellpadding="0"
	cellspacing="0" bgcolor="#000000">
	<tr>
		<td width="15" height="25">
			&nbsp;
		</td>
		<td width="579">
			<font color="#FFFFFF">当前登录：<%=loginname%></font>
		</td>
	</tr>
</table>
<%
	}
%>
