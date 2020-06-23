package Dao;

import java.util.List;

import vo.classes;

public interface ClassesDao {
	//根据教师id查找其创建的所有班课
	public List<classes> showAllClassesByTid(int tid);
	//根据班级编号查找
	public classes selectByCid(int cid);
	//根据教师id查找班级号
	//public List<String> selectClasses(int tid);
	//添加班课
	public void insertClass(String classes,int teacher,String major);
	//根据教师id和班级查找
	public classes selectByTidClasses(int tid,String classes);
}