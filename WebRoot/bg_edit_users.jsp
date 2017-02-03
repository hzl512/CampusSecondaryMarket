<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.weicent.web.domain.*"%>

<jsp:useBean id="UsersDao" scope="page" class="com.weicent.web.dao.UsersDao" />
<jsp:useBean id="ProfessionDao" scope="page" class="com.weicent.web.dao.ProfessionDao" />
<jsp:useBean id="DepartmentsDao" scope="page" class="com.weicent.web.dao.DepartmentsDao" />

<script language="javascript">
function ChangeItem(){
	var id=form.id.value;
	var departmentsID=form.departmentsID.value;
	
	window.location.href="bg_edit_users.jsp?id="+id+"&departmentsID="+departmentsID;
}
</script>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--开发者QQ：2390201971-->
<html>
	<head>
		<title>修改用户</title>
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
									<strong>修改用户</strong>
								</div>
							</td>
						</tr>
					</table>
					<br>
					<form name="form" method="get" action="UsersServlet">
						<table width="80%" border="1" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" bgcolor="CCCCCC" align="center">
							<%
								String id = request.getParameter("id").toString();
								Users t = UsersDao.getUsers(id);
							 
								String usersGrade = String.valueOf(t.getUsersGrade());
							 
								String usersName = t.getUsersName();
							 
								String usersPwd = t.getUsersPwd();
							 
								String usersAddTime = t.getUsersAddTime();
							 
								String usersNickName = t.getUsersNickName();
							 
								String usersPhone = t.getUsersPhone();
							 
								String usersQQ = t.getUsersQQ();
								
							 	String departmentsID="";
							 	departmentsID = (String) request.getParameter("departmentsID");
								if (departmentsID == null || departmentsID.equals("")) { //当id值为空时，设置id值为-1
									departmentsID = String.valueOf(t.getDepartmentsID());
								}else{
									departmentsID = (String) request.getParameter("departmentsID");
								}
								
							 
								String professionID = String.valueOf(t.getProfessionID());
							 
							 	ArrayList<Departments> list = DepartmentsDao.getDepartmentsListAll("");
							 	
							 	ArrayList<Profession> list1 = ProfessionDao.getProfessionListAll(departmentsID);
							%>
							 							<tr bgcolor="#FFFFFF">
								<td align="right">院系：</td>
								<td>
									<select name="departmentsID" onChange="ChangeItem()">
										<option >
											请选择
										</option>
									<%
										for (int i = 0; i < list.size(); i++) {
											Departments temp = (Departments) list.get(i);
											if (String.valueOf(temp.getId()).equals(departmentsID)) {
									%>
									<option value="<%=departmentsID%>" selected="selected"><%=DepartmentsDao.getDepartments(departmentsID).getDepartmentsName()%></option>
									<%
										} else {
									%>
									<option value="<%=temp.getId()%>"><%=temp.getDepartmentsName()%></option>
									<%
											}
										}
									%>

									</select>
								</td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">专业：</td>
								<td>
									<select name="professionID">
										<option >
											请选择
										</option>
										<%
										for (int i = 0; i < list1.size(); i++) {
												Profession temp1 = (Profession) list1.get(i);
												if (String.valueOf(temp1.getId()).equals(professionID)) {
										%>
										<option value="<%=professionID%>" selected="selected"><%=ProfessionDao.getProfession(professionID).getProfessionName()%></option>
										<%
											} else {
										%>
										<option value="<%=temp1.getId()%>"><%=temp1.getProfessionName()%></option>
										<%
											}
										}
										%>

									</select>
								</td>
							</tr>
							
							<tr bgcolor="#FFFFFF">
								<td align="right">年级：</td>
								<td><input type="text" name="usersGrade" value=<%=usersGrade%> /></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">用户账号：</td>
								<td><input type="text" name="usersName" value=<%=usersName%> /></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">用户密码：</td>
								<td><input type="text" name="usersPwd" value=<%=usersPwd%> /></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">用户昵称：</td>
								<td><input type="text" name="usersNickName" value=<%=usersNickName%> /></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">联系电话：</td>
								<td><input type="text" name="usersPhone" value=<%=usersPhone%> /></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right">用户QQ：</td>
								<td><input type="text" name="usersQQ" value=<%=usersQQ%> /></td>
							</tr>
							 
							<tr bgcolor="#FFFFFF">
								<td align="right"><input type="reset" value="重填" /></td>
								<td><input type="submit" value="确定" /></td>
							</tr>
						</table>
						<input type="hidden" name="action" value="update" /> 
						<input type="hidden" name="id" value="<%=id.trim()%>" />
						<input type="hidden" name="usersAddTime" value="<%=usersAddTime%>" />
					</form>
		</table>
	</body>
<h4><jsp:include page="bg_bottom.jsp" flush="true" /></h4>
</html>