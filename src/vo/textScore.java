package vo;

public class textScore {
	private int textid;//试卷编号
	private String textpidlist;//测试题
	private String classes;//班级
	private int texttid;//教师编号
	private int pstextid;//已有分数试卷编号(同textid)
	private String pssid;//学号
	private String psrow;//正误
	private int grade;//分数
	//set、get方法
	public int getTextid() {
		return textid;
	}
	public void setTextid(int textid) {
		this.textid = textid;
	}
	public String getTextpidlist() {
		return textpidlist;
	}
	public void setTextpidlist(String textpidlist) {
		this.textpidlist = textpidlist;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public int getTexttid() {
		return texttid;
	}
	public void setTexttid(int texttid) {
		this.texttid = texttid;
	}
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
	public String getPsrow() {
		return psrow;
	}
	public void setPsrow(String psrow) {
		this.psrow = psrow;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
}
