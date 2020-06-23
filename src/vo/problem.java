package vo;

public class problem {
	private int pid;//题目编号
	private int tid;//发布者（教师编号）
	private String poblem;//题目
	private String optionA;//选项
	private String optionB;
	private String optionC;
	private String optionD;
	private String solution;//正确答案
	private String analyzing;//解析
	private String pclass;//班级

	//set、get方法
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getPoblem() {
		return poblem;
	}
	public void setPoblem(String poblem) {
		this.poblem = poblem;
	}
	public String getOptionA() {
		return optionA;
	}
	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}
	public String getOptionB() {
		return optionB;
	}
	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}
	public String getOptionC() {
		return optionC;
	}
	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}
	public String getOptionD() {
		return optionD;
	}
	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public String getAnalyzing() {
		return analyzing;
	}
	public void setAnalyzing(String analyzing) {
		this.analyzing = analyzing;
	}
	public String getPclass() {
		return pclass;
	}
	public void setPclass(String pclass) {
		this.pclass = pclass;
	}	
}
