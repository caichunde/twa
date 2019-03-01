package com.dchb.model;
import java.sql.*; 
public class TestDB{ 
	/** * @param args */
	public static void main(String[] args) {
		// TODO 自动生成方法存根
		String url = "jdbc:sqlserver://192.168.3.188:1433;DatabaseName=HDocs"; 
		String userName = "hbinface";
		String password = "6002@hbkjy"; 
		String sql = null; Connection conn = null; 
		Statement stmt = null; 
		try { 
			System.out.println("加载驱动之前"); 
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
			System.out.println("驱动加载成功"); 
		} catch (ClassNotFoundException e) {
			System.err.print("驱动加载失败"); 
		} try { 
			System.out.println("连接数据库之前"); 
			conn = DriverManager.getConnection(url, userName, password); 
			System.out.println("数据库连接成功"); 
		} catch (SQLException e) {
			System.out.println("数据库连接失败"); e.printStackTrace(); 
		} 
		} 
}
	