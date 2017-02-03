package com.weicent.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.weicent.web.domain.*;
import com.weicent.web.model.*;
import com.weicent.web.response.*;
import com.weicent.web.request.*;
import com.weicent.web.request.model.ReqCommodity;
import com.weicent.web.response.list.*;
import com.weicent.web.response.model.*;
import com.weicent.web.tool.*;

/**
 * 
 */
public class CommodityDao{
	private Connection connection = null; // 定义连接的对象
	private PreparedStatement ps = null; // 定义预准备的对象
	private ConnDAO jdbc = null; // 定义数据库连接对象

	public CommodityDao() {
		jdbc = new ConnDAO();
		connection = jdbc.getConnection(); // 利用构造方法取得数据库连接
	}
	
    //查询信息-泛型数据集-JSON
    public ResCommodityList1 getResCommodityList3(ReqCommodity req){
    	ResCommodityList1 result=new ResCommodityList1();
    	ArrayList<Commodity1> t1=new ArrayList<Commodity1>();
    	Commodity1 t2;
    	String sqlString="SELECT * FROM t_commodity WHERE usersID=? and commodityStatus=0 ORDER BY commodityAddTime DESC  LIMIT ?,?";
    	try{
    		if (req.paging) {
        		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1, req.data.getUsersID());
        		ps.setInt(2,(req.page_no-1) * req.page_size);
        		ps.setInt(3, req.page_size);
			}else {
				sqlString="SELECT * FROM t_commodity WHERE usersID=? and commodityStatus=0";
	    		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1, req.data.getUsersID());
			}
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){
    				t2=new Commodity1();         
    				t2.setId(rs.getInt(1));         
    				t2.setUsersID(rs.getInt(2));         
    				t2.setCommodityImageUrl(rs.getString(3));   
    				UsersDao usersDao=new UsersDao();
    				Users users=usersDao.getUsers(String.valueOf(rs.getInt(2)));
    				ProfessionDao professionDao=new ProfessionDao();
    				Profession profession=professionDao.getProfession(String.valueOf(users.getProfessionID()));
     				t2.setUsersDetail(profession.getProfessionName()+users.getUsersGrade()+"级");
    				t2.setCommodityName(rs.getString(4));         
    				t2.setCommodityDetail(rs.getString(5));         
    				t2.setCommodityAddress(rs.getString(6));         
    				t2.setCommodityPrice(rs.getString(7));         
    				t2.setCommodityBargain(rs.getInt(9));         
    				t2.setCommodityPhone(rs.getInt(10));         
    				t2.setCommodityQQ(rs.getInt(11));         
    				t2.setCommodityAddTime(rs.getString(12));         
    				t2.setCommodityViews(rs.getInt(14)); 
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
    		
			ResultSet rs1 = connection.createStatement().executeQuery("SELECT COUNT(*) FROM t_commodity WHERE usersID="+req.data.getUsersID()+" and commodityStatus=0");
			if (rs1.next()) {
				result.total = rs1.getInt(1);
			}
    		System.out.println("CommodityDao-getCommodityList:"+sqlString);
    		rs.close();
    		rs1.close();
    	}catch(Exception e){
    		result.errorcode=-1;
    		result.errormsg=e.toString();
    		result.data=null;
    		System.out.println(e.toString());
    		System.out.println("CommodityDao-getCommodityList 失败！"+sqlString);
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
    public  ResCommodityList1 getResCommodityList2(ReqCommodity req){
    	ResCommodityList1 result=new ResCommodityList1();
    	ArrayList<Commodity1> t1=new ArrayList<Commodity1>();
    	Commodity1 t2;
    	String sqlString="SELECT * FROM t_commodity WHERE categoryID=? and commodityStatus=0 ORDER BY commodityAddTime DESC  LIMIT ?,?";
    	try{
    		if (req.paging) {
        		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1, req.data.getCategoryID());
        		ps.setInt(2,(req.page_no-1) * req.page_size);
        		ps.setInt(3, req.page_size);
			}else {
				sqlString="SELECT * FROM t_commodity WHERE categoryID=? and commodityStatus=0";
	    		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1, req.data.getCategoryID());
			}
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){
    				t2=new Commodity1();         
    				t2.setId(rs.getInt(1));         
    				t2.setUsersID(rs.getInt(2));         
    				t2.setCommodityImageUrl(rs.getString(3));   
    				UsersDao usersDao=new UsersDao();
    				Users users=usersDao.getUsers(String.valueOf(rs.getInt(2)));
    				ProfessionDao professionDao=new ProfessionDao();
    				Profession profession=professionDao.getProfession(String.valueOf(users.getProfessionID()));
     				t2.setUsersDetail(profession.getProfessionName()+users.getUsersGrade()+"级");
    				t2.setCommodityName(rs.getString(4));         
    				t2.setCommodityDetail(rs.getString(5));         
    				t2.setCommodityAddress(rs.getString(6));         
    				t2.setCommodityPrice(rs.getString(7));         
    				t2.setCommodityBargain(rs.getInt(9));         
    				t2.setCommodityPhone(rs.getInt(10));         
    				t2.setCommodityQQ(rs.getInt(11));         
    				t2.setCommodityAddTime(rs.getString(12));         
    				t2.setCommodityViews(rs.getInt(14)); 
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
    		
			ResultSet rs1 = connection.createStatement().executeQuery("SELECT COUNT(*) FROM t_commodity WHERE categoryID="+req.data.getCategoryID()+" and commodityStatus=0");
			if (rs1.next()) {
				result.total = rs1.getInt(1);
			}
    		System.out.println("CommodityDao-getCommodityList:"+sqlString);
    		rs.close();
    		rs1.close();
    	}catch(Exception e){
    		result.errorcode=-1;
    		result.errormsg=e.toString();
    		result.data=null;
    		System.out.println(e.toString());
    		System.out.println("CommodityDao-getCommodityList 失败！"+sqlString);
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
    public  ResCommodityList1 getResCommodityList1(BaseRequest req){
    	ResCommodityList1 result=new ResCommodityList1();
    	ArrayList<Commodity1> t1=new ArrayList<Commodity1>();
    	Commodity1 t2;
    	String sqlString="SELECT * FROM t_commodity WHERE commodityStatus=0 ORDER BY commodityAddTime DESC  LIMIT ?,?";
    	try{
    		if (req.paging) {
        		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1,(req.page_no-1) * req.page_size);
        		ps.setInt(2, req.page_size);
			}else {
				sqlString="SELECT * FROM t_commodity WHERE commodityStatus=0";
	    		ps=connection.prepareStatement(sqlString);
			}
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){
    				t2=new Commodity1();         
    				t2.setId(rs.getInt(1));         
    				t2.setUsersID(rs.getInt(2));         
    				t2.setCommodityImageUrl(rs.getString(3));   
    				UsersDao usersDao=new UsersDao();
    				Users users=usersDao.getUsers(String.valueOf(rs.getInt(2)));
    				ProfessionDao professionDao=new ProfessionDao();
    				Profession profession=professionDao.getProfession(String.valueOf(users.getProfessionID()));
     				t2.setUsersDetail(users.getUsersName()+"("+users.getUsersNickName()+")");
     				t2.setUsersProfessionNameGrade(profession.getProfessionName()+users.getUsersGrade()+"级");
    				t2.setCommodityName(rs.getString(4));         
    				t2.setCommodityDetail(rs.getString(5));         
    				t2.setCommodityAddress(rs.getString(6));         
    				t2.setCommodityPrice(rs.getString(7));         
    				t2.setCommodityBargain(rs.getInt(9));         
    				t2.setCommodityPhone(rs.getInt(10));         
    				t2.setCommodityQQ(rs.getInt(11));         
    				t2.setCommodityAddTime(rs.getString(12));         
    				t2.setCommodityViews(rs.getInt(14)); 
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
    		
			ResultSet rs1 = connection.createStatement().executeQuery("SELECT COUNT(*) FROM t_commodity WHERE commodityStatus=0");
			if (rs1.next()) {
				result.total = rs1.getInt(1);
			}
    		System.out.println("CommodityDao-getCommodityList:"+sqlString);
    		rs.close();
    		rs1.close();
    	}catch(Exception e){
    		result.errorcode=-1;
    		result.errormsg=e.toString();
    		result.data=null;
    		System.out.println(e.toString());
    		System.out.println("CommodityDao-getCommodityList 失败！"+sqlString);
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
    public  ResCommodityList getResCommodityList(BaseRequest req){
    	ResCommodityList result=new ResCommodityList();
    	ArrayList<Commodity> t1=new ArrayList<Commodity>();
    	Commodity t2;
    	String sqlString="SELECT * FROM t_commodity ORDER BY commodityAddTime DESC  LIMIT ?,?";
    	try{
    		if (req.paging) {
        		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1,(req.page_no-1) * req.page_size);
        		ps.setInt(2, req.page_size);
			}else {
				sqlString="SELECT * FROM t_commodity";
	    		ps=connection.prepareStatement(sqlString);
			}
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){
    				t2=new Commodity();         
    				t2.setId(rs.getInt(1));         
    				t2.setUsersID(rs.getInt(2));         
    				t2.setCommodityImageUrl(rs.getString(3));         
    				t2.setCommodityName(rs.getString(4));         
    				t2.setCommodityDetail(rs.getString(5));         
    				t2.setCommodityAddress(rs.getString(6));         
    				t2.setCommodityPrice(rs.getString(7));         
    				t2.setCategoryID(rs.getInt(8));         
    				t2.setCommodityBargain(rs.getInt(9));         
    				t2.setCommodityPhone(rs.getInt(10));         
    				t2.setCommodityQQ(rs.getInt(11));         
    				t2.setCommodityAddTime(rs.getString(12));         
    				t2.setCommodityStatus(rs.getInt(13));         
    				t2.setCommodityViews(rs.getInt(14)); 
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
    		
			ResultSet rs1 = connection.createStatement().executeQuery("SELECT COUNT(*) FROM t_commodity");
			if (rs1.next()) {
				result.total = rs1.getInt(1);
			}
    		System.out.println("CommodityDao-getCommodityList:"+sqlString);
    		rs.close();
    		rs1.close();
    	}catch(Exception e){
    		result.errorcode=-1;
    		result.errormsg=e.toString();
    		result.data=null;
    		System.out.println(e.toString());
    		System.out.println("CommodityDao-getCommodityList 失败！"+sqlString);
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
    public  ArrayList<Commodity> getCommodityList(BaseRequest req){
    	ArrayList<Commodity> result=new ArrayList<Commodity>();
    	Commodity t;
    	String sqlString="SELECT * FROM t_commodity LIMIT ?,?";
    	try{
    		if (req.paging) {
        		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1,(req.page_no-1) * req.page_size);
        		ps.setInt(2, req.page_size);
			}else {
				sqlString="SELECT * FROM t_commodity";
	    		ps=connection.prepareStatement(sqlString);
			}
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){        
    				t=new Commodity();         
    				t.setId(rs.getInt(1));         
    				t.setUsersID(rs.getInt(2));         
    				t.setCommodityImageUrl(rs.getString(3));         
    				t.setCommodityName(rs.getString(4));         
    				t.setCommodityDetail(rs.getString(5));         
    				t.setCommodityAddress(rs.getString(6));         
    				t.setCommodityPrice(rs.getString(7));         
    				t.setCategoryID(rs.getInt(8));         
    				t.setCommodityBargain(rs.getInt(9));         
    				t.setCommodityPhone(rs.getInt(10));         
    				t.setCommodityQQ(rs.getInt(11));         
    				t.setCommodityAddTime(rs.getString(12));         
    				t.setCommodityStatus(rs.getInt(13));         
    				t.setCommodityViews(rs.getInt(14)); 
    				System.out.println(t.toString());
    				result.add(t);
    			}
    		}
    		System.out.println("CommodityDao-getCommodityList:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		System.out.println(e.toString());
    		System.out.println("CommodityDao-getCommodityList 失败！"+sqlString);
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
    public  ArrayList<Commodity> getCommodityListAll(String type){
    	ArrayList<Commodity> result=new ArrayList<Commodity>();
    	Commodity t;
    	String sqlString="SELECT * FROM t_commodity where id=?";
    	try{
    		if (!type.equals("")) {
        		ps=connection.prepareStatement(sqlString);
        		ps.setInt(1, Integer.parseInt(type));
			}else {
				sqlString="SELECT * FROM t_commodity";
	    		ps=connection.prepareStatement(sqlString);
			}
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){
    				t=new Commodity();         
    				t.setId(rs.getInt(1));         
    				t.setUsersID(rs.getInt(2));         
    				t.setCommodityImageUrl(rs.getString(3));         
    				t.setCommodityName(rs.getString(4));         
    				t.setCommodityDetail(rs.getString(5));         
    				t.setCommodityAddress(rs.getString(6));         
    				t.setCommodityPrice(rs.getString(7));         
    				t.setCategoryID(rs.getInt(8));         
    				t.setCommodityBargain(rs.getInt(9));         
    				t.setCommodityPhone(rs.getInt(10));         
    				t.setCommodityQQ(rs.getInt(11));         
    				t.setCommodityAddTime(rs.getString(12));         
    				t.setCommodityStatus(rs.getInt(13));         
    				t.setCommodityViews(rs.getInt(14)); 
    				System.out.println(t.toString());
    				result.add(t);
    			}
    		}
    		System.out.println("CommodityDao-getCommodityListAll:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		System.out.println(e.toString());
    		System.out.println("CommodityDao-getCommodityListAll 失败！"+sqlString);
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
    public  ResCommodity getResCommodity(String id){
    	ResCommodity result=new ResCommodity();
    	Commodity t1=new Commodity();
    	String sqlString="select * from t_commodity where id=? ";
    	try{
    		ps=connection.prepareStatement(sqlString);
    		ps.setInt(1, Integer.parseInt(id));
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){         
    				t1.setId(rs.getInt(1));         
    				t1.setUsersID(rs.getInt(2));         
    				t1.setCommodityImageUrl(rs.getString(3));         
    				t1.setCommodityName(rs.getString(4));         
    				t1.setCommodityDetail(rs.getString(5));         
    				t1.setCommodityAddress(rs.getString(6));         
    				t1.setCommodityPrice(rs.getString(7));         
    				t1.setCategoryID(rs.getInt(8));         
    				t1.setCommodityBargain(rs.getInt(9));         
    				t1.setCommodityPhone(rs.getInt(10));         
    				t1.setCommodityQQ(rs.getInt(11));         
    				t1.setCommodityAddTime(rs.getString(12));         
    				t1.setCommodityStatus(rs.getInt(13));         
    				t1.setCommodityViews(rs.getInt(14)); 
    			}
        		result.errorcode=0;
        		result.errormsg="请求成功！";
        		result.data=t1;
    		}else {
        		result.errorcode=0;
        		result.errormsg="请求成功！";
        		result.data=null;
			}
    		System.out.println("CommodityDao-getCommodity:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		result.errorcode=-1;
			result.errormsg=e.toString();
    		result.data=null;
    		System.out.println(e.toString());
    		System.out.println("CommodityDao-getResCommodity 失败！"+sqlString);
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
	public BaseResponse deleteResCommodity(Commodity t) {
		BaseResponse result=new BaseResponse();
		int n = 0;// n=0说明删除不成功
		String sqlString = "delete from t_commodity where id=?";
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
    		System.out.println("CommodityDao-deleteResCommodity 失败！"+sqlString);
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
	public int deleteCommodity(String id) {
		int n = 0;// n=0说明删除不成功
		String sqlString = "delete from t_commodity where id=?";
		try {
			ps = connection.prepareStatement(sqlString);
			ps.setString(1, id);
			n = ps.executeUpdate();
		} catch (Exception e) {
    		System.out.println("CommodityDao-deleteCommodity 失败！"+sqlString);
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
    public BaseResponse addResCommodity(Commodity t){
		BaseResponse result=new BaseResponse();
        int n=0;//n=0说明添加不成功
		String sqlString="insert into t_commodity(usersID,commodityImageUrl,commodityName,commodityDetail,commodityAddress,commodityPrice,categoryID,commodityBargain,commodityPhone,commodityQQ,commodityAddTime,commodityStatus,commodityViews) "+"values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    	try{
    		ps=connection.prepareStatement(sqlString);            
            ps.setInt(1, t.getUsersID());            
            ps.setString(2, t.getCommodityImageUrl());            
            ps.setString(3, t.getCommodityName());            
            ps.setString(4, t.getCommodityDetail());            
            ps.setString(5, t.getCommodityAddress());            
            ps.setString(6, t.getCommodityPrice());            
            ps.setInt(7, t.getCategoryID());            
            ps.setInt(8, t.getCommodityBargain());            
            ps.setInt(9, t.getCommodityPhone());            
            ps.setInt(10, t.getCommodityQQ());            
            ps.setString(11, t.getCommodityAddTime());            
            ps.setInt(12, t.getCommodityStatus());            
            ps.setInt(13, t.getCommodityViews());         
    		n=ps.executeUpdate();
			if (n>0) {
				result.errorcode=0;
				result.errormsg="添加成功！";
			} else {
				result.errorcode=-1;
				result.errormsg="添加失败！";
			}
    		System.out.println("CommodityDao-addResCommodity:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
			result.errorcode=-1;
			result.errormsg=e.toString();
    		System.out.println("CommodityDao-addResCommodity 失败！");
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
    public  int addCommodity(Commodity t){
        int n=0;//n=0说明添加不成功
		String sqlString="insert into t_commodity(usersID,commodityImageUrl,commodityName,commodityDetail,commodityAddress,commodityPrice,categoryID,commodityBargain,commodityPhone,commodityQQ,commodityAddTime,commodityStatus,commodityViews) "+"values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    	try{
    		ps=connection.prepareStatement(sqlString);            
            ps.setInt(1, t.getUsersID());            
            ps.setString(2, t.getCommodityImageUrl());            
            ps.setString(3, t.getCommodityName());            
            ps.setString(4, t.getCommodityDetail());            
            ps.setString(5, t.getCommodityAddress());            
            ps.setString(6, t.getCommodityPrice());            
            ps.setInt(7, t.getCategoryID());            
            ps.setInt(8, t.getCommodityBargain());            
            ps.setInt(9, t.getCommodityPhone());            
            ps.setInt(10, t.getCommodityQQ());            
            ps.setString(11, t.getCommodityAddTime());            
            ps.setInt(12, t.getCommodityStatus());            
            ps.setInt(13, t.getCommodityViews());         
    		n=ps.executeUpdate();
    		System.out.println("CommodityDao-addCommodity:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
    		System.out.println("CommodityDao-addCommodity 失败！");
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
    public  BaseResponse updateResCommodity1(String id){
		BaseResponse result=new BaseResponse();
        int n=0;//n=0说明修改不成功
		String sqlString="update t_commodity set commodityViews=?  where id=?";
    	try{
    		int i=getCommodity(id).getCommodityViews()+1; 
    		ps=connection.prepareStatement(sqlString); 
    		ps.setInt(1 , i);    
    		ps.setInt(2 , Integer.parseInt(id));     
    		n=ps.executeUpdate();
			if (n>0) {
				result.errorcode=0;
				result.errormsg="修改成功！";
			} else {
				result.errorcode=-1;
				result.errormsg="修改失败！";
			}
    		System.out.println("CommodityDao-updateResCommodity:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
			result.errorcode=-1;
			result.errormsg=e.toString();
    		System.out.println("CommodityDao-updateResCommodity 失败！"+sqlString);
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
    
    //修改信息-JOSN
    public  BaseResponse updateResCommodity(Commodity t){
		BaseResponse result=new BaseResponse();
        int n=0;//n=0说明修改不成功
		String sqlString="update t_commodity set usersID=?,commodityImageUrl=?,commodityName=?,commodityDetail=?,commodityAddress=?,commodityPrice=?,categoryID=?,commodityBargain=?,commodityPhone=?,commodityQQ=?,commodityAddTime=?,commodityStatus=?,commodityViews=?  where id=?";
    	try{
    		ps=connection.prepareStatement(sqlString);         
    		ps.setInt(14 , t.getId());         
    		ps.setInt(1 , t.getUsersID());         
    		ps.setString(2 , t.getCommodityImageUrl());         
    		ps.setString(3 , t.getCommodityName());         
    		ps.setString(4 , t.getCommodityDetail());         
    		ps.setString(5 , t.getCommodityAddress());         
    		ps.setString(6 , t.getCommodityPrice());         
    		ps.setInt(7 , t.getCategoryID());         
    		ps.setInt(8 , t.getCommodityBargain());         
    		ps.setInt(9 , t.getCommodityPhone());         
    		ps.setInt(10 , t.getCommodityQQ());         
    		ps.setString(11 , t.getCommodityAddTime());         
    		ps.setInt(12 , t.getCommodityStatus());         
    		ps.setInt(13 , t.getCommodityViews());         
    		n=ps.executeUpdate();
			if (n>0) {
				result.errorcode=0;
				result.errormsg="修改成功！";
			} else {
				result.errorcode=-1;
				result.errormsg="修改失败！";
			}
    		System.out.println("CommodityDao-updateResCommodity:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
			result.errorcode=-1;
			result.errormsg=e.toString();
    		System.out.println("CommodityDao-updateResCommodity 失败！"+sqlString);
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
    public  int updateCommodity(Commodity t){
        int n=0;//n=0说明修改不成功
		String sqlString="update t_commodity set usersID=?,commodityImageUrl=?,commodityName=?,commodityDetail=?,commodityAddress=?,commodityPrice=?,categoryID=?,commodityBargain=?,commodityPhone=?,commodityQQ=?,commodityAddTime=?,commodityStatus=?,commodityViews=?  where id=?";
    	try{
    		ps=connection.prepareStatement(sqlString);         
    		ps.setInt(14 , t.getId());         
    		ps.setInt(1 , t.getUsersID());         
    		ps.setString(2 , t.getCommodityImageUrl());         
    		ps.setString(3 , t.getCommodityName());         
    		ps.setString(4 , t.getCommodityDetail());         
    		ps.setString(5 , t.getCommodityAddress());         
    		ps.setString(6 , t.getCommodityPrice());         
    		ps.setInt(7 , t.getCategoryID());         
    		ps.setInt(8 , t.getCommodityBargain());         
    		ps.setInt(9 , t.getCommodityPhone());         
    		ps.setInt(10 , t.getCommodityQQ());         
    		ps.setString(11 , t.getCommodityAddTime());         
    		ps.setInt(12 , t.getCommodityStatus());         
    		ps.setInt(13 , t.getCommodityViews());         
    		n=ps.executeUpdate();
    		System.out.println("CommodityDao-updateCommodity:"+sqlString);
    		System.out.println(n);
        }catch(Exception e){
    		System.out.println("CommodityDao-updateCommodity 失败！"+sqlString);
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
    public  Commodity getCommodity(String id){
    	Commodity t=new Commodity();
    	String sqlString="select * from t_commodity where id=?";
    	try{
    		ps=connection.prepareStatement(sqlString);
    		ps.setInt(1, Integer.parseInt(id));
    		ResultSet rs=ps.executeQuery();
    		if(rs!=null){
    			while(rs.next()){         
    				t.setId(rs.getInt(1));         
    				t.setUsersID(rs.getInt(2));         
    				t.setCommodityImageUrl(rs.getString(3));         
    				t.setCommodityName(rs.getString(4));         
    				t.setCommodityDetail(rs.getString(5));         
    				t.setCommodityAddress(rs.getString(6));         
    				t.setCommodityPrice(rs.getString(7));         
    				t.setCategoryID(rs.getInt(8));         
    				t.setCommodityBargain(rs.getInt(9));         
    				t.setCommodityPhone(rs.getInt(10));         
    				t.setCommodityQQ(rs.getInt(11));         
    				t.setCommodityAddTime(rs.getString(12));         
    				t.setCommodityStatus(rs.getInt(13));         
    				t.setCommodityViews(rs.getInt(14)); 
    			}
    		}
    		System.out.println("CommodityDao-getCommodity:"+sqlString);
    		rs.close();
    	}catch(Exception e){
    		System.out.println(e.toString());
    		System.out.println("CommodityDao-getCommodity 失败！"+sqlString);
    	}finally{
			try {
//				ps.close();
				//connection.close();
			} catch (Exception e2) {
				System.out.println(e2.toString());
			}
		}
    	return t;
    }
}