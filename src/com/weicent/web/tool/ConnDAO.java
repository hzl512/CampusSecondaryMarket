package com.weicent.web.tool;

import java.sql.Connection;
import java.sql.DriverManager;

//数据库连接
public class ConnDAO {
	public ConnDAO() {
	}

	// 连接数据库
	String driver = "org.gjt.mm.mysql.Driver";
	
	String uesr = "root";
	String userPassword = "123456";
	String url = "jdbc:mysql://localhost:3306/db_campus_secondary_market";
	
//	String uesr = "bdde3ad696b0485183000844439e2a9e";
//	String userPassword = "d1d2ce5c2e184280a6027c8f69eb7239";
//	String url = "jdbc:mysql://sqld.duapp.com:4050/mSIxyxyLAdhNNgYvEULy";
	
	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, uesr, userPassword);
			System.out.println("数据库连接成功！");
		} catch (Exception e) {
			System.out.println("数据库连接失败！");
		}
		return con;
	}
}
