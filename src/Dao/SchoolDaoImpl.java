package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBTool;
import vo.school;
import vo.teacher;

public class SchoolDaoImpl implements SchoolDao {
	//创建连接对象
	Connection conn=null;
	//创建语句对象
	PreparedStatement prst=null;
	@Override
	//查找学校
	public school selectSchool(String school) {
		// TODO Auto-generated method stub
		//创建结果集对象
		ResultSet rs=null;
		//创建JavaBean对象（school）
		school school2=null;	
		
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="select * from school where school=?";
			prst=conn.prepareStatement(sql);
			prst.setString(1, school);
			//获得结果集
			rs=prst.executeQuery();
			if(rs.next()){
				//若该学校已存在
				school2=new school();
				school2.setSchool(school);
			}else{
				//该学校不存在,则添加该学校
				String sql2="insert into school(school) values(?)";
				prst=conn.prepareStatement(sql2);
				prst.setString(1,school);
				prst.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst, rs);
		}		
		return null;
	}

	//@Override
	//添加学校
	/*public void insertSchool(String school) {
		// TODO Auto-generated method stub
		
	}*/

}
