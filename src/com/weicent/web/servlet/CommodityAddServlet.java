package com.weicent.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.weicent.web.dao.CommodityDao;
import com.weicent.web.domain.Commodity;
import com.weicent.web.response.BaseResponse;
import com.weicent.web.tool.AbDateUtil;
import com.weicent.web.tool.ResponseJsonForObject;
import com.weicent.web.tool.UploadFile;

/**
 * 客户端添加商品
 */
public class CommodityAddServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		// 创建文件项目工厂对象
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 获取系统默认的临时文件保存路径，该路径为Tomcat根目录下的temp文件夹
		String temp = System.getProperty("java.io.tmpdir");
		// 设置缓冲区大小为 5M
		factory.setSizeThreshold(1024 * 1024 * 5);
		// 设置临时文件夹为temp
		factory.setRepository(new File(temp));
		// 用工厂实例化上传组件,ServletFileUpload 用来解析文件上传请求
		ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
		servletFileUpload.setHeaderEncoding("UTF-8");//解决http报头乱码，即中文文件名乱码
		// 操作之后回馈的信息
		BaseResponse responseDefault=new BaseResponse();
		// 解析结果放在List中
		try {
			CommodityDao dao = new CommodityDao();
			Commodity t = new Commodity();
			List<FileItem> list = servletFileUpload.parseRequest(request);
			for (FileItem item : list) {
				String name = item.getFieldName();
				if (name.contains("usersID")) {
					t.setUsersID(Integer.parseInt(item.getString("UTF-8")));   
				} else if (name.contains("file")) {
					try {
						UploadFile uploadFile = new UploadFile();
						String dir = this.getServletContext().getRealPath("/goodsPicture");
						String resString="goodsPicture/"+uploadFile.upload1(dir, item.getInputStream(),item.getName());
						System.out.println(resString);
						t.setCommodityImageUrl(resString);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else if (name.contains("commodityName")) {
					System.out.println(item.getString("UTF-8"));
					t.setCommodityName(item.getString("UTF-8"));
				}else if (name.contains("commodityDetail")) {
					t.setCommodityDetail(item.getString("UTF-8")); 
				}else if (name.contains("commodityAddress")) {
					t.setCommodityAddress(item.getString("UTF-8")); 
				}else if (name.contains("commodityPrice")) {
					t.setCommodityPrice(item.getString("UTF-8"));   
				}else if (name.contains("categoryID")) {
					t.setCategoryID(Integer.parseInt(item.getString("UTF-8")));     
				}else if (name.contains("commodityBargain")) {
					t.setCommodityBargain(Integer.parseInt(item.getString("UTF-8")));
				}else if (name.contains("commodityPhone")) {
					t.setCommodityPhone(Integer.parseInt(item.getString("UTF-8"))); 
				}else if (name.contains("commodityQQ")) {
					t.setCommodityQQ(Integer.parseInt(item.getString("UTF-8"))); 
				}
			}
			t.setCommodityAddTime(AbDateUtil.getCurrentDateStringFordateFormatYMDHM2());            
			t.setCommodityStatus(0);            
			t.setCommodityViews(0);  
			System.out.println(t.toString());
			int n = dao.addCommodity(t);
			System.out.println(n);
			if (n > 0) {
				responseDefault.errorcode=0;
				responseDefault.errormsg="操作成功！";
			}else {
				responseDefault.errorcode=-1;
				responseDefault.errormsg="添加商品失败！";
			}
			response.getWriter().println(ResponseJsonForObject.createJsonString(responseDefault));
		} catch (FileUploadException e) {
			e.printStackTrace();
			responseDefault.errorcode=-1;
			responseDefault.errormsg="请求类型错误！";
			response.getWriter().println(ResponseJsonForObject.createJsonString(responseDefault));
		}
		out.flush();
		out.close();
	}

}
