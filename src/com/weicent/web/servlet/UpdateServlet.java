package com.weicent.web.servlet;

import java.io.BufferedReader;
import java.io.File;
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
 * http://127.0.0.1:8080//UpdateServlet?action=
 * 1:{"rssName":"1","rssType":"1","rssLink":"1","rssNote":"1"}
 * 2:{"rssId":1}
 * 3:{"rssId":1,"rssName":"1","rssType":"1","rssLink":"1","rssNote":"1"}
 * 4:{"paging":true,"page_no":1,"page_size":10}
 */
public class UpdateServlet extends HttpServlet {

	private static final String CHARACTER_ENCODING = "utf-8";
	private static final String CONTENT_TYPE_TEXT_JSON = "application/json;charset=utf-8";
	private int action;
	private UpdateDao dao = null;

	// post 处理数据
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.dao = new UpdateDao();
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
				dao.addResUpdate(new RequestObjectForJson<Update>(Update.class).getfromJson(sb.toString()))));
				*/
				response.getWriter().println(ResponseJsonForObject.createJsonString(new Update()));
				break;
			case 2://delete
				response.getWriter().println(ResponseJsonForObject.createJsonString(
				dao.deleteResUpdate(new RequestObjectForJson<Update>(Update.class).getfromJson(sb.toString()))));
				break;
			case 3://update
				response.getWriter().println(ResponseJsonForObject.createJsonString(
				dao.updateResUpdate(new RequestObjectForJson<Update>(Update.class).getfromJson(sb.toString()))));
				break;
			case 4://select
				response.getWriter().println(ResponseJsonForObject.createJsonString(
				dao.getResUpdateList(new RequestObjectForJson<BaseRequest>(BaseRequest.class).getfromJson(sb.toString()))));
				break;
			case 5://select
				response.getWriter().println(ResponseJsonForObject.createJsonString(
				dao.getResUpdate1()));
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
		this.dao = new UpdateDao();
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
			try {
				add(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		out.flush();
		out.close();
	}
	
	// 删除信息
	private void delete(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		if (dao.deleteUpdate(id) > 0) {
			//request.getRequestDispatcher("list_update.jsp").forward(request,response);
			session.setAttribute("effectview", "删除成功");
			request.getRequestDispatcher("bg_effectview.jsp").forward(request,response);
		} else {
			session.setAttribute("bg_effectview", "删除失败");
			request.getRequestDispatcher("bg_effectview.jsp").forward(request,response);
		}
	}

	// 添加信息
	private void add(HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String dir = this.getServletContext().getRealPath("/files");
		HttpSession session = request.getSession();            
        String versionName = new String(request.getParameter("versionName").getBytes("ISO-8859-1"), "utf-8");            
        String versionNumber = new String(request.getParameter("versionNumber").getBytes("ISO-8859-1"), "utf-8");            
        String forcedUpdate = new String(request.getParameter("forcedUpdate").getBytes("ISO-8859-1"), "utf-8");            
        String description = new String(request.getParameter("description").getBytes("ISO-8859-1"), "utf-8");
        String url_top = new String(request.getParameter("url_top").getBytes("ISO-8859-1"), "utf-8");   
        String url = new String(request.getParameter("url").getBytes("ISO-8859-1"), "utf-8");   
        File formFile=new File(url);
        String getType = formFile.getName().substring(formFile.getName().lastIndexOf(".") + 1);
//        String createTime = new String(request.getParameter("createTime").getBytes("ISO-8859-1"), "utf-8");            
//        String size = new String(request.getParameter("size").getBytes("ISO-8859-1"), "utf-8"); 
		Update t = null;
		if (url_top.equals("")||versionName.equals("")||versionNumber.equals("")||forcedUpdate.equals("")||description.equals("")||url.equals("")) {
			session.setAttribute("effectview", "请填写完整");
			request.getRequestDispatcher("/bg_effectview.jsp").forward(request,response);
		} else {
			t = new Update(); 
			UploadFile uploadFile=new UploadFile();
			if (getType.trim().equals("apk")) {
				t.setVersionName(versionName);            
				t.setVersionNumber(versionNumber);            
				t.setForcedUpdate(Integer.parseInt(forcedUpdate));            
				t.setDescription(description);            
				t.setUrl(url_top+uploadFile.upload2(dir, formFile));            
				t.setCreateTime(AbDateUtil.getCurrentDate(AbDateUtil.dateFormatYMDHMS));            
				t.setSize(AbStrUtil.FormetFileSize(formFile.length()));  
				t.setApkSize(formFile.length());  
			}
			int n = dao.addUpdate(t);
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
        String versionName = new String(request.getParameter("versionName").getBytes("ISO-8859-1"), "utf-8");            
        String versionNumber = new String(request.getParameter("versionNumber").getBytes("ISO-8859-1"), "utf-8");            
        String forcedUpdate = new String(request.getParameter("forcedUpdate").getBytes("ISO-8859-1"), "utf-8");            
        String description = new String(request.getParameter("description").getBytes("ISO-8859-1"), "utf-8");            
        String url = new String(request.getParameter("url").getBytes("ISO-8859-1"), "utf-8");            
        String createTime = new String(request.getParameter("createTime").getBytes("ISO-8859-1"), "utf-8");            
        String size = new String(request.getParameter("size").getBytes("ISO-8859-1"), "utf-8"); 
        String apkSize = new String(request.getParameter("apkSize").getBytes("ISO-8859-1"), "utf-8");
		String id = request.getParameter("id");
		Update t = null;
		if (versionName.equals("")||versionNumber.equals("")||forcedUpdate.equals("")||description.equals("")||url.equals("")||createTime.equals("")||size.equals("")) {
			session.setAttribute("effectview", "请填写完整");
			request.getRequestDispatcher("/bg_effectview.jsp").forward(request,response);
		} else {
			t = new Update();
			t.setId(Integer.parseInt(id));            
			t.setVersionName(versionName);            
			t.setVersionNumber(versionNumber);            
			t.setForcedUpdate(Integer.parseInt(forcedUpdate));            
			t.setDescription(description);            
			t.setUrl(url);            
			t.setCreateTime(createTime);            
			t.setSize(size); 
			t.setApkSize(Long.parseLong(apkSize)); 
			int n = dao.updateUpdate(t);
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