package borrow.dao;

//import static bookIn.db.JdbcUtil.close;
//import static bookIn.db.JdbcUtil.getConnection;
import static bookIn.db.JdbcUtil.*;


import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import bookIn.vo.BookBean;
import borrow.vo.BookVO;
import borrow.vo.DayVO;
import borrow.vo.SignVO;

public class BookDAO {
	DataSource ds;
	Connection con;
	private static BookDAO bookDAO;

	private BookDAO() {

	}

	public static BookDAO getInstance() {
		if (bookDAO == null) {
			bookDAO = new BookDAO();
		}
		return bookDAO;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public int borrowListCount() {

		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement("select count(*) from sign");

			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("getListCount 에러: " + ex);
		} finally {
			close(rs);
			close(pstmt);
//			close(con);
		}

		return listCount;

	}

	// 검색목록 갯수 구하기
	public int selectSearchCount(String word, String option) {

		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		word = "%" + word + "%";

		try {

			pstmt = con.prepareStatement("select count(*) from CATALOGUE where " + option + " like ?");
			pstmt.setString(1, word);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("getListCount 에러: " + ex);
		} finally {
			close(rs);
			close(pstmt);
//			close(con);
		}

		return listCount;

	}

	// 글의 개수 구하기.
	public int selectListCount() {

		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			pstmt = con.prepareStatement("select count(*) from CATALOGUE");

			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("getListCount 에러: " + ex);
		} finally {
			close(rs);
			close(pstmt);
//			close(con);
		}

		return listCount;

	}

	// 글 목록 보기.
	public ArrayList<BookVO> selectArticleList(int page, int limit) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String book_list_sql = "select * from (select rownum rnum, A.* from (select * from catalogue order by no desc ) A ) where rnum between ? and ?";
		ArrayList<BookVO> articleList = new ArrayList<BookVO>();
		int startrow = (page - 1) * 10 + 1; // 읽기 시작할 row 번호.. 11
		int endrow = (page - 1) * 10 + 10; // 20

		try {
			pstmt = con.prepareStatement(book_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BookVO bookVO = new BookVO();
				bookVO.setGenre(rs.getString("genre"));
				bookVO.setTitle(rs.getString("title"));
				bookVO.setAutho(rs.getString("author"));
				bookVO.setPub(rs.getString("pub"));
				bookVO.setNo(rs.getString("no"));
				bookVO.setBookno(rs.getString("bookno"));
				bookVO.setBsign(rs.getString("bsign"));
				bookVO.setBookFile(rs.getString("bookfile"));

				articleList.add(bookVO);
			}

		} catch (Exception ex) {
			System.out.println("getBookList 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
//			close(con);
		}

		return articleList;

	}

	public ArrayList<BookVO> selectBorrowList(int page, int limit) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String borrow_list_sql = "select * from sign where id = ?";
		ArrayList<BookVO> articleList = new ArrayList<BookVO>();
		SignVO signVO = new SignVO();
		int startrow = (page - 1) * 10 + 1; // 읽기 시작할 row 번호..
		int endrow = (page - 1) * 10 + 10;

		try {

		} catch (Exception ex) {
			System.out.println("getBookList 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
//			close(con);
		}

		return articleList;

	}

	public int insertBorrow(SignVO article) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//	         Date date = new Date();
