package com.weicent.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.weicent.web.domain.*;
import com.weicent.web.model.*;
import com.weicent.web.response.*;
import com.weicent.web.request.*;
import com.weicent.web.request.model.ReqProfession1;
import com.weicent.web.response.list.*;
import com.weicent.web.response.model.*;
import com.weicent.web.tool.*;

/**
 * 
 */
public class ProfessionDao{
	private Connection connection = null; // 定义连接的对象
	private PreparedStatement ps = null; // 定义预准备的对象
	private ConnDAO jdbc = null; // 定义数据库连接对象

	public ProfessionDao() {
		jdbc = new ConnDAO();
		connection = jdbc.getConnection(); // 利用构造方法取得数据库连接
	}
	
    //查询信息-泛型数据集-JSON
    public  ResProfessionList getResProfessionList(BaseRequest req){
    	ResProfessionList result=new ResProfessionList();
    	ArrayList<Profession> t1=new ArrayList<Profession>();
    	Profession t2;
    	String sqlString="SELECT * FROM t_profession LIMIT ?,?";
    	try{
    		if (req.paging) {
        		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1,(req.page_no-1) * req.page_size);
        		ps.setInt(2, req.page_size);
			}else {
				sqlString="SELECT * FROM t_profession WHERE departmentsID=?";
	    		ps=connection.prepareStatement(sqlString);
			}
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){
    				t2=new Profession();         
    				t2.setId(rs.getInt(1));         
    				t2.setDepartmentsID(rs.getInt(2));         
    				t2.setProfessionName(rs.getString(3));         
    				t2.setProfessionRemark(rs.getString(4)); 
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
    		
			ResultSet rs1 = connection.createStatement().executeQuery("SELECT COUNT(*) FROM t_profession");
			if (rs1.next()) {
				result.total = rs1.getInt(1);
			}
    		System.out.println("ProfessionDao-getProfessionList:"+sqlString);
    		rs.close();
    		rs1.close();
    	}catch(Exception e){
    		result.errorcode=-1;
    		result.errormsg=e.toString();
    		result.data=null;
    		System.out.println(e.toString());
    		System.out.println("ProfessionDao-getProfessionList 失败！"+sqlString);
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
	
    //查询信息-泛型数据集-JSON
    public  ResProfessionList getResProfessionList(ReqProfession1 req){
    	ResProfessionList result=new ResProfessionList();
    	ArrayList<Profession> t1=new ArrayList<Profession>();
    	Profession t2;
    	String sqlString="SELECT * FROM t_profession WHERE departmentsID=? LIMIT ?,?";
    	try{
    		if (req.paging) {
        		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1, req.departmentsID);
        		ps.setInt(2,(req.page_no-1) * req.page_size);
        		ps.setInt(3, req.page_size);
			}else {
				sqlString="SELECT * FROM t_profession WHERE departmentsID=?";
				ps.setInt(1, req.departmentsID);
	    		ps=connection.prepareStatement(sqlString);
			}
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){
    				t2=new Profession();         
    				t2.setId(rs.getInt(1));         
    				t2.setDepartmentsID(rs.getInt(2));         
    				t2.setProfessionName(rs.getString(3));         
    				t2.setProfessionRemark(rs.getString(4)); 
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
    		
			ResultSet rs1 = connection.createStatement().executeQuery("SELECT COUNT(*) FROM t_profession WHERE departmentsID="+req.departmentsID);
			if (rs1.next()) {
				result.total = rs1.getInt(1);
			}
    		System.out.println("ProfessionDao-getProfessionList:"+sqlString);
    		rs.close();
    		rs1.close();
    	}catch(Exception e){
    		result.errorcode=-1;
    		result.errormsg=e.toString();
    		result.data=null;
    		System.out.println(e.toString());
    		System.out.println("ProfessionDao-getProfessionList 失败！"+sqlString);
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
    public  ArrayList<Profession> getProfessionList(BaseRequest req){
    	ArrayList<Profession> result=new ArrayList<Profession>();
    	Profession t;
    	String sqlString="SELECT * FROM t_profession LIMIT ?,?";
    	try{
    		if (req.paging) {
        		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1,(req.page_no-1) * req.page_size);
        		ps.setInt(2, req.page_size);
			}else {
				sqlString="SELECT * FROM t_profession";
	    		ps=connection.prepareStatement(sqlString);
			}
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){        
    				t=new Profession();         
    				t.setId(rs.getInt(1));         
    				t.setDepartmentsID(rs.getInt(2));         
    				t.setProfessionName(rs.getString(3));         
    				t.setProfessionRemark(rs.getString(4)); 
    				System.out.println(t.toString());
    				result.add(t);
    			}
    		}
    		System.out.println("ProfessionDao-getProfessionList:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		System.out.println(e.toString());
    		System.out.println("ProfessionDao-getProfessionList 失败！"+sqlString);
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
    public  ArrayList<Profession> getProfessionListAll(String type){
    	ArrayList<Profession> result=new ArrayList<Profession>();
    	Profession t;
    	String sqlString="SELECT * FROM t_profession where departmentsID=?";
    	try{
    		if (!type.equals("")) {
        		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1, Integer.parseInt(type));
			}else {
				sqlString="SELECT * FROM t_profession";
	    		ps=connection.prepareStatement(sqlString);
			}
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){
    				t=new Profession();         
    				t.setId(rs.getInt(1));         
    				t.setDepartmentsID(rs.getInt(2));         
    				t.setProfessionName(rs.getString(3));         
    				t.setProfessionRemark(rs.getString(4)); 
    				System.out.println(t.toString());
    				result.add(t);
    			}
    		}
    		System.out.println("ProfessionDao-getProfessionListAll:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		System.out.println(e.toString());
    		System.out.println("ProfessionDao-getProfessionListAll 失败！"+sqlString);
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
    public  ResProfession getResProfession(String id){
    	ResProfession result=new ResProfession();
    	Profession t1=new Profession();
    	String sqlString="select * from t_profession where id=? ";
    	try{
    		ps=connection.prepareStatement(sqlString);
    		ps.setInt(1, Integer.parseInt(id));
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){         
    				t1.setId(rs.getInt(1));         
    				t1.setDepartmentsID(rs.getInt(2));         
    				t1.setProfessionName(rs.getString(3));         
    				t1.setProfessionRemark(rs.getString(4)); 
    			}
        		result.errorcode=0;
        		result.errormsg="请求成功！";
        		result.data=t1;
    		}else {
        		result.errorcode=0;
        		result.errormsg="请求成功！";
        		result.data=null;
			}
    		System.out.println("ProfessionDao-getProfession:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		result.errorcode=-1;
			result.errormsg=e.toString();
    		result.data=null;
    		System.out.println(e.toString());
    		System.out.println("ProfessionDao-getResProfession 失败！"+sqlString);
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
	public BaseResponse deleteResProfession(Profession t) {
		BaseResponse result=new BaseResponse();
		int n = 0;// n=0说明删除不成功
		String sqlString = "delete from t_profession where id=?";
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
    		System.out.println("ProfessionDao-deleteResProfession 失败！"+sqlString);
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
	public int deleteProfession(String id) {
		int n = 0;// n=0说明删除不成功
		String sqlString = "delete from t_profession where id=?";
		try {
			ps = connection.prepareStatement(sqlString);
			ps.setString(1, id);
			n = ps.executeUpdate();
		} catch (Exception e) {
    		System.out.println("ProfessionDao-deleteProfession 失败！"+sqlString);
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
    public BaseResponse addResProfession(Profession t){
		BaseResponse result=new BaseResponse();
        int n=0;//n=0说明添加不成功
		String sqlString="insert into t_profession(departmentsID,professionName,professionRemark) "+"values(?,?,?)";
    	try{
    		ps=connection.prepareStatement(sqlString);            
            ps.setInt(1, t.getDepartmentsID());            
            ps.setString(2, t.getProfessionName());            
            ps.setString(3, t.getProfessionRemark());         
    		n=ps.executeUpdate();
			if (n>0) {
				result.errorcode=0;
				result.errormsg="添加成功！";
			} else {
				result.errorcode=-1;
				result.errormsg="添加失败！";
			}
    		System.out.println("ProfessionDao-addResProfession:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
			result.errorcode=-1;
			result.errormsg=e.toString();
    		System.out.println("ProfessionDao-addResProfession 失败！");
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
    public  int addProfession(Profession t){
        int n=0;//n=0说明添加不成功
		String sqlString="insert into t_profession(departmentsID,professionName,professionRemark) "+"values(?,?,?)";
    	try{
    		ps=connection.prepareStatement(sqlString);            
            ps.setInt(1, t.getDepartmentsID());            
            ps.setString(2, t.getProfessionName());            
            ps.setString(3, t.getProfessionRemark());         
    		n=ps.executeUpdate();
    		System.out.println("ProfessionDao-addProfession:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
    		System.out.println("ProfessionDao-addProfession 失败！");
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
    public  BaseResponse updateResProfession(Profession t){
		BaseResponse result=new BaseResponse();
        int n=0;//n=0说明修改不成功
		String sqlString="update t_profession set departmentsID=?,professionName=?,professionRemark=?  where id=?";
    	try{
    		ps=connection.prepareStatement(sqlString);         
    		ps.setInt(4 , t.getId());         
    		ps.setInt(1 , t.getDepartmentsID());         
    		ps.setString(2 , t.getProfessionName());         
    		ps.setString(3 , t.getProfessionRemark());         
    		n=ps.executeUpdate();
			if (n>0) {
				result.errorcode=0;
				result.errormsg="修改成功！";
			} else {
				result.errorcode=-1;
				result.errormsg="修改失败！";
			}
    		System.out.println("ProfessionDao-updateResProfession:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
			result.errorcode=-1;
			result.errormsg=e.toString();
    		System.out.println("ProfessionDao-updateResProfession 失败！"+sqlString);
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
    public  int updateProfession(Profession t){
        int n=0;//n=0说明修改不成功
		String sqlString="update t_profession set departmentsID=?,professionName=?,professionRemark=?  where id=?";
    	try{
    		ps=connection.prepareStatement(sqlString);         
    		ps.setInt(4 , t.getId());         
    		ps.setInt(1 , t.getDepartmentsID());         
    		ps.setString(2 , t.getProfessionName());         
    		ps.setString(3 , t.getProfessionRemark());         
    		n=ps.executeUpdate();
    		System.out.println("ProfessionDao-updateProfession:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
    		System.out.println("ProfessionDao-updateProfession 失败！"+sqlString);
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
    public  Profession getProfession(String id){
    	Profession t=new Profession();
    	String sqlString="select * from t_profession where id=?";
    	try{
    		ps=connection.prepareStatement(sqlString);
    		ps.setInt(1, Integer.parseInt(id));
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){         
    				t.setId(rs.getInt(1));         
    				t.setDepartmentsID(rs.getInt(2));         
    				t.setProfessionName(rs.getString(3));         
    				t.setProfessionRemark(rs.getString(4)); 
    			}
    		}
    		System.out.println("ProfessionDao-getProfession:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		System.out.println(e.toString());
    		System.out.println("ProfessionDao-getProfession 失败！"+sqlString);
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