package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBTool;
import vo.problem;
import vo.score;
import vo.text;
import vo.textScore;

public class ScoreDaoImpl implements ScoreDao {
	//创建连接对象
	Connection conn=null;
	//创建语句对象
	PreparedStatement prst=null;
	
	@Override
	//添加
	public void insertScore(score score) {
		// TODO Auto-generated method stub	
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="insert into score(pstextid,pssid,grade,psrow) values(?,?,?,?)";
			prst=conn.prepareStatement(sql);
			prst.setInt(1, score.getPstextid());
			prst.setString(2, score.getPssid());
			prst.setInt(3, score.getGrade());
			prst.setString(4, score.getPsrow());
			prst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst);
		}
		
	}

	@Override
	//按教id和学号查找
	public List<textScore> selectByTidSid(int tid,String sid) {
		// TODO Auto-generated method stub
		//创建结果集对象
		ResultSet rs=null;
		//创建JavaBean对象（textScore）
		textScore textScore=null;	
		//创建list集合
		List<textScore> textScoreList=new ArrayList<textScore>();		
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="select * from `text-score` where texttid=? and pssid=?";
			prst=conn.prepareStatement(sql);
			prst.setInt(1, tid);
			prst.setString(2, sid);
			//获得结果集
			rs=prst.executeQuery();
			while(rs.next()){
				textScore=new textScore();
				textScore.setTextid(rs.getInt("textid"));
				textScore.setTextpidlist(rs.getString("textpid"));
				textScore.setClasses(rs.getString("textclass"));
				textScore.setTexttid(tid);
				textScore.setPstextid(rs.getInt("textid"));
				textScore.setPssid(sid);
				textScore.setPsrow(rs.getString("psrow"));
				textScore.setGrade(rs.getInt("grade"));
				textScoreList.add(textScore);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst, rs);
		}		
		return textScoreList;
	}

	@Override
	//按教师id查找
	public List<textScore> selectByTid(int tid) {
		// TODO Auto-generated method stub
		//创建结果集对象
		ResultSet rs=null;
		//创建JavaBean对象（textScore）
		textScore textScore=null;	
		//创建list集合
		List<textScore> textScoreList=new ArrayList<textScore>();
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="select * from `text-score` where texttid=?";
			prst=conn.prepareStatement(sql);
			prst.setInt(1, tid);
			//获得结果集
			rs=prst.executeQuery();
			while(rs.next()){
				textScore=new textScore();
				textScore.setTextid(rs.getInt("textid"));
				textScore.setTextpidlist(rs.getString("textpid"));
				textScore.setClasses(rs.getString("textclass"));
				textScore.setTexttid(tid);
				textScore.setPstextid(rs.getInt("textid"));
				textScore.setPssid(rs.getString("pssid"));
				textScore.setPsrow(rs.getString("psrow"));
				textScore.setGrade(rs.getInt("grade"));
				textScoreList.add(textScore);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst, rs);
		}		
		return textScoreList;
	}

	@Override
	public List<textScore> selectByTidText(int tid, List<text> textlist) {
		// TODO Auto-generated method stub
		//创建结果集对象
		ResultSet rs=null;
		//创建JavaBean对象（textScore）
		textScore textScore=null;
		//创建list集合
		List<textScore> textScoreList=new ArrayList<textScore>();
		try {
			//建立连接
			conn=DBTool.getConnection();
			for(text text:textlist){
				//sql语句
				String sql="select * from `text-score` where texttid=? and textid=?";
				prst=conn.prepareStatement(sql);
				prst.setInt(1, tid);
				prst.setInt(2, text.getTextid());
				//获得结果集
				rs=prst.executeQuery();
				while(rs.next()){
					textScore=new textScore();
					textScore.setTextid(text.getTextid());
					textScore.setTextpidlist(rs.getString("textpid"));
					textScore.setClasses(rs.getString("textclass"));
					textScore.setTexttid(tid);
					textScore.setPstextid(text.getTextid());
					textScore.setPssid(rs.getString("pssid"));
					textScore.setPsrow(rs.getString("psrow"));
					textScore.setGrade(rs.getInt("grade"));
					textScoreList.add(textScore);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return textScoreList;
	}

}
