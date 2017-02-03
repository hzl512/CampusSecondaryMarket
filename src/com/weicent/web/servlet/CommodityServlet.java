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
 * http://127.0.0.1:8080//CommodityServlet?action=
 * 1:{"rssName":"1","rssType":"1","rssLink":"1","rssNote":"1"}
 * 2:{"rssId":1}
 * 3:{"rssId":1,"rssName":"1","rssType":"1","rssLink":"1","rssNote":"1"}
 * 4:{"paging":true,"page_no":1,"page_size":10}
 */
public class CommodityServlet extends HttpServlet {

	private static final String CHARACTER_ENCODING = "utf-8";
	private static final String CONTENT_TYPE_TEXT_JSON = "application/json;charset=utf-8";
	private int action;
	private CommodityDao dao = null;

	// post 处理数据
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.dao = new CommodityDao();
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
				dao.addResCommodity(new RequestObjectForJson<Commodity>(Commodity.class).getfromJson(sb.toString()))));
				*/
				response.getWriter().println(ResponseJsonForObject.createJsonString(new Commodity()));
				break;
			case 2://delete
				response.getWriter().println(ResponseJsonForObject.createJsonString(
				dao.deleteResCommodity(new RequestObjectForJson<Commodity>(Commodity.class).getfromJson(sb.toString()))));
				break;
			case 3://update
				response.getWriter().println(ResponseJsonForObject.createJsonString(
				dao.updateResCommodity(new RequestObjectForJson<Commodity>(Commodity.class).getfromJson(sb.toString()))));
				break;
			case 4://select
				response.getWriter().println(ResponseJsonForObject.createJsonString(
				dao.getResCommodityList1(new RequestObjectForJson<BaseRequest>(BaseRequest.class).getfromJson(sb.toString()))));
				break;
			case 5://update
				response.getWriter().println(ResponseJsonForObject.createJsonString(
						dao.updateResCommodity1(String.valueOf(new RequestObjectForJson<BaseRequest>(BaseRequest.class).getfromJson(sb.toString()).id))));
				break;
			case 6://select
				response.getWriter().println(ResponseJsonForObject.createJsonString(
				dao.getResCommodity(String.valueOf(new RequestObjectForJson<BaseRequest>(BaseRequest.class).getfromJson(sb.toString()).id))));
				break;
			case 7://select
				response.getWriter().println(ResponseJsonForObject.createJsonString(
				dao.getResCommodityList2(new RequestObjectForJson<ReqCommodity>(ReqCommodity.class).getfromJson(sb.toString()))));
				break;
			case 8://select
				response.getWriter().println(ResponseJsonForObject.createJsonString(
				dao.getResCommodityList3(new RequestObjectForJson<ReqCommodity>(ReqCommodity.class).getfromJson(sb.toString()))));
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
		this.dao = new CommodityDao();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		System.out.println("actionString:" + action);
		// 操作类型
		if (action.equals("delete")) {
			delete(request, response);
		} else if (action.equals("update")) {
			try {
				update(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
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
		if (dao.deleteCommodity(id) > 0) {
			//request.getRequestDispatcher("list_commodity.jsp").forward(request,response);
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
		UploadFile uploadFile = new UploadFile();
		String dir = this.getServletContext().getRealPath("/goodsPicture");
		HttpSession session = request.getSession();            
        String usersID = new String(request.getParameter("usersID").getBytes("ISO-8859-1"), "utf-8"); 

        String commodityImageUrl = new String(request.getParameter("commodityImageUrl").getBytes("ISO-8859-1"), "utf-8"); 
        File formFile=new File(commodityImageUrl);
        String getType = formFile.getName().substring(formFile.getName().lastIndexOf(".") + 1);
        String commodityName = new String(request.getParameter("commodityName").getBytes("ISO-8859-1"), "utf-8");            
        String commodityDetail = new String(request.getParameter("commodityDetail").getBytes("ISO-8859-1"), "utf-8");            
        String commodityAddress = new String(request.getParameter("commodityAddress").getBytes("ISO-8859-1"), "utf-8");            
        String commodityPrice = new String(request.getParameter("commodityPrice").getBytes("ISO-8859-1"), "utf-8");            
        String categoryID = new String(request.getParameter("categoryID").getBytes("ISO-8859-1"), "utf-8");            
        String commodityBargain = new String(request.getParameter("commodityBargain").getBytes("ISO-8859-1"), "utf-8");            
        String commodityPhone = new String(request.getParameter("commodityPhone").getBytes("ISO-8859-1"), "utf-8");            
        String commodityQQ = new String(request.getParameter("commodityQQ").getBytes("ISO-8859-1"), "utf-8");            
        String commodityAddTime = AbDateUtil.getCurrentDateStringFordateFormatYMDHM2();            
        String commodityStatus = new String(request.getParameter("commodityStatus").getBytes("ISO-8859-1"), "utf-8");
        int commodityViews =0; 
		Commodity t = null;
		if (usersID.equals("")||commodityImageUrl.equals("")||commodityName.equals("")||commodityDetail.equals("")||commodityAddress.equals("")||commodityPrice.equals("")||categoryID.equals("")||commodityBargain.equals("")||commodityPhone.equals("")||commodityQQ.equals("")||commodityAddTime.equals("")||commodityStatus.equals("")) {
			session.setAttribute("effectview", "请填写完整");
			request.getRequestDispatcher("/bg_effectview.jsp").forward(request,response);
		} else {
			t = new Commodity();            
			String imageType[] = { "JPG", "jpg", "gif", "bmp", "png","PNG","BMP" };
			for (int ii = 0; ii < imageType.length; ii++) {
				if (imageType[ii].equals(getType)) {
					t.setUsersID(Integer.parseInt(usersID));            
					t.setCommodityImageUrl("goodsPicture/"+uploadFile.upload(dir, formFile));            
					t.setCommodityName(commodityName);            
					t.setCommodityDetail(commodityDetail);            
					t.setCommodityAddress(commodityAddress);            
					t.setCommodityPrice(commodityPrice);            
					t.setCategoryID(Integer.parseInt(categoryID));            
					t.setCommodityBargain(Integer.parseInt(commodityBargain));            
					t.setCommodityPhone(Integer.parseInt(commodityPhone));            
					t.setCommodityQQ(Integer.parseInt(commodityQQ));            
					t.setCommodityAddTime(commodityAddTime);            
					t.setCommodityStatus(Integer.parseInt(commodityStatus));            
					t.setCommodityViews(commodityViews);  
				}
			}
			int n = dao.addCommodity(t);
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
	throws ServletException, IOException,Exception {
		UploadFile uploadFile = new UploadFile();
		String dir = this.getServletContext().getRealPath("/goodsPicture");
		HttpSession session = request.getSession();            
        String usersID = new String(request.getParameter("usersID").getBytes("ISO-8859-1"), "utf-8");            
        String commodityImageUrl = new String(request.getParameter("commodityImageUrl").getBytes("ISO-8859-1"), "utf-8"); 
        String imageUrl = new String(request.getParameter("imageUrl").getBytes("ISO-8859-1"), "utf-8");
        String commodityName = new String(request.getParameter("commodityName").getBytes("ISO-8859-1"), "utf-8");            
        String commodityDetail = new String(request.getParameter("commodityDetail").getBytes("ISO-8859-1"), "utf-8");            
        String commodityAddress = new String(request.getParameter("commodityAddress").getBytes("ISO-8859-1"), "utf-8");            
        String commodityPrice = new String(request.getParameter("commodityPrice").getBytes("ISO-8859-1"), "utf-8");            
        String categoryID = new String(request.getParameter("categoryID").getBytes("ISO-8859-1"), "utf-8");            
        String commodityBargain = new String(request.getParameter("commodityBargain").getBytes("ISO-8859-1"), "utf-8");            
        String commodityPhone = new String(request.getParameter("commodityPhone").getBytes("ISO-8859-1"), "utf-8");            
        String commodityQQ = new String(request.getParameter("commodityQQ").getBytes("ISO-8859-1"), "utf-8");            
        String commodityAddTime = new String(request.getParameter("commodityAddTime").getBytes("ISO-8859-1"), "utf-8");            
        String commodityStatus = new String(request.getParameter("commodityStatus").getBytes("ISO-8859-1"), "utf-8");            
        String commodityViews = new String(request.getParameter("commodityViews").getBytes("ISO-8859-1"), "utf-8");  
		String id = request.getParameter("id");
		Commodity t = null;
		if (usersID.equals("")||commodityName.equals("")||commodityDetail.equals("")||commodityAddress.equals("")||commodityPrice.equals("")||categoryID.equals("")||commodityBargain.equals("")||commodityPhone.equals("")||commodityQQ.equals("")||commodityAddTime.equals("")||commodityStatus.equals("")) {
			session.setAttribute("effectview", "请填写完整");
			request.getRequestDispatcher("/bg_effectview.jsp").forward(request,response);
		} else {
			t = new Commodity();
	        if (commodityImageUrl.equals("")) {
	        	t.setCommodityImageUrl(imageUrl);
			} else {
		        File formFile=new File(commodityImageUrl);
		        String getType = formFile.getName().substring(formFile.getName().lastIndexOf(".") + 1);
				String imageType[] = { "JPG", "jpg", "gif", "bmp", "png","PNG","BMP" };
				for (int ii = 0; ii < imageType.length; ii++) {
					if (imageType[ii].equals(getType)) {
						t.setCommodityImageUrl("goodsPicture/"+uploadFile.upload(dir, formFile));            
					}
				}
			}
			t.setId(Integer.parseInt(id));
			t.setUsersID(Integer.parseInt(usersID));
			t.setCommodityName(commodityName);            
			t.setCommodityDetail(commodityDetail);            
			t.setCommodityAddress(commodityAddress);            
			t.setCommodityPrice(commodityPrice);            
			t.setCategoryID(Integer.parseInt(categoryID));            
			t.setCommodityBargain(Integer.parseInt(commodityBargain));            
			t.setCommodityPhone(Integer.parseInt(commodityPhone));            
			t.setCommodityQQ(Integer.parseInt(commodityQQ));            
			t.setCommodityAddTime(commodityAddTime);            
			t.setCommodityStatus(Integer.parseInt(commodityStatus));            
			t.setCommodityViews(Integer.parseInt(commodityViews));  
			int n = dao.updateCommodity(t);
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