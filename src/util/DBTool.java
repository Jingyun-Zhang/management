package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBTool {
	static public Connection getConnection(){
		//创建连接对象
		Connection conn=null;
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/management?characterEncoding=utf-8";
		try {
			//载入JDBC驱动程序
			Class.forName(driver);
			//建立连接
			conn=DriverManager.getConnection(url,"root","mysql");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	//关闭对象（写操作）
	static public void close(Connection conn,PreparedStatement prst){
		if(prst!=null){
			try {
				//关闭语句对象
				prst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				//关闭连接对象
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//关闭对象（读操作）
	static public void close(Connection conn,PreparedStatement prst,ResultSet rs){
		if(rs!=null){
			try {
				//关闭结果集
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(prst!=null){
			try {
				//关闭语句对象
				prst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				//关闭连接对象
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
