package com.weicent.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.weicent.web.domain.*;
import com.weicent.web.model.*;
import com.weicent.web.response.*;
import com.weicent.web.request.*;
import com.weicent.web.response.list.*;
import com.weicent.web.response.model.*;
import com.weicent.web.tool.*;

/**
 * 
 */
public class CategoryDao{
	private Connection connection = null; // 定义连接的对象
	private PreparedStatement ps = null; // 定义预准备的对象
	private ConnDAO jdbc = null; // 定义数据库连接对象

	public CategoryDao() {
		jdbc = new ConnDAO();
		connection = jdbc.getConnection(); // 利用构造方法取得数据库连接
	}
	
    //查询信息-泛型数据集-JSON
    public  ResCategoryList getResCategoryList(BaseRequest req){
    	ResCategoryList result=new ResCategoryList();
    	ArrayList<Category> t1=new ArrayList<Category>();
    	Category t2;
    	String sqlString="SELECT * FROM t_category  LIMIT ?,?";
    	try{
    		if (req.paging) {
        		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1,(req.page_no-1) * req.page_size);
        		ps.setInt(2, req.page_size);
			}else {
				sqlString="SELECT * FROM t_category";
	    		ps=connection.prepareStatement(sqlString);
			}
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){
    				t2=new Category();         
    				t2.setId(rs.getInt(1));         
    				t2.setCategoryName(rs.getString(2));         
    				t2.setCategoryRemark(rs.getString(3)); 
    				System.out.println(t2.toString());
    				t1.add(t2);
    			}
        		result.errorcode=0;
        		result.errormsg="请求成功！";
        		result.data=t1;
    		}else {
        		result.errorcode=0;
        		result.errormsg="请求成功！";
        		result.data=null;
			}
    		
			ResultSet rs1 = connection.createStatement().executeQuery("SELECT COUNT(*) FROM t_category");
			if (rs1.next()) {
				result.total = rs1.getInt(1);
			}
    		System.out.println("CategoryDao-getCategoryList:"+sqlString);
    		rs.close();
    		rs1.close();
    	}catch(Exception e){
    		result.errorcode=-1;
    		result.errormsg=e.toString();
    		result.data=null;
    		System.out.println(e.toString());
    		System.out.println("CategoryDao-getCategoryList 失败！"+sqlString);
    	}finally{
			try {
				ps.close();
				connection.close();
			} catch (Exception e2) {
				result.errorcode=-1;
				result.errormsg=e2.toString();
				System.out.println(e2.toString());
			}
		}
    	return result;
    }
    
    //查询信息-泛型数据集-分页
    public  ArrayList<Category> getCategoryList(BaseRequest req){
    	ArrayList<Category> result=new ArrayList<Category>();
    	Category t;
    	String sqlString="SELECT * FROM t_category LIMIT ?,?";
    	try{
    		if (req.paging) {
        		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1,(req.page_no-1) * req.page_size);
        		ps.setInt(2, req.page_size);
			}else {
				sqlString="SELECT * FROM t_category";
	    		ps=connection.prepareStatement(sqlString);
			}
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){        
    				t=new Category();         
    				t.setId(rs.getInt(1));         
    				t.setCategoryName(rs.getString(2));         
    				t.setCategoryRemark(rs.getString(3)); 
    				System.out.println(t.toString());
    				result.add(t);
    			}
    		}
    		System.out.println("CategoryDao-getCategoryList:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		System.out.println(e.toString());
    		System.out.println("CategoryDao-getCategoryList 失败！"+sqlString);
    	}finally{
			try {
				ps.close();
				connection.close();
			} catch (Exception e2) {
				System.out.println(e2.toString());
			}
		}
    	return result;
    }
    
    //查询信息-泛型数据集-参数
    public  ArrayList<Category> getCategoryListAll(String type){
    	ArrayList<Category> result=new ArrayList<Category>();
    	Category t;
    	String sqlString="SELECT * FROM t_category where id=?";
    	try{
    		if (!type.equals("")) {
        		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1, Integer.parseInt(type));
			}else {
				sqlString="SELECT * FROM t_category";
	    		ps=connection.prepareStatement(sqlString);
			}
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){
    				t=new Category();         
    				t.setId(rs.getInt(1));         
    				t.setCategoryName(rs.getString(2));         
    				t.setCategoryRemark(rs.getString(3)); 
    				System.out.println(t.toString());
    				result.add(t);
    			}
    		}
    		System.out.println("CategoryDao-getCategoryListAll:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		System.out.println(e.toString());
    		System.out.println("CategoryDao-getCategoryListAll 失败！"+sqlString);
    	}finally{
			try {
				ps.close();
//				connection.close();
			} catch (Exception e2) {
				System.out.println(e2.toString());
			}
		}
    	return result;
    }
    
    //查询信息-单条数据集-JSON
    public  ResCategory getResCategory(String id){
    	ResCategory result=new ResCategory();
    	Category t1=new Category();
    	String sqlString="select * from t_category where id=? ";
    	try{
    		ps=connection.prepareStatement(sqlString);
    		ps.setInt(1, Integer.parseInt(id));
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){         
    				t1.setId(rs.getInt(1));         
    				t1.setCategoryName(rs.getString(2));         
    				t1.setCategoryRemark(rs.getString(3)); 
    			}
        		result.errorcode=0;
        		result.errormsg="请求成功！";
        		result.data=t1;
    		}else {
        		result.errorcode=0;
        		result.errormsg="请求成功！";
        		result.data=null;
			}
    		System.out.println("CategoryDao-getCategory:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		result.errorcode=-1;
			result.errormsg=e.toString();
    		result.data=null;
    		System.out.println(e.toString());
    		System.out.println("CategoryDao-getResCategory 失败！"+sqlString);
    	}finally{
			try {
				ps.close();
				connection.close();
			} catch (Exception e2) {
				result.errorcode=-1;
				result.errormsg=e2.toString();
				System.out.println(e2.toString());
			}
		}
    	return result;
    }
    
    //删除信息-JSON
	public BaseResponse deleteResCategory(Category t) {
		BaseResponse result=new BaseResponse();
		int n = 0;// n=0说明删除不成功
		String sqlString = "delete from t_category where id=?";
		try {
    		ps=connection.prepareStatement(sqlString);         
    		ps.setInt(1, t.getId());         
    		n=ps.executeUpdate();
			if (n>0) {
				result.errorcode=0;
				result.errormsg="删除成功！";
			} else {
				result.errorcode=-1;
				result.errormsg="删除失败！";
			}
		} catch (Exception e) {
			result.errorcode=-1;
			result.errormsg=e.toString();
    		System.out.println("CategoryDao-deleteResCategory 失败！"+sqlString);
		}finally{
			try {
				ps.close();
				connection.close();
			} catch (Exception e2) {
				result.errorcode=-1;
				result.errormsg=e2.toString();
				System.out.println(e2.toString());
			}
		}
		return result;
	}
	
	//删除信息
	public int deleteCategory(String id) {
		int n = 0;// n=0说明删除不成功
		String sqlString = "delete from t_category where id=?";
		try {
			ps = connection.prepareStatement(sqlString);
			ps.setString(1, id);
			n = ps.executeUpdate();
		} catch (Exception e) {
    		System.out.println("CategoryDao-deleteCategory 失败！"+sqlString);
		}finally{
			try {
				ps.close();
				connection.close();
			} catch (Exception e2) {
				System.out.println(e2.toString());
			}
		}
		return n;
	}
	
	//添加信息-JSON
    public BaseResponse addResCategory(Category t){
		BaseResponse result=new BaseResponse();
        int n=0;//n=0说明添加不成功
		String sqlString="insert into t_category(categoryName,categoryRemark) "+"values(?,?)";
    	try{
    		ps=connection.prepareStatement(sqlString);            
            ps.setString(1, t.getCategoryName());            
            ps.setString(2, t.getCategoryRemark());         
    		n=ps.executeUpdate();
			if (n>0) {
				result.errorcode=0;
				result.errormsg="添加成功！";
			} else {
				result.errorcode=-1;
				result.errormsg="添加失败！";
			}
    		System.out.println("CategoryDao-addResCategory:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
			result.errorcode=-1;
			result.errormsg=e.toString();
    		System.out.println("CategoryDao-addResCategory 失败！");
    	}finally{
			try {
				ps.close();
				connection.close();
			} catch (Exception e2) {
				result.errorcode=-1;
				result.errormsg=e2.toString();
				System.out.println(e2.toString());
			}
		}
    	return result;
    }
	
	//添加信息
    public  int addCategory(Category t){
        int n=0;//n=0说明添加不成功
		String sqlString="insert into t_category(categoryName,categoryRemark) "+"values(?,?)";
    	try{
    		ps=connection.prepareStatement(sqlString);            
            ps.setString(1, t.getCategoryName());            
            ps.setString(2, t.getCategoryRemark());         
    		n=ps.executeUpdate();
    		System.out.println("CategoryDao-addCategory:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
    		System.out.println("CategoryDao-addCategory 失败！");
    	}finally{
			try {
				ps.close();
				connection.close();
			} catch (Exception e2) {
				System.out.println(e2.toString());
			}
		}
    	return n;
    }
    
    //修改信息-JOSN
    public  BaseResponse updateResCategory(Category t){
		BaseResponse result=new BaseResponse();
        int n=0;//n=0说明修改不成功
		String sqlString="update t_category set categoryName=?,categoryRemark=?  where id=?";
    	try{
    		ps=connection.prepareStatement(sqlString);         
    		ps.setInt(3 , t.getId());         
    		ps.setString(1 , t.getCategoryName());         
    		ps.setString(2 , t.getCategoryRemark());         
    		n=ps.executeUpdate();
			if (n>0) {
				result.errorcode=0;
				result.errormsg="修改成功！";
			} else {
				result.errorcode=-1;
				result.errormsg="修改失败！";
			}
    		System.out.println("CategoryDao-updateResCategory:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
			result.errorcode=-1;
			result.errormsg=e.toString();
    		System.out.println("CategoryDao-updateResCategory 失败！"+sqlString);
    	}finally{
			try {
				ps.close();
				connection.close();
			} catch (Exception e2) {
				System.out.println(e2.toString());
			}
		}
    	return result;
    }
    
    //修改信息
    public  int updateCategory(Category t){
        int n=0;//n=0说明修改不成功
		String sqlString="update t_category set categoryName=?,categoryRemark=?  where id=?";
    	try{
    		ps=connection.prepareStatement(sqlString);         
    		ps.setInt(3 , t.getId());         
    		ps.setString(1 , t.getCategoryName());         
    		ps.setString(2 , t.getCategoryRemark());         
    		n=ps.executeUpdate();
    		System.out.println("CategoryDao-updateCategory:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
    		System.out.println("CategoryDao-updateCategory 失败！"+sqlString);
    	}finally{
			try {
				ps.close();
				connection.close();
			} catch (Exception e2) {
				System.out.println(e2.toString());
			}
		}
    	return n;
    }
  
    //查询信息
    public  Category getCategory(String id){
    	Category t=new Category();
    	String sqlString="select * from t_category where id=?";
    	try{
    		ps=connection.prepareStatement(sqlString);
    		ps.setInt(1, Integer.parseInt(id));
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){         
    				t.setId(rs.getInt(1));         
    				t.setCategoryName(rs.getString(2));         
    				t.setCategoryRemark(rs.getString(3)); 
    			}
    		}
    		System.out.println("CategoryDao-getCategory:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		System.out.println(e.toString());
    		System.out.println("CategoryDao-getCategory 失败！"+sqlString);
    	}finally{
			try {
				ps.close();
				//connection.close();
			} catch (Exception e2) {
				System.out.println(e2.toString());
			}
		}
    	return t;
    }
}