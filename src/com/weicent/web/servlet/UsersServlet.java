package com.weicent.web.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Date;

import com.weicent.web.domain.*;
import com.weicent.web.model.*;
import com.weicent.web.response.*;
import com.weicent.web.request.*;
import com.weicent.web.response.list.*;
import com.weicent.web.response.model.*;
import com.weicent.web.request.list.*;
import com.weicent.web.request.model.*;
import com.weicent.web.tool.*;
import com.weicent.web.dao.*;

/**
 * http://127.0.0.1:8080//UsersServlet?action=
 * 1:{"usersName":"1","usersPwd":"2","usersNickName":"3","usersPhone":"4","usersQQ":"5","departmentsID":123,"professionID":123,"usersGrade":123}
 * 2:{"rssId":1}
 * 3:{"rssId":1,"rssName":"1","rssType":"1","rssLink":"1","rssNote":"1"}
 * 4:{"paging":true,"page_no":1,"page_size":10}
 */
public class UsersServlet extends HttpServlet {

	private static final String CHARACTER_ENCODING = "utf-8";
	private static final String CONTENT_TYPE_TEXT_JSON = "application/json;charset=utf-8";
	private int action;
	private UsersDao dao = null;

	// post 处理数据
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.dao = new UsersDao();
		// Request 读取请求内容
		request.setCharacterEncoding(CHARACTER_ENCODING);
		this.action = Integer.parseInt(request.getParameter("action"));
		System.out.println("action" + action);
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), CHARACTER_ENCODING));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		System.out.println("json" + sb.toString());
		// Response 写出反馈内容
		response.setContentType(CONTENT_TYPE_TEXT_JSON);
		response.setCharacterEncoding(CHARACTER_ENCODING);

		switch (action) {
			case 1://add
				response.getWriter().println(ResponseJsonForObject.createJsonString(
				dao.addResUsers(new RequestObjectForJson<Users>(Users.class).getfromJson(sb.toString()))));
//				response.getWriter().println(ResponseJsonForObject.createJsonString(new Users("1","2","3","4","5",123,123,123)));
				break;
			case 2://delete
				response.getWriter().println(ResponseJsonForObject.createJsonString(
				dao.deleteResUsers(new RequestObjectForJson<Users>(Users.class).getfromJson(sb.toString()))));
				break;
			case 3://update
				response.getWriter().println(ResponseJsonForObject.createJsonString(
				dao.updateResUsers(new RequestObjectForJson<Users>(Users.class).getfromJson(sb.toString()))));
				break;
			case 4://select
				response.getWriter().println(ResponseJsonForObject.createJsonString(
				dao.getResUsersList(new RequestObjectForJson<BaseRequest>(BaseRequest.class).getfromJson(sb.toString()))));
				break;
			case 5://login
				response.getWriter().println(ResponseJsonForObject.createJsonString(
				dao.getResUsers(new RequestObjectForJson<Users>(Users.class).getfromJson(sb.toString()))));
				break;
			case 6://select
				response.getWriter().println(ResponseJsonForObject.createJsonString(
				dao.getResUsers1(new RequestObjectForJson<Users>(Users.class).getfromJson(sb.toString()))));
				break;
			default:
				BaseResponse responseDefault=new BaseResponse();
				responseDefault.errorcode=-1;
				responseDefault.errormsg="请求类型错误！";
				response.getWriter().println(ResponseJsonForObject.createJsonString(responseDefault));
			break;
		}
	}
	
	// get 处理数据
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		this.dao = new UsersDao();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		System.out.println("actionString:" + action);
		// 操作类型
		if (action.equals("delete")) {
			delete(request, response);
		} else if (action.equals("update")) {
			update(request, response);
		} else if (action.equals("add")) {
			add(request, response);
		}
		out.flush();
		out.close();
	}
	
	// 删除信息
	private void delete(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		if (dao.deleteUsers(id) > 0) {
			//request.getRequestDispatcher("list_users.jsp").forward(request,response);
			session.setAttribute("effectview", "删除成功");
			request.getRequestDispatcher("bg_effectview.jsp").forward(request,response);
		} else {
			session.setAttribute("bg_effectview", "删除失败");
			request.getRequestDispatcher("bg_effectview.jsp").forward(request,response);
		}
	}

	// 添加信息
	private void add(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		HttpSession session = request.getSession();            
        String usersName = new String(request.getParameter("usersName").getBytes("ISO-8859-1"), "utf-8");            
        String usersPwd = new String(request.getParameter("usersPwd").getBytes("ISO-8859-1"), "utf-8");            
//        String usersAddTime = new String(request.getParameter("usersAddTime").getBytes("ISO-8859-1"), "utf-8");            
        String usersNickName = new String(request.getParameter("usersNickName").getBytes("ISO-8859-1"), "utf-8");            
        String usersPhone = new String(request.getParameter("usersPhone").getBytes("ISO-8859-1"), "utf-8");            
        String usersQQ = new String(request.getParameter("usersQQ").getBytes("ISO-8859-1"), "utf-8");            
        String departmentsID = new String(request.getParameter("departmentsID").getBytes("ISO-8859-1"), "utf-8");            
        String professionID = new String(request.getParameter("professionID").getBytes("ISO-8859-1"), "utf-8");            
        String usersGrade = new String(request.getParameter("usersGrade").getBytes("ISO-8859-1"), "utf-8"); 
		Users t = null;
		if (usersName.equals("")||usersPwd.equals("")||usersNickName.equals("")||usersPhone.equals("")||usersQQ.equals("")||departmentsID.equals("")||professionID.equals("")||usersGrade.equals("")) {
			session.setAttribute("effectview", "请填写完整");
			request.getRequestDispatcher("/bg_effectview.jsp").forward(request,response);
		} else {
			t = new Users();            
			t.setUsersName(usersName);            
			t.setUsersPwd(usersPwd);            
			t.setUsersAddTime(AbDateUtil.getCurrentDateStringFordateFormatYMDHM2());            
			t.setUsersNickName(usersNickName);            
			t.setUsersPhone(usersPhone);            
			t.setUsersQQ(usersQQ);            
			t.setDepartmentsID(Integer.parseInt(departmentsID));            
			t.setProfessionID(Integer.parseInt(professionID));            
			t.setUsersGrade(Integer.parseInt(usersGrade));            
			int n = dao.addUsers(t);
			System.out.println(n);
			if (n > 0) {
				session.setAttribute("effectview", "添加成功！");
				request.getRequestDispatcher("/bg_effectview.jsp").forward(request, response);
			} else {
				session.setAttribute("effectview", "添加失败！");
				request.getRequestDispatcher("/bg_effectview.jsp").forward(request, response);
			}
		}
	}

	// 修改信息
	private void update(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		HttpSession session = request.getSession();            
        String usersName = new String(request.getParameter("usersName").getBytes("ISO-8859-1"), "utf-8");            
        String usersPwd = new String(request.getParameter("usersPwd").getBytes("ISO-8859-1"), "utf-8");            
        String usersAddTime = new String(request.getParameter("usersAddTime").getBytes("ISO-8859-1"), "utf-8");            
        String usersNickName = new String(request.getParameter("usersNickName").getBytes("ISO-8859-1"), "utf-8");            
        String usersPhone = new String(request.getParameter("usersPhone").getBytes("ISO-8859-1"), "utf-8");            
        String usersQQ = new String(request.getParameter("usersQQ").getBytes("ISO-8859-1"), "utf-8");            
        String departmentsID = new String(request.getParameter("departmentsID").getBytes("ISO-8859-1"), "utf-8");            
        String professionID = new String(request.getParameter("professionID").getBytes("ISO-8859-1"), "utf-8");            
        String usersGrade = new String(request.getParameter("usersGrade").getBytes("ISO-8859-1"), "utf-8"); 
		String id = request.getParameter("id");
		Users t = null;
		if (usersName.equals("")||usersPwd.equals("")||usersNickName.equals("")||usersPhone.equals("")||usersQQ.equals("")||departmentsID.equals("")||professionID.equals("")||usersGrade.equals("")) {
			session.setAttribute("effectview", "请填写完整");
			request.getRequestDispatcher("/bg_effectview.jsp").forward(request,response);
		} else {
			t = new Users();
			t.setId(Integer.parseInt(id));            
			t.setUsersName(usersName);            
			t.setUsersPwd(usersPwd);            
			t.setUsersAddTime(usersAddTime);           
			t.setUsersNickName(usersNickName);            
			t.setUsersPhone(usersPhone);            
			t.setUsersQQ(usersQQ);            
			t.setDepartmentsID(Integer.parseInt(departmentsID));            
			t.setProfessionID(Integer.parseInt(professionID));            
			t.setUsersGrade(Integer.parseInt(usersGrade)); 
			int n = dao.updateUsers(t);
			System.out.println(n);
			if (n > 0) {
				session.setAttribute("effectview", "修改成功！");
				request.getRequestDispatcher("/bg_effectview.jsp").forward(request, response);
			} else {
				session.setAttribute("effectview", "修改失败！");
				request.getRequestDispatcher("/bg_effectview.jsp").forward(request, response);
			}

		}
	}
	
}