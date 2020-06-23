package Dao;

import vo.administrator;
import vo.teacher;

public interface TeacherDao {
	//按教师id查询
	public teacher selectByTid(int tid);
	//按教师工号和所在学校查询教师
	public teacher selectByTnumTschool(String tnum,String tschool);
	//按教师工号和所在学校查询管理员
	public administrator selectByTunumTuschool(String tunum,String tuschool);
	//添加教师
	public void insertTeacher(teacher teacher);
	//添加管理员
	public void insertAdmin(administrator admin);
}
