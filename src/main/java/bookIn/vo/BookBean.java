package bookIn.vo;

public class BookBean {
	private String genre;
	private String title;
	private String author;
	private String pub;
	private String no;
	private String bookno;
	private String bookfile;
	private String bsign;

	public String getBsign() {
		return bsign;
	}

	public void setBsign(String bsign) {
		this.bsign = bsign;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

	public String getBookfile() {
		return bookfile;
	}

	public void setBookfile(String bookfile) {
		this.bookfile = bookfile;
	}

	@Override
	public String toString() {
		return "BookBean [genre=" + genre + ", title=" + title + ", author=" + author + ", pub=" + pub + ", no=" + no
				+ ", bookno=" + bookno + ", bookfile=" + bookfile + ", bsign=" + bsign + "]";
	}
}
