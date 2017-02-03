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
 * http://127.0.0.1:8080//BuysServlet?action=
 * 1:{"rssName":"1","rssType":"1","rssLink":"1","rssNote":"1"}
 * 2:{"rssId":1}
 * 3:{"rssId":1,"rssName":"1","rssType":"1","rssLink":"1","rssNote":"1"}
 * 4:{"paging":true,"page_no":1,"page_size":10}
 */
public class BuysServlet extends HttpServlet {

	private static final String CHARACTER_ENCODING = "utf-8";
	private static final String CONTENT_TYPE_TEXT_JSON = "application/json;charset=utf-8";
	private int action;
	private BuysDao dao = null;

	// post 处理数据
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.dao = new BuysDao();
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
				dao.addResBuys(new RequestObjectForJson<Buys>(Buys.class).getfromJson(sb.toString()))));
//				response.getWriter().println(ResponseJsonForObject.createJsonString(new Buys("1","2","3","4","5",123)));
				break;
			case 2://delete
				response.getWriter().println(ResponseJsonForObject.createJsonString(
				dao.deleteResBuys(new RequestObjectForJson<Buys>(Buys.class).getfromJson(sb.toString()))));
				break;
			case 3://update
				response.getWriter().println(ResponseJsonForObject.createJsonString(
				dao.updateResBuys(new RequestObjectForJson<Buys>(Buys.class).getfromJson(sb.toString()))));
				break;
			case 4://select
				response.getWriter().println(ResponseJsonForObject.createJsonString(
				dao.getResBuysList(new RequestObjectForJson<BaseRequest>(BaseRequest.class).getfromJson(sb.toString()))));
				break;
			case 5://select
				response.getWriter().println(ResponseJsonForObject.createJsonString(
				dao.getResBuysList1(new RequestObjectForJson<BaseRequest>(BaseRequest.class).getfromJson(sb.toString()))));
				break;
			case 6://select
				response.getWriter().println(ResponseJsonForObject.createJsonString(
				dao.getResBuysList2(new RequestObjectForJson<BaseRequest>(BaseRequest.class).getfromJson(sb.toString()))));
				break;
			case 7://select
				response.getWriter().println(ResponseJsonForObject.createJsonString(
				dao.getResBuysList1(new RequestObjectForJson<BaseRequest>(BaseRequest.class).getfromJson(sb.toString()))));
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
		this.dao = new BuysDao();
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
		if (dao.deleteBuys(id) > 0) {
			//request.getRequestDispatcher("list_buys.jsp").forward(request,response);
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
        String usersID = new String(request.getParameter("usersID").getBytes("ISO-8859-1"), "utf-8");            
        String buysName = new String(request.getParameter("buysName").getBytes("ISO-8859-1"), "utf-8");            
        String buysPrice = new String(request.getParameter("buysPrice").getBytes("ISO-8859-1"), "utf-8");            
        String buysAddress = new String(request.getParameter("buysAddress").getBytes("ISO-8859-1"), "utf-8");            
        String buysDetail = new String(request.getParameter("buysDetail").getBytes("ISO-8859-1"), "utf-8");            
        String buysPhone = new String(request.getParameter("buysPhone").getBytes("ISO-8859-1"), "utf-8");            
//        String buysAddTime = new String(request.getParameter("buysAddTime").getBytes("ISO-8859-1"), "utf-8");            
        String buysStatus = new String(request.getParameter("buysStatus").getBytes("ISO-8859-1"), "utf-8");            
        String buysQQ = new String(request.getParameter("buysQQ").getBytes("ISO-8859-1"), "utf-8"); 
		Buys t = null;
		if (usersID.equals("")||buysName.equals("")||buysPrice.equals("")||buysAddress.equals("")||buysDetail.equals("")||buysPhone.equals("")||buysStatus.equals("")||buysQQ.equals("")) {
			session.setAttribute("effectview", "请填写完整");
			request.getRequestDispatcher("/bg_effectview.jsp").forward(request,response);
		} else {
			t = new Buys();            
			t.setUsersID(Integer.parseInt(usersID));            
			t.setBuysName(buysName);            
			t.setBuysPrice(buysPrice);            
			t.setBuysAddress(buysAddress);            
			t.setBuysDetail(buysDetail);            
			t.setBuysPhone(buysPhone);            
			t.setBuysAddTime(AbDateUtil.getCurrentDateStringFordateFormatYMDHM2());            
			t.setBuysStatus(Integer.parseInt(buysStatus));            
			t.setBuysQQ(Integer.parseInt(buysQQ));            
			int n = dao.addBuys(t);
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
        String usersID = new String(request.getParameter("usersID").getBytes("ISO-8859-1"), "utf-8");            
        String buysName = new String(request.getParameter("buysName").getBytes("ISO-8859-1"), "utf-8");            
        String buysPrice = new String(request.getParameter("buysPrice").getBytes("ISO-8859-1"), "utf-8");            
        String buysAddress = new String(request.getParameter("buysAddress").getBytes("ISO-8859-1"), "utf-8");            
        String buysDetail = new String(request.getParameter("buysDetail").getBytes("ISO-8859-1"), "utf-8");            
        String buysPhone = new String(request.getParameter("buysPhone").getBytes("ISO-8859-1"), "utf-8");            
        String buysAddTime = new String(request.getParameter("buysAddTime").getBytes("ISO-8859-1"), "utf-8");            
        String buysStatus = new String(request.getParameter("buysStatus").getBytes("ISO-8859-1"), "utf-8");            
        String buysQQ = new String(request.getParameter("buysQQ").getBytes("ISO-8859-1"), "utf-8"); 
		String id = request.getParameter("id");
		Buys t = null;
		if (usersID.equals("")||buysName.equals("")||buysPrice.equals("")||buysAddress.equals("")||buysDetail.equals("")||buysPhone.equals("")||buysAddTime.equals("")||buysStatus.equals("")||buysQQ.equals("")) {
			session.setAttribute("effectview", "请填写完整");
			request.getRequestDispatcher("/bg_effectview.jsp").forward(request,response);
		} else {
			t = new Buys();
			t.setId(Integer.parseInt(id));            
			t.setUsersID(Integer.parseInt(usersID));            
			t.setBuysName(buysName);            
			t.setBuysPrice(buysPrice);            
			t.setBuysAddress(buysAddress);            
			t.setBuysDetail(buysDetail);            
			t.setBuysPhone(buysPhone);            
			t.setBuysAddTime(buysAddTime);            
			t.setBuysStatus(Integer.parseInt(buysStatus));            
			t.setBuysQQ(Integer.parseInt(buysQQ)); 
			int n = dao.updateBuys(t);
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