//	         DateFormat format = new SimpleDateFormat("yyyyMMdd");
//	         String bDate = format.format(date);
//	         int day1 = 24 * 60 * 60;
		String sql = "";
		int insertCount = 1;
		int borrowCount = 0;
		// 추가
		String over = "";
		int max = 0;

		try {
			sql = "select * from sign where id = ? and b_pass not in ('F') and r_due < sysdate";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				insertCount = 3;
			}

			sql = "select * from sign where id = ? and b_pass not in('F','C')";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				borrowCount++;
			}
			if (borrowCount >= 4) {
				insertCount = 2;
			}

			if (insertCount == 1) {
				sql = "select * from catalogue where no = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, article.getNo());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					String bsign = rs.getString("bsign");
					if (bsign.equals("Y")) {
						bsign = "N";
						sql = "update catalogue set bsign = ? where no = ?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, bsign);
						pstmt.setString(2, article.getNo());
						pstmt.executeUpdate();
						commit(con);
					}
				}
			}



			if (borrowCount <= 3 && insertCount == 1) {

				sql = "insert into sign (no,id,b_pass,b_date,r_due,r_date) values(?,?,?,sysdate,sysdate+14,null)";

				pstmt = con.prepareStatement(sql);

				pstmt.setString(1, article.getNo());
				pstmt.setString(2, article.getId());
				pstmt.setString(3, article.getB_pass());

				insertCount = pstmt.executeUpdate();
				commit(con);

			}

		} catch (Exception ex) {
			System.out.println("boardInsert 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
//			close(con);
		}

		return insertCount;

	}

	// 책 보기.
	public BookVO selectBook(int book_no) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BookVO bookVO = null;
		
		try {
			pstmt = con.prepareStatement("select * from catalogue where no = ?");
			pstmt.setInt(1, book_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				
				bookVO = new BookVO();
				bookVO.setGenre(rs.getString("genre"));
				bookVO.setTitle(rs.getString("title"));
				bookVO.setAutho(rs.getString("author"));
				bookVO.setPub(rs.getString("pub"));
				bookVO.setNo(rs.getString("no"));
				bookVO.setBookno(rs.getString("bookno"));
				bookVO.setBsign(rs.getString("bsign"));

			}
		} catch (Exception ex) {
			System.out.println("getDetail 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
//			close(con);
		}

		return bookVO;

	}

	public ArrayList<BookVO> blist(HttpServletRequest request, HttpServletResponse response,int page, int limit) {
		ArrayList<BookVO> articleList = new ArrayList<BookVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		

		try {
			sql = "select * from (select rownum rnum, A.* from (select b.*,id,b_pass,b_date,r_due,r_date,over,extend,record from sign a, catalogue b  where a.id = ? and a.no = b.no  order by b_pass desc)A) where rnum between ? and ?  order by b_pass desc";
			
			int startrow = (page - 1) * 10 + 1; // 읽기 시작할 row 번호..
			int endrow = (page - 1) * 10 + 10;
		
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BookVO bookVO = new BookVO();
				bookVO.setGenre(rs.getString("genre"));
				bookVO.setTitle(rs.getString("title"));
				bookVO.setAutho(rs.getString("author"));
				bookVO.setPub(rs.getString("pub"));
				bookVO.setNo(rs.getString("no"));
				bookVO.setBookno(rs.getString("bookno"));
				bookVO.setB_pass(rs.getString("b_pass"));
				bookVO.setR_due(rs.getString("r_due"));
				bookVO.setB_date(rs.getString("b_date"));
				bookVO.setBookFile(rs.getString("bookFile"));
				bookVO.setOver(rs.getString("over"));
				articleList.add(bookVO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
//			close(con);
		}

		return articleList;

	}

	// 반납 대기중 리스트 수
	public int bookLoanCount() {

		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = con.prepareStatement("SELECT COUNT(*) FROM SIGN WHERE B_PASS in('W','R')");

			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("getListCount 에러: " + ex);
		} finally {
			close(rs);
			close(pstmt);
//			close(con);
		}

		return listCount;

	}

	// 대출 대기중인 리스트 가져오기
	public ArrayList<SignVO> getbookLoan(int page, int limit) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from (select rownum rnum, A.* from (select * from SIGN WHERE B_PASS in('W','R')) A ) where rnum between ? and ?";

		ArrayList<SignVO> articleList = new ArrayList<SignVO>();
		int startrow = (page - 1) * 10 + 1; // 읽기 시작할 row 번호..
		int endrow = (page - 1) * 10 + 10;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				SignVO signVO = new SignVO();
				signVO.setNo(rs.getString("no"));
				signVO.setId(rs.getString("id"));
				signVO.setB_pass(rs.getString("b_pass"));
				signVO.setB_date(rs.getString("b_date"));
				signVO.setR_due(rs.getString("r_due"));
				signVO.setExtend(rs.getString("extend"));
				articleList.add(signVO);
			}

		} catch (Exception ex) {
			System.out.println("getBookList 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
//			close(con);
		}

		return articleList;

	}

	// 대출 연장
	public DayVO bExtend(String id, String no) {
		
		DayVO dayVo = new DayVO();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;

		try {

			String sql = "select R_DUE from SIGN where id = ? and no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				
				// 연장 전 반납 예정일
				String dueDay = new SimpleDateFormat("yyyyMMdd").format(rs.getDate(1));
				
				// 오늘 날짜
				String today = new SimpleDateFormat("yyyyMMdd").format(new Date());

				// 연장 전 반납 예정일이 오늘 날짜보다 과거인 경우.
				if (Integer.parseInt(dueDay) < Integer.parseInt(today)) {
					i = 100;
					dayVo.setSuccess(i);
				} else {
					// 연장 전 반납 예정일이 오늘 날짜의 미래거나 현재인 경우.
					sql = "update SIGN set R_DUE = R_DUE+7, extend = 'T' where id = ? and no = ? and extend = 'F' ";

					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, id);
					pstmt.setString(2, no);
					i = pstmt.executeUpdate();
					commit(con);
					
					dayVo.setSuccess(i);

					// 연장 후 반납 예정일
					if (i > 0) {
						sql = "select R_DUE from SIGN where id = ? and no = ? ";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, id);
						pstmt.setString(2, no);
						rs = pstmt.executeQuery();

						if (rs.next()) {
							dayVo.setDay(rs.getString(1));
						}
					}
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
//			close(con);
		}
		return dayVo;

	}

	public int delayListCount() {

		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = con.prepareStatement("SELECT COUNT(*) FROM SIGN WHERE OVER > 0 OR R_DUE < SYSDATE");

			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("getListCount 에러: " + ex);
		} finally {
			close(rs);
			close(pstmt);
//			close(con);
		}

		return listCount;

	}

	public ArrayList<SignVO> delayListImport(int page, int limit) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from (select rownum rnum, A.* from (select * from SIGN WHERE (OVER > 0 OR R_DUE < SYSDATE) and b_pass not in('F')) A order by id ) where rnum between ? and ?";

		ArrayList<SignVO> articleList = new ArrayList<SignVO>();
		int startrow = (page - 1) * 10 + 1; // 읽기 시작할 row 번호..
		int endrow = (page - 1) * 10 + 10;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				SignVO signVO = new SignVO();
				signVO.setNo(rs.getString("no"));
				signVO.setId(rs.getString("id"));
				signVO.setB_pass(rs.getString("b_pass"));
				signVO.setB_date(rs.getString("b_date"));
				signVO.setR_due(rs.getString("r_due"));
				signVO.setExtend(rs.getString("extend"));
				signVO.setOver(rs.getString("over"));
				signVO.setR_date(rs.getString("r_date"));
				articleList.add(signVO);
			}

		} catch (Exception ex) {
			System.out.println("getBookList 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
//			close(con);
		}

		return articleList;

	}

	public String over(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		String over = "";
		try {
			sql = "select * from sign where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				over = rs.getString("over");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
//			close(con);
		}

		return over;

	}

	public ArrayList<BookBean> bookSearch(String word, String option, int page, int limit) {
		ArrayList<BookBean> bookFind = new ArrayList<BookBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = getConnection();
		int startrow = (page - 1) * 10 + 1; // 읽기 시작할 row 번호.. 11
		int endrow = (page - 1) * 10 + 10; // 20

		word = "%" + word + "%";
		try {
			String sql = "select * from (select rownum rnum, A.* from (select * from catalogue where " + option
					+ " like ? order by no desc ) A ) where rnum between ? and ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, word);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BookBean bookli = new BookBean();

				bookli.setGenre(rs.getString("genre"));
				bookli.setTitle(rs.getString("title"));
				bookli.setAuthor(rs.getString("author"));
				bookli.setPub(rs.getString("pub"));
				bookli.setNo(rs.getString("no"));
				bookli.setBookno(rs.getString("bookno"));
				bookli.setBookfile(rs.getString("bookfile"));

				

				bookFind.add(bookli);
			}

		} catch (SQLException e) {
			System.out.println("에러 : " + e);

		} finally {
			close(rs);
			close(pstmt);
//			close(con);
		}
		return bookFind;
	}

}
