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
public class DepartmentsDao{
	private Connection connection = null; // 定义连接的对象
	private PreparedStatement ps = null; // 定义预准备的对象
	private ConnDAO jdbc = null; // 定义数据库连接对象

	public DepartmentsDao() {
		jdbc = new ConnDAO();
		connection = jdbc.getConnection(); // 利用构造方法取得数据库连接
	}
	
    //查询信息-泛型数据集-JSON
    public  ResDepartmentsList getResDepartmentsList(BaseRequest req){
    	ResDepartmentsList result=new ResDepartmentsList();
    	ArrayList<Departments> t1=new ArrayList<Departments>();
    	Departments t2;
    	String sqlString="SELECT * FROM t_departments  LIMIT ?,?";
    	try{
    		if (req.paging) {
        		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1,(req.page_no-1) * req.page_size);
        		ps.setInt(2, req.page_size);
			}else {
				sqlString="SELECT * FROM t_departments";
	    		ps=connection.prepareStatement(sqlString);
			}
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){
    				t2=new Departments();         
    				t2.setId(rs.getInt(1));         
    				t2.setDepartmentsName(rs.getString(2));         
    				t2.setDepartmentsRemark(rs.getString(3)); 
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
    		
			ResultSet rs1 = connection.createStatement().executeQuery("SELECT COUNT(*) FROM t_departments");
			if (rs1.next()) {
				result.total = rs1.getInt(1);
			}
    		System.out.println("DepartmentsDao-getDepartmentsList:"+sqlString);
    		rs.close();
    		rs1.close();
    	}catch(Exception e){
    		result.errorcode=-1;
    		result.errormsg=e.toString();
    		result.data=null;
    		System.out.println(e.toString());
    		System.out.println("DepartmentsDao-getDepartmentsList 失败！"+sqlString);
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
    public  ArrayList<Departments> getDepartmentsList(BaseRequest req){
    	ArrayList<Departments> result=new ArrayList<Departments>();
    	Departments t;
    	String sqlString="SELECT * FROM t_departments LIMIT ?,?";
    	try{
    		if (req.paging) {
        		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1,(req.page_no-1) * req.page_size);
        		ps.setInt(2, req.page_size);
			}else {
				sqlString="SELECT * FROM t_departments";
	    		ps=connection.prepareStatement(sqlString);
			}
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){        
    				t=new Departments();         
    				t.setId(rs.getInt(1));         
    				t.setDepartmentsName(rs.getString(2));         
    				t.setDepartmentsRemark(rs.getString(3)); 
    				System.out.println(t.toString());
    				result.add(t);
    			}
    		}
    		System.out.println("DepartmentsDao-getDepartmentsList:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		System.out.println(e.toString());
    		System.out.println("DepartmentsDao-getDepartmentsList 失败！"+sqlString);
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
    public  ArrayList<Departments> getDepartmentsListAll(String type){
    	ArrayList<Departments> result=new ArrayList<Departments>();
    	Departments t;
    	String sqlString="SELECT * FROM t_departments where id=?";
    	try{
    		if (!type.equals("")) {
        		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1, Integer.parseInt(type));
			}else {
				sqlString="SELECT * FROM t_departments";
	    		ps=connection.prepareStatement(sqlString);
			}
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){
    				t=new Departments();         
    				t.setId(rs.getInt(1));         
    				t.setDepartmentsName(rs.getString(2));         
    				t.setDepartmentsRemark(rs.getString(3)); 
    				System.out.println(t.toString());
    				result.add(t);
    			}
    		}
    		System.out.println("DepartmentsDao-getDepartmentsListAll:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		System.out.println(e.toString());
    		System.out.println("DepartmentsDao-getDepartmentsListAll 失败！"+sqlString);
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
    public  ResDepartments getResDepartments(String id){
    	ResDepartments result=new ResDepartments();
    	Departments t1=new Departments();
    	String sqlString="select * from t_departments where id=? ";
    	try{
    		ps=connection.prepareStatement(sqlString);
    		ps.setInt(1, Integer.parseInt(id));
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){         
    				t1.setId(rs.getInt(1));         
    				t1.setDepartmentsName(rs.getString(2));         
    				t1.setDepartmentsRemark(rs.getString(3)); 
    			}
        		result.errorcode=0;
        		result.errormsg="请求成功！";
        		result.data=t1;
    		}else {
        		result.errorcode=0;
        		result.errormsg="请求成功！";
        		result.data=null;
			}
    		System.out.println("DepartmentsDao-getDepartments:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		result.errorcode=-1;
			result.errormsg=e.toString();
    		result.data=null;
    		System.out.println(e.toString());
    		System.out.println("DepartmentsDao-getResDepartments 失败！"+sqlString);
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
	public BaseResponse deleteResDepartments(Departments t) {
		BaseResponse result=new BaseResponse();
		int n = 0;// n=0说明删除不成功
		String sqlString = "delete from t_departments where id=?";
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
    		System.out.println("DepartmentsDao-deleteResDepartments 失败！"+sqlString);
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
	public int deleteDepartments(String id) {
		int n = 0;// n=0说明删除不成功
		String sqlString = "delete from t_departments where id=?";
		try {
			ps = connection.prepareStatement(sqlString);
			ps.setString(1, id);
			n = ps.executeUpdate();
		} catch (Exception e) {
    		System.out.println("DepartmentsDao-deleteDepartments 失败！"+sqlString);
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
    public BaseResponse addResDepartments(Departments t){
		BaseResponse result=new BaseResponse();
        int n=0;//n=0说明添加不成功
		String sqlString="insert into t_departments(departmentsName,departmentsRemark) "+"values(?,?)";
    	try{
    		ps=connection.prepareStatement(sqlString);            
            ps.setString(1, t.getDepartmentsName());            
            ps.setString(2, t.getDepartmentsRemark());         
    		n=ps.executeUpdate();
			if (n>0) {
				result.errorcode=0;
				result.errormsg="添加成功！";
			} else {
				result.errorcode=-1;
				result.errormsg="添加失败！";
			}
    		System.out.println("DepartmentsDao-addResDepartments:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
			result.errorcode=-1;
			result.errormsg=e.toString();
    		System.out.println("DepartmentsDao-addResDepartments 失败！");
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
    public  int addDepartments(Departments t){
        int n=0;//n=0说明添加不成功
		String sqlString="insert into t_departments(departmentsName,departmentsRemark) "+"values(?,?)";
    	try{
    		ps=connection.prepareStatement(sqlString);            
            ps.setString(1, t.getDepartmentsName());            
            ps.setString(2, t.getDepartmentsRemark());         
    		n=ps.executeUpdate();
    		System.out.println("DepartmentsDao-addDepartments:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
    		System.out.println("DepartmentsDao-addDepartments 失败！");
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
    public  BaseResponse updateResDepartments(Departments t){
		BaseResponse result=new BaseResponse();
        int n=0;//n=0说明修改不成功
		String sqlString="update t_departments set departmentsName=?,departmentsRemark=?  where id=?";
    	try{
    		ps=connection.prepareStatement(sqlString);         
    		ps.setInt(3 , t.getId());         
    		ps.setString(1 , t.getDepartmentsName());         
    		ps.setString(2 , t.getDepartmentsRemark());         
    		n=ps.executeUpdate();
			if (n>0) {
				result.errorcode=0;
				result.errormsg="修改成功！";
			} else {
				result.errorcode=-1;
				result.errormsg="修改失败！";
			}
    		System.out.println("DepartmentsDao-updateResDepartments:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
			result.errorcode=-1;
			result.errormsg=e.toString();
    		System.out.println("DepartmentsDao-updateResDepartments 失败！"+sqlString);
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
    public  int updateDepartments(Departments t){
        int n=0;//n=0说明修改不成功
		String sqlString="update t_departments set departmentsName=?,departmentsRemark=?  where id=?";
    	try{
    		ps=connection.prepareStatement(sqlString);         
    		ps.setInt(3 , t.getId());         
    		ps.setString(1 , t.getDepartmentsName());         
    		ps.setString(2 , t.getDepartmentsRemark());         
    		n=ps.executeUpdate();
    		System.out.println("DepartmentsDao-updateDepartments:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
    		System.out.println("DepartmentsDao-updateDepartments 失败！"+sqlString);
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
    public  Departments getDepartments(String id){
    	Departments t=new Departments();
    	String sqlString="select * from t_departments where id=?";
    	try{
    		ps=connection.prepareStatement(sqlString);
    		ps.setInt(1, Integer.parseInt(id));
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){         
    				t.setId(rs.getInt(1));         
    				t.setDepartmentsName(rs.getString(2));         
    				t.setDepartmentsRemark(rs.getString(3)); 
    			}
    		}
    		System.out.println("DepartmentsDao-getDepartments:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		System.out.println(e.toString());
    		System.out.println("DepartmentsDao-getDepartments 失败！"+sqlString);
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
    
    //查询信息
    public  String getDepartmentsName(String id){
    	Departments t=new Departments();
    	String sqlString="select * from t_departments where id=?";
    	try{
    		ps=connection.prepareStatement(sqlString);
    		ps.setInt(1, Integer.parseInt(id));
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){         
    				t.setDepartmentsName(rs.getString(2));         
    			}
    		}
    		System.out.println("DepartmentsDao-getDepartments:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		System.out.println(e.toString());
    		System.out.println("DepartmentsDao-getDepartments 失败！"+sqlString);
    	}finally{
			try {
				ps.close();
				//connection.close();
			} catch (Exception e2) {
				System.out.println(e2.toString());
			}
		}
    	return t.getDepartmentsName();
    }
}