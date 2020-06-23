package vo;

public class chat {
	private int chid;//id
	private String raise;//提疑
	private String answer;//答疑
	private int chcid;//班级id
	private int chtid;//教师id
	//set、get方法
	public int getChid() {
		return chid;
	}
	public void setChid(int chid) {
		this.chid = chid;
	}
	public String getRaise() {
		return raise;
	}	
	public void setRaise(String raise) {
		this.raise = raise;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getChcid() {
		return chcid;
	}
	public void setChcid(int chcid) {
		this.chcid = chcid;
	}
	public int getChtid() {
		return chtid;
	}
	public void setChtid(int chtid) {
		this.chtid = chtid;
	}
}
