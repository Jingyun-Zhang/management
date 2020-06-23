package Dao;

import java.util.List;

import vo.qingjia;

public interface QingjiaDao {
	//根据教师编号查找
	public List<qingjia> selectByTid(int tid);
	//添加请假条
	public void insertqingjia(qingjia al);
}
