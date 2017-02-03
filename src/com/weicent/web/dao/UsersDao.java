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
public class UsersDao{
	private Connection connection = null; // 定义连接的对象
	private PreparedStatement ps = null; // 定义预准备的对象
	private ConnDAO jdbc = null; // 定义数据库连接对象

	public UsersDao() {
		jdbc = new ConnDAO();
		connection = jdbc.getConnection(); // 利用构造方法取得数据库连接
	}
	
    //查询信息-单条数据集-JSON
    public  ResUsers1 getResUsers1(Users t){
    	ResUsers1 result=new ResUsers1();
    	Users1 t1=new Users1();
    	String sqlString="select * from t_users where usersName=? and usersPwd=?";
    	try{
    		ps=connection.prepareStatement(sqlString);
    		ps.setString(1, t.getUsersName());
    		ps.setString(2, t.getUsersPwd());
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){         
    				t1.setId(rs.getInt(1));         
    				t1.setUsersName(rs.getString(2));         
    				t1.setUsersPwd(rs.getString(3));         
    				t1.setUsersAddTime(rs.getString(4));         
    				t1.setUsersNickName(rs.getString(5));         
    				t1.setUsersPhone(rs.getString(6));         
    				t1.setUsersQQ(rs.getString(7));         
    				t1.setDepartmentsID(new DepartmentsDao().getDepartmentsName(String.valueOf(rs.getInt(8))));
    				t1.setProfessionID(new ProfessionDao().getProfession(String.valueOf(rs.getInt(9))).getProfessionName());     
    				t1.setUsersGrade(rs.getInt(10)); 
    			}
        		result.errorcode=0;
        		result.errormsg="请求成功！";
        		result.data=t1;
    		}else {
        		result.errorcode=0;
        		result.errormsg="请求成功！";
        		result.data=null;
			}
    		System.out.println("UsersDao-getUsers:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		result.errorcode=-1;
			result.errormsg=e.toString();
    		result.data=null;
    		System.out.println(e.toString());
    		System.out.println("UsersDao-getResUsers 失败！"+sqlString);
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
    public  ResUsers getResUsers(Users t){
    	ResUsers result=new ResUsers();
    	Users t1=new Users();
    	String sqlString="select * from t_users where usersName=? and usersPwd=?";
    	try{
    		ps=connection.prepareStatement(sqlString);
    		ps.setString(1, t.getUsersName());
    		ps.setString(2, t.getUsersPwd());
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){         
    				t1.setId(rs.getInt(1));         
    				t1.setUsersName(rs.getString(2));         
    				t1.setUsersPwd(rs.getString(3));         
    				t1.setUsersAddTime(rs.getString(4));         
    				t1.setUsersNickName(rs.getString(5));         
    				t1.setUsersPhone(rs.getString(6));         
    				t1.setUsersQQ(rs.getString(7));         
    				t1.setDepartmentsID(rs.getInt(8));         
    				t1.setProfessionID(rs.getInt(9));         
    				t1.setUsersGrade(rs.getInt(10)); 
    			}
        		result.errorcode=0;
        		result.errormsg="请求成功！";
        		result.data=t1;
    		}else {
        		result.errorcode=0;
        		result.errormsg="请求成功！";
        		result.data=null;
			}
    		System.out.println("UsersDao-getUsers:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		result.errorcode=-1;
			result.errormsg=e.toString();
    		result.data=null;
    		System.out.println(e.toString());
    		System.out.println("UsersDao-getResUsers 失败！"+sqlString);
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
    public  ResUsersList getResUsersList(BaseRequest req){
    	ResUsersList result=new ResUsersList();
    	ArrayList<Users> t1=new ArrayList<Users>();
    	Users t2;
    	String sqlString="SELECT * FROM t_users  LIMIT ?,?";
    	try{
    		if (req.paging) {
        		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1,(req.page_no-1) * req.page_size);
        		ps.setInt(2, req.page_size);
			}else {
				sqlString="SELECT * FROM t_users";
	    		ps=connection.prepareStatement(sqlString);
			}
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){
    				t2=new Users();         
    				t2.setId(rs.getInt(1));         
    				t2.setUsersName(rs.getString(2));         
    				t2.setUsersPwd(rs.getString(3));         
    				t2.setUsersAddTime(rs.getString(4));         
    				t2.setUsersNickName(rs.getString(5));         
    				t2.setUsersPhone(rs.getString(6));         
    				t2.setUsersQQ(rs.getString(7));         
    				t2.setDepartmentsID(rs.getInt(8));         
    				t2.setProfessionID(rs.getInt(9));         
    				t2.setUsersGrade(rs.getInt(10)); 
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
    		
			ResultSet rs1 = connection.createStatement().executeQuery("SELECT COUNT(*) FROM t_users");
			if (rs1.next()) {
				result.total = rs1.getInt(1);
			}
    		System.out.println("UsersDao-getUsersList:"+sqlString);
    		rs.close();
    		rs1.close();
    	}catch(Exception e){
    		result.errorcode=-1;
    		result.errormsg=e.toString();
    		result.data=null;
    		System.out.println(e.toString());
    		System.out.println("UsersDao-getUsersList 失败！"+sqlString);
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
    public  ArrayList<Users> getUsersList(BaseRequest req){
    	ArrayList<Users> result=new ArrayList<Users>();
    	Users t;
    	String sqlString="SELECT * FROM t_users LIMIT ?,?";
    	try{
    		if (req.paging) {
        		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1,(req.page_no-1) * req.page_size);
        		ps.setInt(2, req.page_size);
			}else {
				sqlString="SELECT * FROM t_users";
	    		ps=connection.prepareStatement(sqlString);
			}
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){        
    				t=new Users();         
    				t.setId(rs.getInt(1));         
    				t.setUsersName(rs.getString(2));         
    				t.setUsersPwd(rs.getString(3));         
    				t.setUsersAddTime(rs.getString(4));         
    				t.setUsersNickName(rs.getString(5));         
    				t.setUsersPhone(rs.getString(6));         
    				t.setUsersQQ(rs.getString(7));         
    				t.setDepartmentsID(rs.getInt(8));         
    				t.setProfessionID(rs.getInt(9));         
    				t.setUsersGrade(rs.getInt(10)); 
    				System.out.println(t.toString());
    				result.add(t);
    			}
    		}
    		System.out.println("UsersDao-getUsersList:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		System.out.println(e.toString());
    		System.out.println("UsersDao-getUsersList 失败！"+sqlString);
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
    public  ArrayList<Users> getUsersListAll(String type){
    	ArrayList<Users> result=new ArrayList<Users>();
    	Users t;
    	String sqlString="SELECT * FROM t_users where id=?";
    	try{
    		if (!type.equals("")) {
        		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1, Integer.parseInt(type));
			}else {
				sqlString="SELECT * FROM t_users";
	    		ps=connection.prepareStatement(sqlString);
			}
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){
    				t=new Users();         
    				t.setId(rs.getInt(1));         
    				t.setUsersName(rs.getString(2));         
    				t.setUsersPwd(rs.getString(3));         
    				t.setUsersAddTime(rs.getString(4));         
    				t.setUsersNickName(rs.getString(5));         
    				t.setUsersPhone(rs.getString(6));         
    				t.setUsersQQ(rs.getString(7));         
    				t.setDepartmentsID(rs.getInt(8));         
    				t.setProfessionID(rs.getInt(9));         
    				t.setUsersGrade(rs.getInt(10)); 
    				System.out.println(t.toString());
    				result.add(t);
    			}
    		}
    		System.out.println("UsersDao-getUsersListAll:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		System.out.println(e.toString());
    		System.out.println("UsersDao-getUsersListAll 失败！"+sqlString);
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
    public  ResUsers getResUsers(String id){
    	ResUsers result=new ResUsers();
    	Users t1=new Users();
    	String sqlString="select * from t_users where id=? ";
    	try{
    		ps=connection.prepareStatement(sqlString);
    		ps.setInt(1, Integer.parseInt(id));
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){         
    				t1.setId(rs.getInt(1));         
    				t1.setUsersName(rs.getString(2));         
    				t1.setUsersPwd(rs.getString(3));         
    				t1.setUsersAddTime(rs.getString(4));         
    				t1.setUsersNickName(rs.getString(5));         
    				t1.setUsersPhone(rs.getString(6));         
    				t1.setUsersQQ(rs.getString(7));         
    				t1.setDepartmentsID(rs.getInt(8));         
    				t1.setProfessionID(rs.getInt(9));         
    				t1.setUsersGrade(rs.getInt(10)); 
    			}
        		result.errorcode=0;
        		result.errormsg="请求成功！";
        		result.data=t1;
    		}else {
        		result.errorcode=0;
        		result.errormsg="请求成功！";
        		result.data=null;
			}
    		System.out.println("UsersDao-getUsers:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		result.errorcode=-1;
			result.errormsg=e.toString();
    		result.data=null;
    		System.out.println(e.toString());
    		System.out.println("UsersDao-getResUsers 失败！"+sqlString);
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
	public BaseResponse deleteResUsers(Users t) {
		BaseResponse result=new BaseResponse();
		int n = 0;// n=0说明删除不成功
		String sqlString = "delete from t_users where id=?";
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
    		System.out.println("UsersDao-deleteResUsers 失败！"+sqlString);
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
	public int deleteUsers(String id) {
		int n = 0;// n=0说明删除不成功
		String sqlString = "delete from t_users where id=?";
		try {
			ps = connection.prepareStatement(sqlString);
			ps.setString(1, id);
			n = ps.executeUpdate();
		} catch (Exception e) {
    		System.out.println("UsersDao-deleteUsers 失败！"+sqlString);
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
    public BaseResponse addResUsers(Users t){
		BaseResponse result=new BaseResponse();
        int n=0;//n=0说明添加不成功
		String sqlString="insert into t_users(usersName,usersPwd,usersAddTime,usersNickName,usersPhone,usersQQ,departmentsID,professionID,usersGrade) "+"values(?,?,?,?,?,?,?,?,?)";
    	try{
    		ps=connection.prepareStatement(sqlString);            
            ps.setString(1, t.getUsersName());            
            ps.setString(2, t.getUsersPwd());            
//            ps.setString(3, t.getUsersAddTime());  
            ps.setString(3, AbDateUtil.getCurrentDateStringFordateFormatYMDHM2());
            ps.setString(4, t.getUsersNickName());            
            ps.setString(5, t.getUsersPhone());            
            ps.setString(6, t.getUsersQQ());            
            ps.setInt(7, t.getDepartmentsID());            
            ps.setInt(8, t.getProfessionID());            
            ps.setInt(9, t.getUsersGrade());         
    		n=ps.executeUpdate();
			if (n>0) {
				result.errorcode=0;
				result.errormsg="添加成功！";
			} else {
				result.errorcode=-1;
				result.errormsg="添加失败！";
			}
    		System.out.println("UsersDao-addResUsers:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
			result.errorcode=-1;
			result.errormsg=e.toString();
    		System.out.println("UsersDao-addResUsers 失败！");
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
    public  int addUsers(Users t){
        int n=0;//n=0说明添加不成功
		String sqlString="insert into t_users(usersName,usersPwd,usersAddTime,usersNickName,usersPhone,usersQQ,departmentsID,professionID,usersGrade) "+"values(?,?,?,?,?,?,?,?,?)";
    	try{
    		ps=connection.prepareStatement(sqlString);            
            ps.setString(1, t.getUsersName());            
            ps.setString(2, t.getUsersPwd());            
            ps.setString(3, t.getUsersAddTime());            
            ps.setString(4, t.getUsersNickName());            
            ps.setString(5, t.getUsersPhone());            
            ps.setString(6, t.getUsersQQ());            
            ps.setInt(7, t.getDepartmentsID());            
            ps.setInt(8, t.getProfessionID());            
            ps.setInt(9, t.getUsersGrade());         
    		n=ps.executeUpdate();
    		System.out.println("UsersDao-addUsers:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
    		System.out.println("UsersDao-addUsers 失败！");
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
    public  BaseResponse updateResUsers(Users t){
		BaseResponse result=new BaseResponse();
        int n=0;//n=0说明修改不成功
		String sqlString="update t_users set usersName=?,usersPwd=?,usersAddTime=?,usersNickName=?,usersPhone=?,usersQQ=?,departmentsID=?,professionID=?,usersGrade=?  where id=?";
    	try{
    		ps=connection.prepareStatement(sqlString);         
    		ps.setInt(10 , t.getId());         
    		ps.setString(1 , t.getUsersName());         
    		ps.setString(2 , t.getUsersPwd());         
    		ps.setString(3 , t.getUsersAddTime());         
    		ps.setString(4 , t.getUsersNickName());         
    		ps.setString(5 , t.getUsersPhone());         
    		ps.setString(6 , t.getUsersQQ());         
    		ps.setInt(7 , t.getDepartmentsID());         
    		ps.setInt(8 , t.getProfessionID());         
    		ps.setInt(9 , t.getUsersGrade());         
    		n=ps.executeUpdate();
			if (n>0) {
				result.errorcode=0;
				result.errormsg="修改成功！";
			} else {
				result.errorcode=-1;
				result.errormsg="修改失败！";
			}
    		System.out.println("UsersDao-updateResUsers:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
			result.errorcode=-1;
			result.errormsg=e.toString();
    		System.out.println("UsersDao-updateResUsers 失败！"+sqlString);
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
    public  int updateUsers(Users t){
        int n=0;//n=0说明修改不成功
		String sqlString="update t_users set usersName=?,usersPwd=?,usersAddTime=?,usersNickName=?,usersPhone=?,usersQQ=?,departmentsID=?,professionID=?,usersGrade=?  where id=?";
    	try{
    		ps=connection.prepareStatement(sqlString);         
    		ps.setInt(10 , t.getId());         
    		ps.setString(1 , t.getUsersName());         
    		ps.setString(2 , t.getUsersPwd());         
    		ps.setString(3 , t.getUsersAddTime());         
    		ps.setString(4 , t.getUsersNickName());         
    		ps.setString(5 , t.getUsersPhone());         
    		ps.setString(6 , t.getUsersQQ());         
    		ps.setInt(7 , t.getDepartmentsID());         
    		ps.setInt(8 , t.getProfessionID());         
    		ps.setInt(9 , t.getUsersGrade());         
    		n=ps.executeUpdate();
    		System.out.println("UsersDao-updateUsers:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
    		System.out.println("UsersDao-updateUsers 失败！"+sqlString);
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
    public  Users getUsers(String id){
    	Users t=new Users();
    	String sqlString="select * from t_users where id=?";
    	try{
    		ps=connection.prepareStatement(sqlString);
    		ps.setInt(1, Integer.parseInt(id));
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){         
    				t.setId(rs.getInt(1));         
    				t.setUsersName(rs.getString(2));         
    				t.setUsersPwd(rs.getString(3));         
    				t.setUsersAddTime(rs.getString(4));         
    				t.setUsersNickName(rs.getString(5));         
    				t.setUsersPhone(rs.getString(6));         
    				t.setUsersQQ(rs.getString(7));         
    				t.setDepartmentsID(rs.getInt(8));         
    				t.setProfessionID(rs.getInt(9));         
    				t.setUsersGrade(rs.getInt(10)); 
    			}
    		}
    		System.out.println("UsersDao-getUsers:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		System.out.println(e.toString());
    		System.out.println("UsersDao-getUsers 失败！"+sqlString);
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