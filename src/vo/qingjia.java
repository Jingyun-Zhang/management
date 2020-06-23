package vo;

import java.sql.Date;

public class qingjia {
	private int alid;//编号
	private String alsid;//学号
	private String alsname;//姓名
	private String alclass;//班级
	private String alreason;//原因
	private Date alstartdate;//开始时间
	private Date alenddate;//结束时间
	private int altid;//教师编号
	public int getAlid() {
		return alid;
	}
	public void setAlid(int alid) {
		this.alid = alid;
	}
	public String getAlsid() {
		return alsid;
	}
	public void setAlsid(String alsid) {
		this.alsid = alsid;
	}
	public String getAlsname() {
		return alsname;
	}
	public void setAlsname(String alsname) {
		this.alsname = alsname;
	}
	public String getAlclass() {
		return alclass;
	}
	public void setAlclass(String alclass) {
		this.alclass = alclass;
	}
	public String getAlreason() {
		return alreason;
	}
	public void setAlreason(String alreason) {
		this.alreason = alreason;
	}
	public Date getAlstartdate() {
		return alstartdate;
	}
	public void setAlstartdate(Date alstartdate) {
		this.alstartdate = alstartdate;
	}
	public Date getAlenddate() {
		return alenddate;
	}
	public void setAlenddate(Date alenddate) {
		this.alenddate = alenddate;
	}
	public int getAltid() {
		return altid;
	}
	public void setAltid(int altid) {
		this.altid = altid;
	}
}
