package vo;

public class administrator {
	private int tuid; //管理员编号
	private String tunum; //管理员账户
	private String tuschool;//管理员所在学校
	private String tupass; //管理员密码
	//set、get方法
	public int getTuid() {
		return tuid;
	}
	public void setTuid(int tuid) {
		this.tuid = tuid;
	}
	public String getTunum() {
		return tunum;
	}
	public void setTunum(String tunum) {
		this.tunum = tunum;
	}
	public String getTuschool() {
		return tuschool;
	}
	public void setTuschool(String tuschool) {
		this.tuschool = tuschool;
	}
	public String getTupass() {
		return tupass;
	}
	public void setTupass(String tupass) {
		this.tupass = tupass;
	}
}
