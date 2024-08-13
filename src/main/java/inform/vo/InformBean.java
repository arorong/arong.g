package inform.vo;

import java.sql.Date;

public class InformBean {
	
	private int Rownum;
	private int inform_NUM;
	private String inform_PASS;
	private String inform_NM;
	private String inform_TITLE;
	private String inform_TEXTS;
	private String inform_FILES;
	private int RE_REF;
	private int RE_LEV;
	private int RE_SEQ;
	private int READCOUNT;
	private Date CREATE_DATE;
	private String inform_INF;
	

	public int getRownum() {
		return Rownum;
	}
	public void setRownum(int rownum) {
		Rownum = rownum;
	}
	public int getInform_NUM() {
		return inform_NUM;
	}
	public void setInform_NUM(int inform_NUM) {
		this.inform_NUM = inform_NUM;
	}
	public String getInform_PASS() {
		return inform_PASS;
	}
	public void setInform_PASS(String inform_ID) {
		this.inform_PASS = inform_ID;
	}
	public String getInform_NM() {
		return inform_NM;
	}
	public void setInform_NM(String inform_NM) {
		this.inform_NM = inform_NM;
	}
	public String getInform_TITLE() {
		return inform_TITLE;
	}
	public void setInform_TITLE(String inform_TITLE) {
		this.inform_TITLE = inform_TITLE;
	}
	public String getInform_TEXTS() {
		return inform_TEXTS;
	}
	public void setInform_TEXTS(String inform_TEXTS) {
		this.inform_TEXTS = inform_TEXTS;
	}
	public String getInform_FILES() {
		return inform_FILES;
	}
	public void setInform_FILES(String inform_FILES) {
		this.inform_FILES = inform_FILES;
	}
	public int getRE_REF() {
		return RE_REF;
	}
	public void setRE_REF(int rE_REF) {
		RE_REF = rE_REF;
	}
	public int getRE_LEV() {
		return RE_LEV;
	}
	public void setRE_LEV(int rE_LEV) {
		RE_LEV = rE_LEV;
	}
	public int getRE_SEQ() {
		return RE_SEQ;
	}
	public void setRE_SEQ(int rE_SEQ) {
		RE_SEQ = rE_SEQ;
	}
	public int getREADCOUNT() {
		return READCOUNT;
	}
	public void setREADCOUNT(int rEADCOUNT) {
		READCOUNT = rEADCOUNT;
	}
	public Date getCREATE_DATE() {
		return CREATE_DATE;
	}
	public void setCREATE_DATE(Date cREATE_DATE) {
		CREATE_DATE = cREATE_DATE;
	}
	public String getInform_INF() {
		return inform_INF;
	}
	public void setInform_INF(String string) {
		this.inform_INF = string;
	}
	@Override
	public String toString() {
		return "InformBean [Rownum=" + Rownum + ", inform_NUM=" + inform_NUM + ", inform_PASS=" + inform_PASS
				+ ", inform_NM=" + inform_NM + ", inform_TITLE=" + inform_TITLE + ", inform_TEXTS=" + inform_TEXTS
				+ ", inform_FILES=" + inform_FILES + ", RE_REF=" + RE_REF + ", RE_LEV=" + RE_LEV + ", RE_SEQ=" + RE_SEQ
				+ ", READCOUNT=" + READCOUNT + ", CREATE_DATE=" + CREATE_DATE + ", inform_INF=" + inform_INF + "]";
	}
	
		
}