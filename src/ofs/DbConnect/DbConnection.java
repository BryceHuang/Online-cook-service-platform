package ofs.DbConnect;

import java.sql.*;

public class DbConnection {
private static String url = "jdbc:mysql://localhost:3306/ofs?useUnicode=true&characterEncoding=utf-8";
	
	public DbConnection() {	}

	public static Connection getConnection(){
		Connection cn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(url,"root","root");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cn;
	}
}
