package Dao;

import java.util.List;

import vo.resource;

public interface ResourceDao {
	//按发布人（教师id）和班级查询
	public List<resource> selectByTidRclass(int tid,String rclass);
	//按发布人查询
	public List<resource> selectByTid(int tid);
	//添加资源
	public void insert(resource resource);
}
