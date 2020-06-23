package vo;

public class score {
	private int pstextid;//试卷编号
	private String pssid;//学号
	private int grade;//分数
	private String psrow;//正误
	//set、get方法
	public int getPstextid() {
		return pstextid;
	}
	public void setPstextid(int pstextid) {
		this.pstextid = pstextid;
	}
	public String getPssid() {
		return pssid;
	}
	public void setPssid(String pssid) {
		this.pssid = pssid;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getPsrow() {
		return psrow;
	}
	public void setPsrow(String psrow) {
		this.psrow = psrow;
	}
}
