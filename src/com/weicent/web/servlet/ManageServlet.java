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
 * http://127.0.0.1:8080//ManageServlet?action=
 * 1:{"rssName":"1","rssType":"1","rssLink":"1","rssNote":"1"}
 * 2:{"rssId":1}
 * 3:{"rssId":1,"rssName":"1","rssType":"1","rssLink":"1","rssNote":"1"}
 * 4:{"paging":true,"page_no":1,"page_size":10}
 */
public class ManageServlet extends HttpServlet {

	private static final String CHARACTER_ENCODING = "utf-8";
	private static final String CONTENT_TYPE_TEXT_JSON = "application/json;charset=utf-8";
	private int action;
	private ManageDao dao = null;

	// post 处理数据
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.dao = new ManageDao();
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
				/*
				response.getWriter().println(ResponseJsonForObject.createJsonString(
				dao.addResManage(new RequestObjectForJson<Manage>(Manage.class).getfromJson(sb.toString()))));
				*/
				response.getWriter().println(ResponseJsonForObject.createJsonString(new Manage()));
				break;
			case 2://delete
				response.getWriter().println(ResponseJsonForObject.createJsonString(
				dao.deleteResManage(new RequestObjectForJson<Manage>(Manage.class).getfromJson(sb.toString()))));
				break;
			case 3://update
				response.getWriter().println(ResponseJsonForObject.createJsonString(
				dao.updateResManage(new RequestObjectForJson<Manage>(Manage.class).getfromJson(sb.toString()))));
				break;
			case 4://select
				response.getWriter().println(ResponseJsonForObject.createJsonString(
				dao.getResManageList(new RequestObjectForJson<BaseRequest>(BaseRequest.class).getfromJson(sb.toString()))));
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
		this.dao = new ManageDao();
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
		}else if (action.equals("login")) {
	        login(request,response);
		}
		out.flush();
		out.close();
	}
	
	// 管理员登陆
	private void login(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userName = request.getParameter("name");
		String userPassword = request.getParameter("pwd");
		Manage u = null;
		ManageDao us = null;
		if (userName.equals("") || userPassword.equals("")) {
			session.setAttribute("effectview", "请填写完整");
			request.getRequestDispatcher("/effectview.jsp").forward(request,
					response);
		} else {
			u = new Manage();
			us = new ManageDao();
			u.setManageName(userName);
			u.setManagePassWord(userPassword);
			Manage u1 = us.selectInformation(u);
			System.out.println(userName);
			System.out.println(u1.getManageName());
			if (userName.equals(u1.getManageName())&& userPassword.equals(u1.getManagePassWord())) {
				session.setAttribute("loginname", userName);
				request.getRequestDispatcher("bg_list_manage.jsp").forward(request,response);
			} else {
				if (!userName.equals(u1.getManageName())) {
					session.setAttribute("effectview", "没有此管理员");
					request.getRequestDispatcher("bg_effectview.jsp").forward(
							request, response);
				} else {
					session.setAttribute("effectview", "密码或管理员名不正确");
					request.getRequestDispatcher("bg_effectview.jsp").forward(
							request, response);
				}
			}
		}
	}
	
	// 删除信息
	private void delete(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		if (dao.deleteManage(id) > 0) {
			//request.getRequestDispatcher("list_manage.jsp").forward(request,response);
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
        String manageName = new String(request.getParameter("manageName").getBytes("ISO-8859-1"), "utf-8");            
        String managePassWord = new String(request.getParameter("managePassWord").getBytes("ISO-8859-1"), "utf-8");            
        String manageRemark = new String(request.getParameter("manageRemark").getBytes("ISO-8859-1"), "utf-8"); 
		Manage t = null;
		if (manageName.equals("")||managePassWord.equals("")||manageRemark.equals("")) {
			session.setAttribute("effectview", "请填写完整");
			request.getRequestDispatcher("/bg_effectview.jsp").forward(request,response);
		} else {
			t = new Manage();            
			t.setManageName(manageName);            
			t.setManagePassWord(managePassWord);            
			t.setManageRemark(manageRemark); 
			int n = dao.addManage(t);
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
        String manageName = new String(request.getParameter("manageName").getBytes("ISO-8859-1"), "utf-8");            
        String managePassWord = new String(request.getParameter("managePassWord").getBytes("ISO-8859-1"), "utf-8");            
        String manageRemark = new String(request.getParameter("manageRemark").getBytes("ISO-8859-1"), "utf-8"); 
		String id = request.getParameter("id");
		Manage t = null;
		if (manageName.equals("")||managePassWord.equals("")||manageRemark.equals("")) {
			session.setAttribute("effectview", "请填写完整");
			request.getRequestDispatcher("/bg_effectview.jsp").forward(request,response);
		} else {
			t = new Manage();
			t.setId(Integer.parseInt(id));            
			t.setManageName(manageName);            
			t.setManagePassWord(managePassWord);            
			t.setManageRemark(manageRemark); 
			int n = dao.updateManage(t);
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