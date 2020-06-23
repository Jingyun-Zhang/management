package Dao;

import java.util.List;

import vo.score;
import vo.text;
import vo.textScore;

public interface ScoreDao {
	//添加
	public void insertScore(score score);
	//按学号和教师id查询
	public List<textScore> selectByTidSid(int tid,String sid);
	//按教师id查找
	public List<textScore> selectByTid(int tid);
	//按教师id和试卷号查找
	public List<textScore> selectByTidText(int tid,List<text> textlist);
}