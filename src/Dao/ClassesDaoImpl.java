package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBTool;
import vo.classes;
import vo.resource;
import vo.teacher;

public class ClassesDaoImpl implements ClassesDao {
	//创建连接对象
	Connection conn=null;
	//创建语句对象
	PreparedStatement prst=null;
	
	@Override
	//根据教师id查找其创建的所有班课
	public List<classes> showAllClassesByTid(int tid) {
		// TODO Auto-generated method stub
		//创建结果集对象
		ResultSet rs=null;
		//创建JavaBean对象（classes）
		classes classes=null;
		//创建List集合
		List<classes> clist=new ArrayList<classes>();		
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="select * from class where cteacher=?";
			prst=conn.prepareStatement(sql);
			prst.setInt(1, tid);
			//获得结果集
			rs=prst.executeQuery();
			while(rs.next()){
				classes=new classes();
				classes.setClasses(rs.getString("class"));
				classes.setCteacher(tid);
				classes.setCmajor(rs.getString("cmajor"));
				clist.add(classes);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst, rs);
		}		
		return clist;		
	}

	@Override
	//根据班级编号查找班级
	public classes selectByCid(int cid) {
		// TODO Auto-generated method stub
		//创建结果集对象
		ResultSet rs=null;
		//创建JavaBean对象（classes）
		classes classes=new classes();		
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="select * from class where cid=?";
			prst=conn.prepareStatement(sql);
			prst.setInt(1, cid);
			//获得结果集
			rs=prst.executeQuery();
			if(rs.next()){
				classes.setCid(cid);
				classes.setClasses(rs.getString("class"));				
				classes.setCteacher(rs.getInt("cteacher"));
				classes.setCmajor(rs.getString("cmajor"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst, rs);
		}			
		return classes;
	}
	
	//@Override
	//根据教师id查找班级号
	/*public List<String> selectClasses(int tid) {
		// TODO Auto-generated method stub
		//创建结果集对象
		ResultSet rs=null;
		//创建JavaBean对象（classes）
		classes classes=null;
		//创建List集合
		List<String> clist1=new ArrayList<String>();		
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="select * from class where cteacher=?";
			prst=conn.prepareStatement(sql);
			prst.setInt(1, tid);
			//获得结果集
			rs=prst.executeQuery();
			while(rs.next()){
				clist1.add(rs.getString("class"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst, rs);
		}		
		
		return clist1;
	}*/
	
	@Override
	//添加班课
	public void insertClass(String classes, int teacher, String major) {
		// TODO Auto-generated method stub
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="insert into class(class,cteacher,cmajor) values(?,?,?)";
			prst=conn.prepareStatement(sql);
			prst.setString(1, classes);
			prst.setInt(2, teacher);
			prst.setString(3, major);
			prst.executeUpdate();						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst);
		}			
	}
	
	@Override
	//根据教师id和班号查找班级
	public classes selectByTidClasses(int tid, String classes) {
		// TODO Auto-generated method stub
		//创建结果集对象
		ResultSet rs=null;
		//创建JavaBean对象（classes）
		classes classes2=new classes();
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="select * from class where cteacher=? and class=?";
			prst=conn.prepareStatement(sql);
			prst.setInt(1, tid);
			prst.setString(2, classes);
			//获得结果集
			rs=prst.executeQuery();
			if(rs.next()){
				classes2.setCid(rs.getInt("cid"));
				classes2.setClasses(classes);
				classes2.setCteacher(tid);
				classes2.setCmajor(rs.getString("cmajor"));				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst, rs);
		}			
		return classes2;
	}

	

}
