package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBTool;
import vo.administrator;
import vo.teacher;

public class TeacherDaoImpl implements TeacherDao {
	//创建连接对象
	Connection conn=null;
	//创建语句对象
	PreparedStatement prst=null;
	@Override
	//按教师id查询
	public teacher selectByTid(int tid) {
		// TODO Auto-generated method stub
		//创建结果集对象
		ResultSet rs=null;
		//创建JavaBean对象（teacher）
		teacher teacher=null;	
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="select * from teacher where tid=?";
			prst=conn.prepareStatement(sql);
			prst.setInt(1, tid);
			//获得结果集
			rs=prst.executeQuery();
			if(rs.next()){
				teacher=new teacher();
				teacher.setTid(tid);
				teacher.setTnum(rs.getString("tnum"));
				teacher.setTname(rs.getString("tname"));
				teacher.setTsex(rs.getString("tsex"));
				teacher.setTschool(rs.getString("tschool"));
				teacher.setTitle(rs.getString("title"));
				teacher.setTemail(rs.getString("temail"));
				teacher.setTphone(rs.getString("tphone"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst, rs);
		}
		return teacher;
	}
	@Override
	//按教师工号和学校查询老师
	public teacher selectByTnumTschool(String tnum, String tschool) {
		// TODO Auto-generated method stub
		//创建结果集对象
		ResultSet rs=null;
		//创建JavaBean对象（teacher）
		teacher teacher=null;
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="select * from teacher where tnum=? and tschool=?";
			prst=conn.prepareStatement(sql);
			prst.setString(1,tnum);
			prst.setString(2,tschool);
			//获得结果集
			rs=prst.executeQuery();
			if(rs.next()){
				teacher=new teacher();
				teacher.setTid(rs.getInt("tid"));
				teacher.setTnum(tnum);
				teacher.setTname(rs.getString("tname"));
				teacher.setTsex(rs.getString("tsex"));
				teacher.setTschool(tschool);
				teacher.setTitle(rs.getString("title"));
				teacher.setTemail(rs.getString("temail"));
				teacher.setTphone(rs.getString("tphone"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst, rs);
		}		
		return teacher;
	}
	@Override
	//按教师工号和所在学校查询管理员
	public administrator selectByTunumTuschool(String tunum, String tuschool) {
		// TODO Auto-generated method stub
		//创建结果集对象
		ResultSet rs=null;
		//创建JavaBean对象（administrator）
		administrator admin=null;	
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="select * from tuser where tunum=? and tuschool=?";
			prst=conn.prepareStatement(sql);
			prst.setString(1,tunum);
			prst.setString(2,tuschool);
			//获得结果集
			rs=prst.executeQuery();
			if(rs.next()){
				admin=new administrator();
				admin.setTuid(rs.getInt("tuid"));
				admin.setTunum(tunum);
				admin.setTuschool(tuschool);
				admin.setTupass(rs.getString("tupass"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst, rs);
		}		
		return admin;
	}
	@Override
	//添加教师
	public void insertTeacher(teacher teacher) {
		// TODO Auto-generated method stub
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="insert into teacher(tnum,tname,tsex,tschool,title,temail,tphone) values(?,?,?,?,?,?,?)";
			prst=conn.prepareStatement(sql);
			prst.setString(1,teacher.getTnum());
			prst.setString(2,teacher.getTname());
			prst.setString(3,teacher.getTsex());
			prst.setString(4,teacher.getTschool());
			prst.setString(5,teacher.getTitle());
			prst.setString(6,teacher.getTemail());
			prst.setString(7,teacher.getTphone());
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
	public void insertAdmin(administrator admin) {
		// TODO Auto-generated method stub
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="insert into tuser(tuid,tunum,tuschool,tupass) values(?,?,?,?)";
			prst=conn.prepareStatement(sql);
			prst.setInt(1,admin.getTuid());
			prst.setString(2,admin.getTunum());
			prst.setString(3,admin.getTuschool());
			prst.setString(4,admin.getTupass());
			prst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst);
		}	
	}
}
