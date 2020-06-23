package vo;

import java.util.List;

public class text {
	private int textid;//试卷编号
	private String textpid;//习题编号
	private String textclass;//选择班级
	private int texttid;//上传者id
	public int getTextid() {
		return textid;
	}
	public void setTextid(int textid) {
		this.textid = textid;
	}
	public String getTextpid() {
		return textpid;
	}
	public void setTextpid(String textpid) {
		this.textpid = textpid;
	}
	public String getTextclass() {
		return textclass;
	}
	public void setTextclass(String textclass) {
		this.textclass = textclass;
	}
	public int getTexttid() {
		return texttid;
	}
	public void setTexttid(int texttid) {
		this.texttid = texttid;
	}
	
}
