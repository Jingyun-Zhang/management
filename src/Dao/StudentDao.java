package Dao;

import java.util.List;

import vo.student;
import vo.user;

public interface StudentDao {
	//按学生用户名和所在学校查询
	public user selectBySunameSuschool(String suname,String suschool);
	//按学生学号和学校查询
	public student selectBySidSschool(String sid,String sschool);
	//按学校和班级查找学生
	public List<student> selectBySchoolClass(String school,int classes);
	//添加学生
	public void insertStudent(student student);
	//添加管理员
	public void insertSuser(user suser);
	//根据学号和学校修改密码
	public void updatePass(String sid,String school,String pwd);
}
