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
public class ManageDao{
	private Connection connection = null; // 定义连接的对象
	private PreparedStatement ps = null; // 定义预准备的对象
	private ConnDAO jdbc = null; // 定义数据库连接对象

	public ManageDao() {
		jdbc = new ConnDAO();
		connection = jdbc.getConnection(); // 利用构造方法取得数据库连接
	}
	
    //查询管理员信息
    public Manage selectInformation(Manage t){
    	Manage t1=new Manage();
    	String sqlString="select * from t_manage where manageName=? and managePassWord=?";
    	try{
    		ps=connection.prepareStatement(sqlString);
    		ps.setString(1,t.getManageName());
    		ps.setString(2,t.getManagePassWord());
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){
    				t1.setId(Integer.parseInt(rs.getString(1)));
    				t1.setManageName(rs.getString(2));
    				t1.setManagePassWord(rs.getString(3));
    			}
    		}
    		System.out.println("ManageDao-selectInformation:"+sqlString);
    		rs.close();
    		ps.close();
    	}catch(Exception e){
    		System.out.println(e.toString());
    		System.out.println("ManageDao-selectInformation 失败！"+sqlString);
    	}
    	return t1;
    }
	
    //查询信息-泛型数据集-JSON
    public  ResManageList getResManageList(BaseRequest req){
    	ResManageList result=new ResManageList();
    	ArrayList<Manage> t1=new ArrayList<Manage>();
    	Manage t2;
    	String sqlString="SELECT * FROM t_manage  LIMIT ?,?";
    	try{
    		if (req.paging) {
        		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1,(req.page_no-1) * req.page_size);
        		ps.setInt(2, req.page_size);
			}else {
				sqlString="SELECT * FROM t_manage";
	    		ps=connection.prepareStatement(sqlString);
			}
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){
    				t2=new Manage();         
    				t2.setId(rs.getInt(1));         
    				t2.setManageName(rs.getString(2));         
    				t2.setManagePassWord(rs.getString(3));         
    				t2.setManageRemark(rs.getString(4)); 
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
    		
			ResultSet rs1 = connection.createStatement().executeQuery("SELECT COUNT(*) FROM t_manage");
			if (rs1.next()) {
				result.total = rs1.getInt(1);
			}
    		System.out.println("ManageDao-getManageList:"+sqlString);
    		rs.close();
    		rs1.close();
    	}catch(Exception e){
    		result.errorcode=-1;
    		result.errormsg=e.toString();
    		result.data=null;
    		System.out.println(e.toString());
    		System.out.println("ManageDao-getManageList 失败！"+sqlString);
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
    public  ArrayList<Manage> getManageList(BaseRequest req){
    	ArrayList<Manage> result=new ArrayList<Manage>();
    	Manage t;
    	String sqlString="SELECT * FROM t_manage LIMIT ?,?";
    	try{
    		if (req.paging) {
        		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1,(req.page_no-1) * req.page_size);
        		ps.setInt(2, req.page_size);
			}else {
				sqlString="SELECT * FROM t_manage";
	    		ps=connection.prepareStatement(sqlString);
			}
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){        
    				t=new Manage();         
    				t.setId(rs.getInt(1));         
    				t.setManageName(rs.getString(2));         
    				t.setManagePassWord(rs.getString(3));         
    				t.setManageRemark(rs.getString(4)); 
    				System.out.println(t.toString());
    				result.add(t);
    			}
    		}
    		System.out.println("ManageDao-getManageList:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		System.out.println(e.toString());
    		System.out.println("ManageDao-getManageList 失败！"+sqlString);
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
    public  ArrayList<Manage> getManageListAll(String type){
    	ArrayList<Manage> result=new ArrayList<Manage>();
    	Manage t;
    	String sqlString="SELECT * FROM t_manage where id=?";
    	try{
    		if (!type.equals("")) {
        		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1, Integer.parseInt(type));
			}else {
				sqlString="SELECT * FROM t_manage";
	    		ps=connection.prepareStatement(sqlString);
			}
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){
    				t=new Manage();         
    				t.setId(rs.getInt(1));         
    				t.setManageName(rs.getString(2));         
    				t.setManagePassWord(rs.getString(3));         
    				t.setManageRemark(rs.getString(4)); 
    				System.out.println(t.toString());
    				result.add(t);
    			}
    		}
    		System.out.println("ManageDao-getManageListAll:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		System.out.println(e.toString());
    		System.out.println("ManageDao-getManageListAll 失败！"+sqlString);
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
    
    //查询信息-单条数据集-JSON
    public  ResManage getResManage(String id){
    	ResManage result=new ResManage();
    	Manage t1=new Manage();
    	String sqlString="select * from t_manage where id=? ";
    	try{
    		ps=connection.prepareStatement(sqlString);
    		ps.setInt(1, Integer.parseInt(id));
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){         
    				t1.setId(rs.getInt(1));         
    				t1.setManageName(rs.getString(2));         
    				t1.setManagePassWord(rs.getString(3));         
    				t1.setManageRemark(rs.getString(4)); 
    			}
        		result.errorcode=0;
        		result.errormsg="请求成功！";
        		result.data=t1;
    		}else {
        		result.errorcode=0;
        		result.errormsg="请求成功！";
        		result.data=null;
			}
    		System.out.println("ManageDao-getManage:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		result.errorcode=-1;
			result.errormsg=e.toString();
    		result.data=null;
    		System.out.println(e.toString());
    		System.out.println("ManageDao-getResManage 失败！"+sqlString);
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
	public BaseResponse deleteResManage(Manage t) {
		BaseResponse result=new BaseResponse();
		int n = 0;// n=0说明删除不成功
		String sqlString = "delete from t_manage where id=?";
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
    		System.out.println("ManageDao-deleteResManage 失败！"+sqlString);
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
	public int deleteManage(String id) {
		int n = 0;// n=0说明删除不成功
		String sqlString = "delete from t_manage where id=?";
		try {
			ps = connection.prepareStatement(sqlString);
			ps.setString(1, id);
			n = ps.executeUpdate();
		} catch (Exception e) {
    		System.out.println("ManageDao-deleteManage 失败！"+sqlString);
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
    public BaseResponse addResManage(Manage t){
		BaseResponse result=new BaseResponse();
        int n=0;//n=0说明添加不成功
		String sqlString="insert into t_manage(manageName,managePassWord,manageRemark) "+"values(?,?,?)";
    	try{
    		ps=connection.prepareStatement(sqlString);            
            ps.setString(1, t.getManageName());            
            ps.setString(2, t.getManagePassWord());            
            ps.setString(3, t.getManageRemark());         
    		n=ps.executeUpdate();
			if (n>0) {
				result.errorcode=0;
				result.errormsg="添加成功！";
			} else {
				result.errorcode=-1;
				result.errormsg="添加失败！";
			}
    		System.out.println("ManageDao-addResManage:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
			result.errorcode=-1;
			result.errormsg=e.toString();
    		System.out.println("ManageDao-addResManage 失败！");
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
    public  int addManage(Manage t){
        int n=0;//n=0说明添加不成功
		String sqlString="insert into t_manage(manageName,managePassWord,manageRemark) "+"values(?,?,?)";
    	try{
    		ps=connection.prepareStatement(sqlString);            
            ps.setString(1, t.getManageName());            
            ps.setString(2, t.getManagePassWord());            
            ps.setString(3, t.getManageRemark());         
    		n=ps.executeUpdate();
    		System.out.println("ManageDao-addManage:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
    		System.out.println("ManageDao-addManage 失败！");
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
    public  BaseResponse updateResManage(Manage t){
		BaseResponse result=new BaseResponse();
        int n=0;//n=0说明修改不成功
		String sqlString="update t_manage set manageName=?,managePassWord=?,manageRemark=?  where id=?";
    	try{
    		ps=connection.prepareStatement(sqlString);         
    		ps.setInt(4 , t.getId());         
    		ps.setString(1 , t.getManageName());         
    		ps.setString(2 , t.getManagePassWord());         
    		ps.setString(3 , t.getManageRemark());         
    		n=ps.executeUpdate();
			if (n>0) {
				result.errorcode=0;
				result.errormsg="修改成功！";
			} else {
				result.errorcode=-1;
				result.errormsg="修改失败！";
			}
    		System.out.println("ManageDao-updateResManage:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
			result.errorcode=-1;
			result.errormsg=e.toString();
    		System.out.println("ManageDao-updateResManage 失败！"+sqlString);
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
    public  int updateManage(Manage t){
        int n=0;//n=0说明修改不成功
		String sqlString="update t_manage set manageName=?,managePassWord=?,manageRemark=?  where id=?";
    	try{
    		ps=connection.prepareStatement(sqlString);         
    		ps.setInt(4 , t.getId());         
    		ps.setString(1 , t.getManageName());         
    		ps.setString(2 , t.getManagePassWord());         
    		ps.setString(3 , t.getManageRemark());         
    		n=ps.executeUpdate();
    		System.out.println("ManageDao-updateManage:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
    		System.out.println("ManageDao-updateManage 失败！"+sqlString);
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
    public  Manage getManage(String id){
    	Manage t=new Manage();
    	String sqlString="select * from t_manage where id=?";
    	try{
    		ps=connection.prepareStatement(sqlString);
    		ps.setInt(1, Integer.parseInt(id));
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){         
    				t.setId(rs.getInt(1));         
    				t.setManageName(rs.getString(2));         
    				t.setManagePassWord(rs.getString(3));         
    				t.setManageRemark(rs.getString(4)); 
    			}
    		}
    		System.out.println("ManageDao-getManage:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		System.out.println(e.toString());
    		System.out.println("ManageDao-getManage 失败！"+sqlString);
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