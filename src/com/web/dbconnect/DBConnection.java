package com.web.dbconnect;

import java.sql.*;

public class DBConnection {
	public static Connection connectDB() throws ClassNotFoundException, SQLException {
		final String url = "jdbc:mysql://121.164.24.120:3306/web";
		final String id = "web";
		final String pwd = "Cyber2020!@";
		final String driver = "com.mysql.cj.jdbc.Driver";
		
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, id, pwd);
		return conn;
	}
}
