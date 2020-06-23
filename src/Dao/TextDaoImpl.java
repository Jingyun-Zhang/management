package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBTool;
import vo.problem;
import vo.text;

public class TextDaoImpl implements TextDao {
	//创建连接对象
	Connection conn=null;
	//创建语句对象
	PreparedStatement prst=null;
	
	@Override
	//按教师id查询
	public List<text> selectByTid(int tid) {
		// TODO Auto-generated method stub
		//创建结果集对象
		ResultSet rs=null;
		//创建JavaBean对象（text）
		text text=null;	
		//创建list集合
		List<text> textlist=new ArrayList<text>();
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="select * from text where texttid=?";
			prst=conn.prepareStatement(sql);
			prst.setInt(1, tid);
			//获得结果集
			rs=prst.executeQuery();
			while(rs.next()){
				text=new text();
				/*System.out.println(rs.getInt("textid"));
				System.out.println(rs.getString("textpid"));*/
				text.setTextid(rs.getInt("textid"));
				text.setTextpid(rs.getString("textpid"));//rs.getArray("textpid");
				text.setTextclass(rs.getString("textclass"));
				text.setTexttid(tid);
				textlist.add(text);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst, rs);
		}
		return textlist;
	}

	@Override
	//添加试卷
	public void insert(text text) {
		// TODO Auto-generated method stub
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="insert into text(textpid,textclass,texttid) values(?,?,?)";
			prst=conn.prepareStatement(sql);
			prst.setString(1, text.getTextpid());
			prst.setString(2, text.getTextclass());
			prst.setInt(3, text.getTexttid());
			prst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst);
		}

	}

	@Override
	//按教师id和班号查询
	public List<text> selectByTidClasses(int tid, String classes) {
		// TODO Auto-generated method stub
		//创建结果集对象
		ResultSet rs=null;
		//创建JavaBean对象（text）
		text text=null;	
		//创建list集合
		List<text> textlist=new ArrayList<text>();
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="select * from text where texttid=? and textclass=?";
			prst=conn.prepareStatement(sql);
			prst.setInt(1, tid);
			prst.setString(2, classes);
			//获得结果集
			rs=prst.executeQuery();
			while(rs.next()){
				text=new text();
				/*System.out.println(rs.getInt("textid"));
				System.out.println(rs.getString("textpid"));*/
				text.setTextid(rs.getInt("textid"));
				text.setTextpid(rs.getString("textpid"));//rs.getArray("textpid");
				text.setTextclass(rs.getString("textclass"));
				text.setTexttid(tid);
				textlist.add(text);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst, rs);
		}
		return textlist;
	}

	@Override
	//按试卷编号查询
	public text selectByTextid(int textid) {
		// TODO Auto-generated method stub
		//创建结果集对象
		ResultSet rs=null;
		//创建JavaBean对象（text）
		text text=new text();
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="select * from text where textid=?";
			prst=conn.prepareStatement(sql);
			prst.setInt(1, textid);
			//获得结果集
			rs=prst.executeQuery();
			if(rs.next()){
				text.setTextid(textid);
				text.setTextpid(rs.getString("textpid"));
				text.setTexttid(rs.getInt("texttid"));
				text.setTextclass(rs.getString("textclass"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst, rs);
		}	
		return text;
	}

}
