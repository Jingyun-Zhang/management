package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBTool;
import vo.classes;
import vo.qingjia;

public class QingjiaDaoImpl implements QingjiaDao {
	//创建连接对象
	Connection conn=null;
	//创建语句对象
	PreparedStatement prst=null;
	
	@Override
	//根据教师编号查找
	public List<qingjia> selectByTid(int tid) {
		// TODO Auto-generated method stub
		//创建结果集对象
		ResultSet rs=null;
		//创建JavaBean对象（qingjia）
		qingjia al=null;
		//创建List集合
		List<qingjia> allist=new ArrayList<qingjia>();		
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="select * from applicationLeave where altid=?";
			prst=conn.prepareStatement(sql);
			prst.setInt(1, tid);
			//获得结果集
			rs=prst.executeQuery();
			while(rs.next()){
				al=new qingjia();
				al.setAlsid(rs.getString("alsid"));
				al.setAlsname(rs.getString("alsname"));
				al.setAlclass(rs.getString("alclass"));
				al.setAlreason(rs.getString("alreason"));
				al.setAlstartdate(rs.getDate("alstartdate"));
				al.setAlenddate(rs.getDate("alenddate"));
				al.setAltid(tid);
				allist.add(al);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst, rs);
		}				
		return allist;
	}

	@Override
	//添加请假条
	public void insertqingjia(qingjia al) {
		// TODO Auto-generated method stub	
		try {
			//建立连接
			conn=DBTool.getConnection();
			//sql语句
			String sql="insert into applicationleave(alsid,alsname,alclass,alreason,alstartdate,alenddate,altid) values(?,?,?,?,?,?,?)";
			prst=conn.prepareStatement(sql);
			prst.setString(1,al.getAlsid());
			prst.setString(2,al.getAlsname());
			prst.setString(3,al.getAlclass());
			prst.setString(4,al.getAlreason());
			prst.setDate(5,al.getAlstartdate());
			prst.setDate(6,al.getAlenddate());
			prst.setInt(7,al.getAltid());
			prst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBTool.close(conn, prst);
		}		
	}

}
