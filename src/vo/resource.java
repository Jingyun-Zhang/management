package vo;

import java.sql.Date;

public class resource {
	private int rid;//资源id
	private String rname;//资源名称
	private int trid;//资源上传者（id）
	private String trname;//资源上传者姓名
	private Date rdate;//资源日期
	private String rsize;//大小
	private String rclass;//资源所在的班级
	//set、get方法
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public int getTrid() {
		return trid;
	}
	public void setTrid(int trid) {
		this.trid = trid;
	}
	public String getTrname() {
		return trname;
	}
	public void setTrname(String trname) {
		this.trname = trname;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	public String getRsize() {
		return rsize;
	}
	public void setRsize(String rsize) {
		this.rsize = rsize;
	}
	public String getRclass() {
		return rclass;
	}
	public void setRclass(String rclass) {
		this.rclass = rclass;
	}
}
