package vo;

public class teacher {
	private int tid;//教师id
	private String tnum;//教师工号
	private String tname;//教师姓名
	private String tsex;//教师性别
	private String tschool;//教师所在学校
	private String title;//教师职称
	private String temail;//教师邮箱
	private String tphone;//教师电话
	//set、get方法
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTnum() {
		return tnum;
	}
	public void setTnum(String tnum) {
		this.tnum = tnum;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTsex() {
		return tsex;
	}
	public void setTsex(String tsex) {
		this.tsex = tsex;
	}
	public String getTschool() {
		return tschool;
	}
	public void setTschool(String tschool) {
		this.tschool = tschool;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTemail() {
		return temail;
	}
	public void setTemail(String temail) {
		this.temail = temail;
	}
	public String getTphone() {
		return tphone;
	}
	public void setTphone(String tphone) {
		this.tphone = tphone;
	}
}
