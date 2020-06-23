package vo;

public class user {
	private  int suid; //用户编号
	private String suname; //用户名（学号）
	private String suschool;//学校
	private String supass; //用户密码（设置初始密码为学号）
	//set、get方法
	public int getSuid() {
		return suid;
	}
	public void setSuid(int suid) {
		this.suid = suid;
	}
	public String getSuname() {
		return suname;
	}
	public void setSuname(String suname) {
		this.suname = suname;
	}
	public String getSuschool() {
		return suschool;
	}
	public void setSuschool(String suschool) {
		this.suschool = suschool;
	}
	public String getSupass() {
		return supass;
	}
	public void setSupass(String supass) {
		this.supass = supass;
	}
}
