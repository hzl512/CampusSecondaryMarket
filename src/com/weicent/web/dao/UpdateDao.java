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
public class UpdateDao{
	private Connection connection = null; // 定义连接的对象
	private PreparedStatement ps = null; // 定义预准备的对象
	private ConnDAO jdbc = null; // 定义数据库连接对象

	public UpdateDao() {
		jdbc = new ConnDAO();
		connection = jdbc.getConnection(); // 利用构造方法取得数据库连接
	}
	
    //查询信息-泛型数据集-JSON
    public  ResUpdateList getResUpdateList(BaseRequest req){
    	ResUpdateList result=new ResUpdateList();
    	ArrayList<Update> t1=new ArrayList<Update>();
    	Update t2;
    	String sqlString="SELECT * FROM t_update  LIMIT ?,?";
    	try{
    		if (req.paging) {
        		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1,(req.page_no-1) * req.page_size);
        		ps.setInt(2, req.page_size);
			}else {
				sqlString="SELECT * FROM t_update";
	    		ps=connection.prepareStatement(sqlString);
			}
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){
    				t2=new Update();         
    				t2.setId(rs.getInt(1));         
    				t2.setVersionName(rs.getString(2));         
    				t2.setVersionNumber(rs.getString(3));         
    				t2.setForcedUpdate(rs.getInt(4));         
    				t2.setDescription(rs.getString(5));         
    				t2.setUrl(rs.getString(6));         
    				t2.setCreateTime(rs.getString(7));         
    				t2.setSize(rs.getString(8)); 
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
    		
			ResultSet rs1 = connection.createStatement().executeQuery("SELECT COUNT(*) FROM t_update");
			if (rs1.next()) {
				result.total = rs1.getInt(1);
			}
    		System.out.println("UpdateDao-getUpdateList:"+sqlString);
    		rs.close();
    		rs1.close();
    	}catch(Exception e){
    		result.errorcode=-1;
    		result.errormsg=e.toString();
    		result.data=null;
    		System.out.println(e.toString());
    		System.out.println("UpdateDao-getUpdateList 失败！"+sqlString);
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
    public  ArrayList<Update> getUpdateList(BaseRequest req){
    	ArrayList<Update> result=new ArrayList<Update>();
    	Update t;
    	String sqlString="SELECT * FROM t_update LIMIT ?,?";
    	try{
    		if (req.paging) {
        		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1,(req.page_no-1) * req.page_size);
        		ps.setInt(2, req.page_size);
			}else {
				sqlString="SELECT * FROM t_update";
	    		ps=connection.prepareStatement(sqlString);
			}
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){        
    				t=new Update();         
    				t.setId(rs.getInt(1));         
    				t.setVersionName(rs.getString(2));         
    				t.setVersionNumber(rs.getString(3));         
    				t.setForcedUpdate(rs.getInt(4));         
    				t.setDescription(rs.getString(5));         
    				t.setUrl(rs.getString(6));         
    				t.setCreateTime(rs.getString(7));         
    				t.setSize(rs.getString(8)); 
    				System.out.println(t.toString());
    				result.add(t);
    			}
    		}
    		System.out.println("UpdateDao-getUpdateList:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		System.out.println(e.toString());
    		System.out.println("UpdateDao-getUpdateList 失败！"+sqlString);
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
    public  ArrayList<Update> getUpdateListAll(String type){
    	ArrayList<Update> result=new ArrayList<Update>();
    	Update t;
    	String sqlString="SELECT * FROM t_update where id=?";
    	try{
    		if (!type.equals("")) {
        		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1, Integer.parseInt(type));
			}else {
				sqlString="SELECT * FROM t_update";
	    		ps=connection.prepareStatement(sqlString);
			}
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){
    				t=new Update();         
    				t.setId(rs.getInt(1));         
    				t.setVersionName(rs.getString(2));         
    				t.setVersionNumber(rs.getString(3));         
    				t.setForcedUpdate(rs.getInt(4));         
    				t.setDescription(rs.getString(5));         
    				t.setUrl(rs.getString(6));         
    				t.setCreateTime(rs.getString(7));         
    				t.setSize(rs.getString(8)); 
    				System.out.println(t.toString());
    				result.add(t);
    			}
    		}
    		System.out.println("UpdateDao-getUpdateListAll:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		System.out.println(e.toString());
    		System.out.println("UpdateDao-getUpdateListAll 失败！"+sqlString);
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
    public  ResUpdate getResUpdate(String id){
    	ResUpdate result=new ResUpdate();
    	Update t1=new Update();
    	String sqlString="select * from t_update where id=? ";
    	try{
    		ps=connection.prepareStatement(sqlString);
    		ps.setInt(1, Integer.parseInt(id));
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){         
    				t1.setId(rs.getInt(1));         
    				t1.setVersionName(rs.getString(2));         
    				t1.setVersionNumber(rs.getString(3));         
    				t1.setForcedUpdate(rs.getInt(4));         
    				t1.setDescription(rs.getString(5));         
    				t1.setUrl(rs.getString(6));         
    				t1.setCreateTime(rs.getString(7));         
    				t1.setSize(rs.getString(8)); 
    			}
        		result.errorcode=0;
        		result.errormsg="请求成功！";
        		result.data=t1;
    		}else {
        		result.errorcode=0;
        		result.errormsg="请求成功！";
        		result.data=null;
			}
    		System.out.println("UpdateDao-getUpdate:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		result.errorcode=-1;
			result.errormsg=e.toString();
    		result.data=null;
    		System.out.println(e.toString());
    		System.out.println("UpdateDao-getResUpdate 失败！"+sqlString);
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
    
    //查询信息-单条数据集-JSON
    public  ResUpdate getResUpdate1(){
    	ResUpdate result=new ResUpdate();
    	Update t1=new Update();
    	String sqlString="SELECT  * FROM t_update ORDER BY id DESC LIMIT 1";
    	try{
    		ps=connection.prepareStatement(sqlString);
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){         
    				t1.setId(rs.getInt(1));         
    				t1.setVersionName(rs.getString(2));         
    				t1.setVersionNumber(rs.getString(3));         
    				t1.setForcedUpdate(rs.getInt(4));         
    				t1.setDescription(rs.getString(5));         
    				t1.setUrl(rs.getString(6));         
    				t1.setCreateTime(rs.getString(7));         
    				t1.setSize(rs.getString(8)); 
    				t1.setApkSize(rs.getLong(9)); 
    			}
        		result.errorcode=0;
        		result.errormsg="请求成功！";
        		result.data=t1;
    		}else {
        		result.errorcode=0;
        		result.errormsg="请求成功！";
        		result.data=null;
			}
    		System.out.println("UpdateDao-getUpdate:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		result.errorcode=-1;
			result.errormsg=e.toString();
    		result.data=null;
    		System.out.println(e.toString());
    		System.out.println("UpdateDao-getResUpdate 失败！"+sqlString);
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
	public BaseResponse deleteResUpdate(Update t) {
		BaseResponse result=new BaseResponse();
		int n = 0;// n=0说明删除不成功
		String sqlString = "delete from t_update where id=?";
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
    		System.out.println("UpdateDao-deleteResUpdate 失败！"+sqlString);
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
	public int deleteUpdate(String id) {
		int n = 0;// n=0说明删除不成功
		String sqlString = "delete from t_update where id=?";
		try {
			ps = connection.prepareStatement(sqlString);
			ps.setString(1, id);
			n = ps.executeUpdate();
		} catch (Exception e) {
    		System.out.println("UpdateDao-deleteUpdate 失败！"+sqlString);
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
    public BaseResponse addResUpdate(Update t){
		BaseResponse result=new BaseResponse();
        int n=0;//n=0说明添加不成功
		String sqlString="insert into t_update(versionName,versionNumber,forcedUpdate,description,url,createTime,size) "+"values(?,?,?,?,?,?,?)";
    	try{
    		ps=connection.prepareStatement(sqlString);            
            ps.setString(1, t.getVersionName());            
            ps.setString(2, t.getVersionNumber());            
            ps.setInt(3, t.getForcedUpdate());            
            ps.setString(4, t.getDescription());            
            ps.setString(5, t.getUrl());            
            ps.setString(6, t.getCreateTime());            
            ps.setString(7, t.getSize());         
    		n=ps.executeUpdate();
			if (n>0) {
				result.errorcode=0;
				result.errormsg="添加成功！";
			} else {
				result.errorcode=-1;
				result.errormsg="添加失败！";
			}
    		System.out.println("UpdateDao-addResUpdate:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
			result.errorcode=-1;
			result.errormsg=e.toString();
    		System.out.println("UpdateDao-addResUpdate 失败！");
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
    public  int addUpdate(Update t){
        int n=0;//n=0说明添加不成功
		String sqlString="insert into t_update(versionName,versionNumber,forcedUpdate,description,url,createTime,size,apkSize) "+"values(?,?,?,?,?,?,?,?)";
    	try{
    		ps=connection.prepareStatement(sqlString);            
            ps.setString(1, t.getVersionName());            
            ps.setString(2, t.getVersionNumber());            
            ps.setInt(3, t.getForcedUpdate());            
            ps.setString(4, t.getDescription());            
            ps.setString(5, t.getUrl());            
            ps.setString(6, t.getCreateTime());            
            ps.setString(7, t.getSize());    
            ps.setLong(8, t.getApkSize());
    		n=ps.executeUpdate();
    		System.out.println("UpdateDao-addUpdate:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
    		System.out.println("UpdateDao-addUpdate 失败！");
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
    public  BaseResponse updateResUpdate(Update t){
		BaseResponse result=new BaseResponse();
        int n=0;//n=0说明修改不成功
		String sqlString="update t_update set versionName=?,versionNumber=?,forcedUpdate=?,description=?,url=?,createTime=?,size=?  where id=?";
    	try{
    		ps=connection.prepareStatement(sqlString);         
    		ps.setInt(8 , t.getId());         
    		ps.setString(1 , t.getVersionName());         
    		ps.setString(2 , t.getVersionNumber());         
    		ps.setInt(3 , t.getForcedUpdate());         
    		ps.setString(4 , t.getDescription());         
    		ps.setString(5 , t.getUrl());         
    		ps.setString(6 , t.getCreateTime());         
    		ps.setString(7 , t.getSize());         
    		n=ps.executeUpdate();
			if (n>0) {
				result.errorcode=0;
				result.errormsg="修改成功！";
			} else {
				result.errorcode=-1;
				result.errormsg="修改失败！";
			}
    		System.out.println("UpdateDao-updateResUpdate:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
			result.errorcode=-1;
			result.errormsg=e.toString();
    		System.out.println("UpdateDao-updateResUpdate 失败！"+sqlString);
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
    public  int updateUpdate(Update t){
        int n=0;//n=0说明修改不成功
		String sqlString="update t_update set versionName=?,versionNumber=?,forcedUpdate=?,description=?,url=?,createTime=?,size=?,apkSize=?  where id=?";
    	try{
    		ps=connection.prepareStatement(sqlString);         
    		ps.setInt(9 , t.getId());         
    		ps.setString(1 , t.getVersionName());         
    		ps.setString(2 , t.getVersionNumber());         
    		ps.setInt(3 , t.getForcedUpdate());         
    		ps.setString(4 , t.getDescription());         
    		ps.setString(5 , t.getUrl());         
    		ps.setString(6 , t.getCreateTime());         
    		ps.setString(7 , t.getSize());   
    		ps.setLong(8 , t.getApkSize());
    		n=ps.executeUpdate();
    		System.out.println("UpdateDao-updateUpdate:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
    		System.out.println("UpdateDao-updateUpdate 失败！"+sqlString);
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
    public  Update getUpdate(String id){
    	Update t=new Update();
    	String sqlString="select * from t_update where id=?";
    	try{
    		ps=connection.prepareStatement(sqlString);
    		ps.setInt(1, Integer.parseInt(id));
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){         
    				t.setId(rs.getInt(1));         
    				t.setVersionName(rs.getString(2));         
    				t.setVersionNumber(rs.getString(3));         
    				t.setForcedUpdate(rs.getInt(4));         
    				t.setDescription(rs.getString(5));         
    				t.setUrl(rs.getString(6));         
    				t.setCreateTime(rs.getString(7));         
    				t.setSize(rs.getString(8)); 
    				t.setApkSize(rs.getLong(9)); 
    			}
    		}
    		System.out.println("UpdateDao-getUpdate:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		System.out.println(e.toString());
    		System.out.println("UpdateDao-getUpdate 失败！"+sqlString);
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