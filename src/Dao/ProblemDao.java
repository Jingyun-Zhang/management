package Dao;

import java.util.List;

import vo.problem;

public interface ProblemDao {
	//根据教师id和班级查询
	//public List<problem> selectByTidClasses(int tid,String classes);
	//根据教师id查询
	public List<problem> selectByTid(int tid);
	//添加题目
	public void insertProblem(problem problem);
	//根据题目编号查询
	public List<problem> selectByPidlist(String[] pidlist);
}
