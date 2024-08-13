package borrow.vo;

public class BookVO {
	private String genre;
	private String title;
	private String autho;
	private String pub;
	private String no; // 등록번호
	private String bookno; // 청구기호
	private String b_pass;
	private String bsign; // 대출가능여부
	private String r_due;
	private String b_date;
	private String bookFile;
	private String over;

	
	public String getOver() {
		return over;
	}

	public void setOver(String over) {
		this.over = over;
	}

	public String getBookFile() {
		return bookFile;
	}

	public void setBookFile(String bookFile) {
		this.bookFile = bookFile;
	}

	public String getR_due() {
		return r_due;
	}

	public void setR_due(String r_due) {
		this.r_due = r_due;
	}

	public String getB_date() {
		return b_date;
	}

	public void setB_date(String b_date) {
		this.b_date = b_date;
	}

	public String getBsign() {
		return bsign;
	}

	public void setBsign(String bsign) {
		this.bsign = bsign;
	}

	public String getB_pass() {
		return b_pass;
	}

	public void setB_pass(String b_pass) {
		this.b_pass = b_pass;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAutho() {
		return autho;
	}

	public void setAutho(String autho) {
		this.autho = autho;
	}

	public String getPub() {
		return pub;
	}

	public void setPub(String pub) {
		this.pub = pub;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getBookno() {
		return bookno;
	}

	public void setBookno(String bookno) {
		this.bookno = bookno;
	}

}
