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
public class BuysDao{
	private Connection connection = null; // 定义连接的对象
	private PreparedStatement ps = null; // 定义预准备的对象
	private ConnDAO jdbc = null; // 定义数据库连接对象

	public BuysDao() {
		jdbc = new ConnDAO();
		connection = jdbc.getConnection(); // 利用构造方法取得数据库连接
	}
	
    //查询信息-泛型数据集-JSON
    public  ResBuysList1 getResBuysList2(BaseRequest req){
    	ResBuysList1 result=new ResBuysList1();
    	ArrayList<Buys1> t1=new ArrayList<Buys1>();
    	Buys1 t2;
    	String sqlString="SELECT * FROM t_buys WHERE buysStatus=0 ORDER BY buysAddTime DESC LIMIT ?,?";
    	try{
    		System.out.println("BuysDao-getBuysList:"+req.paging);
    		if (req.paging) {
        		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1,(req.page_no-1) * req.page_size);
        		ps.setInt(2, req.page_size);
			}else {
				sqlString="SELECT * FROM t_buys WHERE buysStatus=0 ORDER BY buysAddTime DESC";
	    		ps=connection.prepareStatement(sqlString);
			}
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){
    				t2=new Buys1();         
    				t2.setId(rs.getInt(1));   
    				UsersDao usersDao=new UsersDao();
    				Users users=usersDao.getUsers(String.valueOf(rs.getInt(2)));
    				t2.setUsersDetail(users.getUsersName());         
    				t2.setBuysName(rs.getString(3));         
    				t2.setBuysPrice(rs.getString(4));         
    				t2.setBuysAddress(rs.getString(5));         
    				t2.setBuysDetail(rs.getString(6));         
    				t2.setBuysPhone(rs.getString(7));         
    				t2.setBuysAddTime(rs.getString(8));         
    				t2.setBuysStatus(rs.getInt(9));         
    				t2.setBuysQQ(rs.getInt(10)); 
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
    		
			ResultSet rs1 = connection.createStatement().executeQuery("SELECT COUNT(*) FROM t_buys WHERE buysStatus=0");
			if (rs1.next()) {
				result.total = rs1.getInt(1);
			}
			
    		System.out.println("BuysDao-getBuysList:"+sqlString);
    		rs.close();
    		rs1.close();
    	}catch(Exception e){
    		result.errorcode=-1;
    		result.errormsg=e.toString();
    		result.data=null;
    		System.out.println(e.toString());
    		System.out.println("BuysDao-getBuysList 失败！"+sqlString);
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
    public  ResBuysList1 getResBuysList1(BaseRequest req){
    	ResBuysList1 result=new ResBuysList1();
    	ArrayList<Buys1> t1=new ArrayList<Buys1>();
    	PreparedStatement ps1;
    	Buys1 t2;
    	String sqlString="SELECT * FROM t_buys WHERE usersID=? and buysStatus=0 ORDER BY buysAddTime DESC LIMIT ?,?";
    	try{
    		if (req.paging) {
        		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1, req.id);
        		ps.setInt(2,(req.page_no-1) * req.page_size);
        		ps.setInt(3, req.page_size);
			}else {
				sqlString="SELECT * FROM t_buys WHERE usersID=? and buysStatus=0 ORDER BY buysAddTime";
	    		ps=connection.prepareStatement(sqlString);
	    		ps.setInt(1, req.id);
			}
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){
    				t2=new Buys1();         
    				t2.setId(rs.getInt(1));   
    				UsersDao usersDao=new UsersDao();
    				Users users=usersDao.getUsers(String.valueOf(rs.getInt(2)));
    				t2.setUsersDetail(users.getUsersName());         
    				t2.setBuysName(rs.getString(3));         
    				t2.setBuysPrice(rs.getString(4));         
    				t2.setBuysAddress(rs.getString(5));         
    				t2.setBuysDetail(rs.getString(6));         
    				t2.setBuysPhone(rs.getString(7));         
    				t2.setBuysAddTime(rs.getString(8));         
    				t2.setBuysStatus(rs.getInt(9));         
    				t2.setBuysQQ(rs.getInt(10)); 
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
    		
    		ps1=connection.prepareStatement("SELECT COUNT(*) FROM t_buys WHERE usersID=?"+" and buysStatus=0");
    		ps1.setInt(1, req.id);
//			ResultSet rs1 = connection.createStatement().executeQuery("SELECT COUNT(*) FROM t_buys WHERE usersID="+req.id);
			ResultSet rs1 = ps1.executeQuery();
			if (rs1.next()) {
				result.total = rs1.getInt(1);
			}
			
    		System.out.println("BuysDao-getBuysList:"+sqlString);
    		rs.close();
    		rs1.close();
    	}catch(Exception e){
    		result.errorcode=-1;
    		result.errormsg=e.toString();
    		result.data=null;
    		System.out.println(e.toString());
    		System.out.println("BuysDao-getBuysList 失败！"+sqlString);
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
    public  ResBuysList getResBuysList(BaseRequest req){
    	ResBuysList result=new ResBuysList();
    	ArrayList<Buys> t1=new ArrayList<Buys>();
    	Buys t2;
    	String sqlString="SELECT * FROM t_buys LIMIT ?,?";
    	try{
    		if (req.paging) {
        		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1,(req.page_no-1) * req.page_size);
        		ps.setInt(2, req.page_size);
			}else {
				sqlString="SELECT * FROM t_buys";
	    		ps=connection.prepareStatement(sqlString);
			}
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){
    				t2=new Buys();         
    				t2.setId(rs.getInt(1));         
    				t2.setUsersID(rs.getInt(2));         
    				t2.setBuysName(rs.getString(3));         
    				t2.setBuysPrice(rs.getString(4));         
    				t2.setBuysAddress(rs.getString(5));         
    				t2.setBuysDetail(rs.getString(6));         
    				t2.setBuysPhone(rs.getString(7));         
    				t2.setBuysAddTime(rs.getString(8));         
    				t2.setBuysStatus(rs.getInt(9));         
    				t2.setBuysQQ(rs.getInt(10)); 
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
    		
			ResultSet rs1 = connection.createStatement().executeQuery("SELECT COUNT(*) FROM t_buys");
			if (rs1.next()) {
				result.total = rs1.getInt(1);
			}
    		System.out.println("BuysDao-getBuysList:"+sqlString);
    		rs.close();
    		rs1.close();
    	}catch(Exception e){
    		result.errorcode=-1;
    		result.errormsg=e.toString();
    		result.data=null;
    		System.out.println(e.toString());
    		System.out.println("BuysDao-getBuysList 失败！"+sqlString);
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
    public  ArrayList<Buys> getBuysList(BaseRequest req){
    	ArrayList<Buys> result=new ArrayList<Buys>();
    	Buys t;
    	String sqlString="SELECT * FROM t_buys LIMIT ?,?";
    	try{
    		if (req.paging) {
        		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1,(req.page_no-1) * req.page_size);
        		ps.setInt(2, req.page_size);
			}else {
				sqlString="SELECT * FROM t_buys";
	    		ps=connection.prepareStatement(sqlString);
			}
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){        
    				t=new Buys();         
    				t.setId(rs.getInt(1));         
    				t.setUsersID(rs.getInt(2));         
    				t.setBuysName(rs.getString(3));         
    				t.setBuysPrice(rs.getString(4));         
    				t.setBuysAddress(rs.getString(5));         
    				t.setBuysDetail(rs.getString(6));         
    				t.setBuysPhone(rs.getString(7));         
    				t.setBuysAddTime(rs.getString(8));         
    				t.setBuysStatus(rs.getInt(9));         
    				t.setBuysQQ(rs.getInt(10)); 
    				System.out.println(t.toString());
    				result.add(t);
    			}
    		}
    		System.out.println("BuysDao-getBuysList:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		System.out.println(e.toString());
    		System.out.println("BuysDao-getBuysList 失败！"+sqlString);
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
    public  ArrayList<Buys> getBuysListAll(String type){
    	ArrayList<Buys> result=new ArrayList<Buys>();
    	Buys t;
    	String sqlString="SELECT * FROM t_buys where id=?";
    	try{
    		if (!type.equals("")) {
        		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1, Integer.parseInt(type));
			}else {
				sqlString="SELECT * FROM t_buys";
	    		ps=connection.prepareStatement(sqlString);
			}
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){
    				t=new Buys();         
    				t.setId(rs.getInt(1));         
    				t.setUsersID(rs.getInt(2));         
    				t.setBuysName(rs.getString(3));         
    				t.setBuysPrice(rs.getString(4));         
    				t.setBuysAddress(rs.getString(5));         
    				t.setBuysDetail(rs.getString(6));         
    				t.setBuysPhone(rs.getString(7));         
    				t.setBuysAddTime(rs.getString(8));         
    				t.setBuysStatus(rs.getInt(9));         
    				t.setBuysQQ(rs.getInt(10)); 
    				System.out.println(t.toString());
    				result.add(t);
    			}
    		}
    		System.out.println("BuysDao-getBuysListAll:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		System.out.println(e.toString());
    		System.out.println("BuysDao-getBuysListAll 失败！"+sqlString);
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
    public  ResBuys getResBuys(String id){
    	ResBuys result=new ResBuys();
    	Buys t1=new Buys();
    	String sqlString="select * from t_buys where id=? ";
    	try{
    		ps=connection.prepareStatement(sqlString);
    		ps.setInt(1, Integer.parseInt(id));
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){         
    				t1.setId(rs.getInt(1));         
    				t1.setUsersID(rs.getInt(2));         
    				t1.setBuysName(rs.getString(3));         
    				t1.setBuysPrice(rs.getString(4));         
    				t1.setBuysAddress(rs.getString(5));         
    				t1.setBuysDetail(rs.getString(6));         
    				t1.setBuysPhone(rs.getString(7));         
    				t1.setBuysAddTime(rs.getString(8));         
    				t1.setBuysStatus(rs.getInt(9));         
    				t1.setBuysQQ(rs.getInt(10)); 
    			}
        		result.errorcode=0;
        		result.errormsg="请求成功！";
        		result.data=t1;
    		}else {
        		result.errorcode=0;
        		result.errormsg="请求成功！";
        		result.data=null;
			}
    		System.out.println("BuysDao-getBuys:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		result.errorcode=-1;
			result.errormsg=e.toString();
    		result.data=null;
    		System.out.println(e.toString());
    		System.out.println("BuysDao-getResBuys 失败！"+sqlString);
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
	public BaseResponse deleteResBuys(Buys t) {
		BaseResponse result=new BaseResponse();
		int n = 0;// n=0说明删除不成功
		String sqlString = "delete from t_buys where id=?";
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
    		System.out.println("BuysDao-deleteResBuys 失败！"+sqlString);
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
	public int deleteBuys(String id) {
		int n = 0;// n=0说明删除不成功
		String sqlString = "delete from t_buys where id=?";
		try {
			ps = connection.prepareStatement(sqlString);
			ps.setString(1, id);
			n = ps.executeUpdate();
		} catch (Exception e) {
    		System.out.println("BuysDao-deleteBuys 失败！"+sqlString);
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
    public BaseResponse addResBuys(Buys t){
		BaseResponse result=new BaseResponse();
        int n=0;//n=0说明添加不成功
		String sqlString="insert into t_buys(usersID,buysName,buysPrice,buysAddress,buysDetail,buysPhone,buysAddTime,buysStatus,buysQQ) "+"values(?,?,?,?,?,?,?,?,?)";
    	try{
    		ps=connection.prepareStatement(sqlString);            
            ps.setInt(1, t.getUsersID());            
            ps.setString(2, t.getBuysName());            
            ps.setString(3, t.getBuysPrice());            
            ps.setString(4, t.getBuysAddress());            
            ps.setString(5, t.getBuysDetail());            
            ps.setString(6, t.getBuysPhone());            
            ps.setString(7, AbDateUtil.getCurrentDateStringFordateFormatYMDHM2());            
            ps.setInt(8, 0);            
            ps.setInt(9, t.getBuysQQ());         
    		n=ps.executeUpdate();
			if (n>0) {
				result.errorcode=0;
				result.errormsg="添加成功！";
			} else {
				result.errorcode=-1;
				result.errormsg="添加失败！";
			}
    		System.out.println("BuysDao-addResBuys:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
			result.errorcode=-1;
			result.errormsg=e.toString();
    		System.out.println("BuysDao-addResBuys 失败！");
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
    public  int addBuys(Buys t){
        int n=0;//n=0说明添加不成功
		String sqlString="insert into t_buys(usersID,buysName,buysPrice,buysAddress,buysDetail,buysPhone,buysAddTime,buysStatus,buysQQ) "+"values(?,?,?,?,?,?,?,?,?)";
    	try{
    		ps=connection.prepareStatement(sqlString);            
            ps.setInt(1, t.getUsersID());            
            ps.setString(2, t.getBuysName());            
            ps.setString(3, t.getBuysPrice());            
            ps.setString(4, t.getBuysAddress());            
            ps.setString(5, t.getBuysDetail());            
            ps.setString(6, t.getBuysPhone());            
            ps.setString(7, t.getBuysAddTime());            
            ps.setInt(8, t.getBuysStatus());            
            ps.setInt(9, t.getBuysQQ());         
    		n=ps.executeUpdate();
    		System.out.println("BuysDao-addBuys:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
    		System.out.println("BuysDao-addBuys 失败！");
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
    public  BaseResponse updateResBuys(Buys t){
		BaseResponse result=new BaseResponse();
        int n=0;//n=0说明修改不成功
		String sqlString="update t_buys set usersID=?,buysName=?,buysPrice=?,buysAddress=?,buysDetail=?,buysPhone=?,buysAddTime=?,buysStatus=?,buysQQ=?  where id=?";
    	try{
    		ps=connection.prepareStatement(sqlString);         
    		ps.setInt(10 , t.getId());         
    		ps.setInt(1 , t.getUsersID());         
    		ps.setString(2 , t.getBuysName());         
    		ps.setString(3 , t.getBuysPrice());         
    		ps.setString(4 , t.getBuysAddress());         
    		ps.setString(5 , t.getBuysDetail());         
    		ps.setString(6 , t.getBuysPhone());         
    		ps.setString(7 , t.getBuysAddTime());         
    		ps.setInt(8 , t.getBuysStatus());         
    		ps.setInt(9 , t.getBuysQQ());         
    		n=ps.executeUpdate();
			if (n>0) {
				result.errorcode=0;
				result.errormsg="修改成功！";
			} else {
				result.errorcode=-1;
				result.errormsg="修改失败！";
			}
    		System.out.println("BuysDao-updateResBuys:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
			result.errorcode=-1;
			result.errormsg=e.toString();
    		System.out.println("BuysDao-updateResBuys 失败！"+sqlString);
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
    public  int updateBuys(Buys t){
        int n=0;//n=0说明修改不成功
		String sqlString="update t_buys set usersID=?,buysName=?,buysPrice=?,buysAddress=?,buysDetail=?,buysPhone=?,buysAddTime=?,buysStatus=?,buysQQ=?  where id=?";
    	try{
    		ps=connection.prepareStatement(sqlString);         
    		ps.setInt(10 , t.getId());         
    		ps.setInt(1 , t.getUsersID());         
    		ps.setString(2 , t.getBuysName());         
    		ps.setString(3 , t.getBuysPrice());         
    		ps.setString(4 , t.getBuysAddress());         
    		ps.setString(5 , t.getBuysDetail());         
    		ps.setString(6 , t.getBuysPhone());         
    		ps.setString(7 , t.getBuysAddTime());         
    		ps.setInt(8 , t.getBuysStatus());         
    		ps.setInt(9 , t.getBuysQQ());         
    		n=ps.executeUpdate();
    		System.out.println("BuysDao-updateBuys:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
    		System.out.println("BuysDao-updateBuys 失败！"+sqlString);
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
    public  Buys getBuys(String id){
    	Buys t=new Buys();
    	String sqlString="select * from t_buys where id=?";
    	try{
    		ps=connection.prepareStatement(sqlString);
    		ps.setInt(1, Integer.parseInt(id));
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){         
    				t.setId(rs.getInt(1));         
    				t.setUsersID(rs.getInt(2));         
    				t.setBuysName(rs.getString(3));         
    				t.setBuysPrice(rs.getString(4));         
    				t.setBuysAddress(rs.getString(5));         
    				t.setBuysDetail(rs.getString(6));         
    				t.setBuysPhone(rs.getString(7));         
    				t.setBuysAddTime(rs.getString(8));         
    				t.setBuysStatus(rs.getInt(9));         
    				t.setBuysQQ(rs.getInt(10)); 
    			}
    		}
    		System.out.println("BuysDao-getBuys:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		System.out.println(e.toString());
    		System.out.println("BuysDao-getBuys 失败！"+sqlString);
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