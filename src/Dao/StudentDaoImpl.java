package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBTool;
import vo.student;
import vo.user;

public class StudentDaoImpl implements StudentDao {
	//创建连接对象
	Connection conn=null;
	//创建语句对象
	PreparedStatement prst=null;
	
	@Override
	//按学生用户名和学校查询
	public user selectBySunameSuschool(String suname,String suschool) {
		// TODO Auto-generated method stub
		//创建结果集对象
		ResultSet rs=null;
		//创建JavaBean对象（user）
		user user=null;	
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="select * from suser where suname=? and suschool=?";
			prst=conn.prepareStatement(sql);
			prst.setString(1,suname);
			prst.setString(2,suschool);
			//获得结果集
			rs=prst.executeQuery();
			if(rs.next()){
				user=new user();
				user.setSuid(rs.getInt("suid"));
				user.setSuname(suname);
				user.setSuschool(suschool);
				user.setSupass(rs.getString("supass"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst, rs);
		}		
		return user;
	}

	@Override
	//按学校和班级查找学生
	public List<student> selectBySchoolClass(String school, int classes) {
		// TODO Auto-generated method stub
		//创建结果集对象
		ResultSet rs=null;
		//创建JavaBean对象（student）
		student student=null;
		//创建List对象
		List<student> slist=new ArrayList<student>();
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="select * from student where sclass=? and sschool=?";
			prst=conn.prepareStatement(sql);
			prst.setInt(1,classes);
			prst.setString(2,school);
			//获得结果集
			rs=prst.executeQuery();
			while(rs.next()){
				student=new student();
				student.setSid(rs.getString("sid"));
				student.setSname(rs.getString("sname"));
				student.setSsex(rs.getString("ssex"));
				student.setSclass(classes);
				student.setSschool(school);
				slist.add(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst, rs);
		}			
		return slist;
	}
	
	@Override
	//按学生学号和学校查询
	public student selectBySidSschool(String sid,String sschool) {
		// TODO Auto-generated method stub
		//创建结果集对象
		ResultSet rs=null;
		//创建JavaBean对象（user）
		student student=null;
		
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="select * from student where sid=? and sschool=?";
			prst=conn.prepareStatement(sql);
			prst.setString(1,sid);
			prst.setString(2,sschool);
			//获得结果集
			rs=prst.executeQuery();
			if(rs.next()){
				student=new student();
				student.setSid(sid);
				student.setSname(rs.getString("sname"));
				student.setSsex(rs.getString("ssex"));
				student.setSclass(rs.getInt("sclass"));
				student.setSschool(sschool);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst, rs);
		}		
		return student;
	}

	@Override
	//添加学生
	public void insertStudent(student student) {
		// TODO Auto-generated method stub
		
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="insert into student(sid,sname,ssex,sclass,sschool) values(?,?,?,?,?)";
			prst=conn.prepareStatement(sql);
			prst.setString(1,student.getSid());
			prst.setString(2,student.getSname());
			prst.setString(3,student.getSsex());
			prst.setInt(4, student.getSclass());
			prst.setString(5,student.getSschool());
			prst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst);
		}
	}

	@Override
	//添加管理员
	public void insertSuser(user suser) {
		// TODO Auto-generated method stub		
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="insert into suser(suname,suschool) values(?,?)";
			prst=conn.prepareStatement(sql);
			prst.setString(1,suser.getSuname());
			prst.setString(2,suser.getSuschool());
			prst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst);
		}
		
	}

	@Override
	//根据学号和学校修改密码
	public void updatePass(String sid,String school,String pwd) {
		// TODO Auto-generated method stub	
		//System.out.println(pwd);
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="update suser set supass=? where suname=? and suschool=?";
			prst=conn.prepareStatement(sql);
			prst.setString(1,pwd);
			prst.setString(2,sid);
			prst.setString(3,school);
			prst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst);
		}
		
	}





}
