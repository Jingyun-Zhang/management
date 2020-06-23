package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBTool;
import vo.chat;
import vo.problem;

public class ChatDaoImpl implements ChatDao {
	//创建连接对象
	Connection conn=null;
	//创建语句对象
	PreparedStatement prst=null;
	
	@Override
	//根据教师id查询
	public List<chat> selectByTid(int tid) {
		// TODO Auto-generated method stub
		//创建结果集对象
		ResultSet rs=null;
		//创建JavaBean对象（resource）
		chat chat=null;	
		//创建list集合
		List<chat> chlist=new ArrayList<chat>();		
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="select * from chat where chtid=?";
			prst=conn.prepareStatement(sql);
			prst.setInt(1, tid);
			//获得结果集
			rs=prst.executeQuery();
			while(rs.next()){
				chat=new chat();
				chat.setChid(rs.getInt("chid"));
				chat.setRaise(rs.getString("raise"));
				chat.setAnswer(rs.getString("answer"));
				chat.setChcid(rs.getInt("chcid"));
				chat.setChtid(tid);
				chlist.add(chat);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst, rs);
		}		
		return chlist;
	}

	@Override
	//根据教师id和班级id查询
	public List<chat> selectByTidCid(int tid, int cid) {
		// TODO Auto-generated method stub
		//创建结果集对象
		ResultSet rs=null;
		//创建JavaBean对象（resource）
		chat chat=null;	
		//创建list集合
		List<chat> chlist=new ArrayList<chat>();		
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="select * from chat where chcid=? and chtid=?";
			prst=conn.prepareStatement(sql);
			prst.setInt(1, cid);
			prst.setInt(2, tid);
			//获得结果集
			rs=prst.executeQuery();
			while(rs.next()){
				chat=new chat();
				chat.setChid(rs.getInt("chid"));
				chat.setRaise(rs.getString("raise"));
				chat.setAnswer(rs.getString("answer"));
				chat.setChcid(cid);
				chat.setChtid(tid);
				chlist.add(chat);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst, rs);
		}		
		return chlist;
	}

	@Override
	//添加疑问
	public void insertRaise(chat ch) {
		// TODO Auto-generated method stub		
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="insert into chat(raise,chcid,chtid) values(?,?,?)";
			prst=conn.prepareStatement(sql);
			prst.setString(1,ch.getRaise());
			prst.setInt(2,ch.getChcid());
			prst.setInt(3,ch.getChtid());
			prst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst);
		}		
	}

	@Override
	//根据疑问编号修改回复
	public void updateByChid(String answer, int chid) {
		// TODO Auto-generated method stub		
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="update chat set answer=? where chid=?";
			prst=conn.prepareStatement(sql);
			prst.setString(1,answer);
			prst.setInt(2,chid);
			prst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst);
		}
		
	}

}
