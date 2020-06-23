package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBTool;
import vo.problem;
import vo.resource;

public class ProblemDaoImpl implements ProblemDao {
	//创建连接对象
	Connection conn=null;
	//创建语句对象
	PreparedStatement prst=null;
	
	//@Override
	//根据教师id和班级查询
	/*public List<problem> selectByTidClasses(int tid, String classes) {
		// TODO Auto-generated method stub
		//创建结果集对象
		ResultSet rs=null;
		//创建JavaBean对象（resource）
		problem problem=null;	
		//创建list集合
		List<problem> plist=new ArrayList<problem>();
		
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="select * from problem where tname=? and pclass=?";
			prst=conn.prepareStatement(sql);
			prst.setInt(1, tid);
			prst.setString(2, classes);
			//获得结果集
			rs=prst.executeQuery();
			while(rs.next()){
				problem=new problem();
				problem.setPid(rs.getInt("pid"));
				problem.setTid(tid);
				problem.setPoblem(rs.getString("problem"));
				problem.setOptionA(rs.getString("optionA"));
				problem.setOptionB(rs.getString("optionB"));
				problem.setOptionC(rs.getString("optionC"));
				problem.setOptionD(rs.getString("optionD"));
				problem.setSolution(rs.getString("solution"));
				problem.setAnalyzing(rs.getString("analyzing"));
				problem.setPclass(classes);
				plist.add(problem);
					}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst, rs);
		}		
		return plist;
	}*/

	@Override
	//根据教师id查询
	public List<problem> selectByTid(int tid) {
		// TODO Auto-generated method stub
		//创建结果集对象
		ResultSet rs=null;
		//创建JavaBean对象（problem）
		problem problem=null;	
		//创建list集合
		List<problem> plist=new ArrayList<problem>();		
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="select * from problem where tpname=?";
			prst=conn.prepareStatement(sql);
			prst.setInt(1, tid);
			//获得结果集
			rs=prst.executeQuery();
			while(rs.next()){
				problem=new problem();
				problem.setPid(rs.getInt("pid"));
				problem.setTid(tid);
				problem.setPoblem(rs.getString("problem"));
				problem.setOptionA(rs.getString("optionA"));
				problem.setOptionB(rs.getString("optionB"));
				problem.setOptionC(rs.getString("optionC"));
				problem.setOptionD(rs.getString("optionD"));
				problem.setSolution(rs.getString("solution"));
				problem.setAnalyzing(rs.getString("analyzing"));
				//problem.setPclass(rs.getString("pclass"));
				plist.add(problem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst, rs);
		}				
		return plist;
	}

	@Override
	//添加题目
	public void insertProblem(problem problem) {
		// TODO Auto-generated method stub	
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="insert into problem(tpname,problem,optionA,optionB,optionC,optionD,solution,analyzing) values(?,?,?,?,?,?,?,?)";
			prst=conn.prepareStatement(sql);
			prst.setInt(1, problem.getTid());
			prst.setString(2, problem.getPoblem());
			prst.setString(3, problem.getOptionA());
			prst.setString(4, problem.getOptionB());
			prst.setString(5, problem.getOptionC());
			prst.setString(6, problem.getOptionD());
			prst.setString(7, problem.getSolution());
			prst.setString(8, problem.getAnalyzing());
			prst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst);
		}		
	}

	@Override
	//根据题目编号查询
	public List<problem> selectByPidlist(String[] pidlist) {
		// TODO Auto-generated method stub
		//创建结果集对象
		ResultSet rs=null;
		//创建JavaBean对象（resource）
		problem problem=null;	
		//创建list集合
		List<problem> plist=new ArrayList<problem>();
		try {
			//建立连接
			conn=DBTool.getConnection();
			for(String pid:pidlist){
				//sql语句
				String sql="select * from problem where pid=?";
				prst=conn.prepareStatement(sql);
				prst.setInt(1, Integer.parseInt(pid));
				//获得结果集
				rs=prst.executeQuery();
				while(rs.next()){
					problem=new problem();
					problem.setPid(Integer.parseInt(pid));
					problem.setTid(rs.getInt("tpname"));
					problem.setPoblem(rs.getString("problem"));
					problem.setOptionA(rs.getString("optionA"));
					problem.setOptionB(rs.getString("optionB"));
					problem.setOptionC(rs.getString("optionC"));
					problem.setOptionD(rs.getString("optionD"));
					problem.setSolution(rs.getString("solution"));
					problem.setAnalyzing(rs.getString("analyzing"));
					//problem.setPclass(rs.getString("pclass"));
					plist.add(problem);
				}
			}
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}finally{
			DBTool.close(conn, prst);
		}		
		return plist;
	}

}
