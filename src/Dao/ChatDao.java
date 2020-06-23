package Dao;

import java.util.List;

import vo.chat;

public interface ChatDao {
	//根据教师id查询
	public List<chat> selectByTid(int tid);
	//根据教师id和班级id查询
	public List<chat> selectByTidCid(int tid,int cid);
	//添加疑问
	public void insertRaise(chat ch);
	//根据疑问修改回复
	public void updateByChid(String answer,int chid);
}
