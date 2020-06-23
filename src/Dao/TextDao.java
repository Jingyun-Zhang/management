package Dao;

import java.util.List;

import vo.text;

public interface TextDao {
	//按教师id查询
	public List<text> selectByTid(int tid);
	//添加
	public void insert(text text);
	//按教师id和班级查询
	public List<text> selectByTidClasses(int tid,String classes); 
	//按试卷编号查询
	public text selectByTextid(int textid);
}
