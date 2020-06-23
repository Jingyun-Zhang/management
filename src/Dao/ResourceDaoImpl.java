package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBTool;
import vo.resource;

public class ResourceDaoImpl implements ResourceDao {
	//创建连接对象
	Connection conn=null;
	//创建语句对象
	PreparedStatement prst=null;
	@Override
	
	//根据班级和教师id获取资料
	public List<resource> selectByTidRclass(int tid, String rclass) {
		// TODO Auto-generated method stub
		//创建结果集对象
		ResultSet rs=null;
		//创建JavaBean对象（resource）
		resource resource=null;	
		//创建list集合
		List<resource> rlist=new ArrayList<resource>();
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="select * from resource where trid=? and rclass=?";
			prst=conn.prepareStatement(sql);
			prst.setInt(1, tid);
			prst.setString(2, rclass);
			//获得结果集
			rs=prst.executeQuery();
			while(rs.next()){
				resource=new resource();
				resource.setRid(rs.getInt("rid"));
				resource.setRname(rs.getString("rname"));
				resource.setTrname(rs.getString("trname"));
				resource.setRdate(rs.getDate("rdate"));
				resource.setRclass(rclass);
				resource.setTrid(tid);
				resource.setRsize(rs.getString("rsize"));
				rlist.add(resource);
			}
		} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		}finally{
					DBTool.close(conn, prst, rs);
		}		
		return rlist;
	}
	
	@Override
	//按发布人id查询
	public List<resource> selectByTid(int tid) {
		// TODO Auto-generated method stub
		//创建结果集对象
		ResultSet rs=null;
		//创建JavaBean对象（resource）
		resource resource=null;	
		//创建list集合
		List<resource> rlist=new ArrayList<resource>();
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="select * from resource where trid=?";
			prst=conn.prepareStatement(sql);
			prst.setInt(1, tid);
			//获得结果集
			rs=prst.executeQuery();
			while(rs.next()){
				resource=new resource();
				resource.setRid(rs.getInt("rid"));
				resource.setRname(rs.getString("rname"));
				resource.setTrname(rs.getString("trname"));
				resource.setRdate(rs.getDate("rdate"));
				resource.setRsize(rs.getString("rsize"));
				resource.setRclass(rs.getString("rclass"));
				resource.setTrid(tid);
				rlist.add(resource);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst, rs);
		}		
		return rlist;
	}

	@Override
	//添加资源
	public void insert(resource resource) {
		// TODO Auto-generated method stub
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="insert into resource(rname,trname,rdate,rsize,rclass,trid) values(?,?,?,?,?,?)";
			prst=conn.prepareStatement(sql);
			prst.setString(1, resource.getRname());
			prst.setString(2, resource.getTrname());
			prst.setDate(3, resource.getRdate());
			prst.setString(4, resource.getRsize());
			prst.setString(5, resource.getRclass());
			prst.setInt(6, resource.getTrid());
			prst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst);
		}
		
	}

}
