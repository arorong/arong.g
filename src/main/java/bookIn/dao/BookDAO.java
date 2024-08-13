package bookIn.dao;

import static bookIn.db.JdbcUtil.close;
import static bookIn.db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import bookIn.vo.BookBean;

public class BookDAO {
	private static BookDAO bookDAO;
	Connection con;
	DataSource ds;
	PreparedStatement pstmt;
	ResultSet rs;

	public static BookDAO getInstance() {
		if (bookDAO == null) {
			bookDAO = new BookDAO();
		}
		return bookDAO;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

   // 책 등록 갯수 구하기
   public int selectListCount() {
      int listCount = 0;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      con = getConnection();

      try {
         System.out.println("getConnection");
         pstmt = con.prepareStatement("select count(*) from CATALOGUE");
         rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("getListCount 에러: " + ex);
		} finally {
			try {
				if (rs != null) {
					close(rs);
				}
				if (pstmt != null) {
					close(pstmt);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return listCount;
	}

	// 책 등록
	public int BookInsert(BookBean bookIn) {
		pstmt = null;
		int insertCount = 0;
		con = getConnection();
		try {
			pstmt = con.prepareStatement(
					"INSERT INTO catalogue (GENRE, TITLE, AUTHOR, PUB, NO, BOOKNO, BOOKFILE) VALUES (?, ?, ?, ?, CATALOGUE_NO_SEQ.NEXTVAL, ?, ?)");

			pstmt.setString(1, bookIn.getGenre());
			pstmt.setString(2, bookIn.getTitle());
			pstmt.setString(3, bookIn.getAuthor());
			pstmt.setString(4, bookIn.getPub());
			pstmt.setString(5, bookIn.getBookno());
			pstmt.setString(6, bookIn.getBookfile());

			insertCount = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("도서 등록 실패 : " + e.getMessage());

		} finally {
			close(pstmt);
			close(con);
		}
		return insertCount;
	}

	// 책 검색
	public ArrayList<BookBean> bookSearch(BookBean bookSc) {
		ArrayList<BookBean> bookFind = new ArrayList<BookBean>();
		pstmt = null;
		rs = null;
		con = getConnection();

      try {
         String sql = "SELECT * FROM CATALOGUE WHERE ";

         if (bookSc != null) {
            if (bookSc.getTitle() != null && !bookSc.getTitle().isEmpty()) {
               sql += " TITLE LIKE '%" + bookSc.getTitle() + "%'";
               sql += " OR AUTHOR LIKE '%" + bookSc.getTitle() + "%'";
               sql += " OR PUB LIKE '%" + bookSc.getTitle() + "%'";
            }
         }
         System.out.println("sql: " + sql);
         pstmt = con.prepareStatement(sql);
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
			close(con);
		}
		return bookFind;
	}

	// 책목록 보기
	public ArrayList<BookBean> selectBookList(int page, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String catalogue_list_sql = "SELECT * FROM (SELECT ROWNUM rnum, A.* FROM (SELECT * FROM CATALOGUE ORDER BY NO ASC) A) WHERE rnum BETWEEN ? AND ?";
		ArrayList<BookBean> catalogueList = new ArrayList<BookBean>();
		int startrow = (page - 1) * 10 + 1; // 읽기 시작할 row 번호
		int endrow = startrow + limit - 1; // 페이지에 따른 종료 row 번호

		try {
			pstmt = con.prepareStatement(catalogue_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();

         while (rs.next()) {
            BookBean catalogue = new BookBean();
            catalogue.setGenre(rs.getString("genre"));
            catalogue.setTitle(rs.getString("title"));
            catalogue.setAuthor(rs.getString("author"));
            catalogue.setPub(rs.getString("pub"));
            catalogue.setNo(rs.getString("no"));
            catalogue.setBookno(rs.getString("bookno"));
            catalogue.setBookfile(rs.getString("bookfile"));
            catalogueList.add(catalogue);
         }
      } catch (Exception ex) {
         System.out.println("getBookList 에러: " + ex);
      } finally {
         close(rs);
         close(pstmt);
      }

		return catalogueList;
	}


	// 책 한줄 선택문
	public BookBean oneBook(String no) {
		BookBean book = null;

		String sql = "SELECT * FROM CATALOGUE WHERE NO=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				book = new BookBean();
				book.setGenre(rs.getString("genre"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPub(rs.getString("pub"));
				book.setNo(rs.getString("no"));
				book.setBookno(rs.getString("bookno"));
				book.setBookfile(rs.getString("bookfile"));
				book.setBsign(rs.getString("bsign"));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return book;
	}

	// 도서등록 수정
	public int updateBook(BookBean member) {
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "";
		con = getConnection();
		try {
			if (member.getBookfile() == null || member.getBookfile().equals("")) {
				sql = "UPDATE CATALOGUE SET GENRE=?, TITLE=?, AUTHOR=?, PUB=?, BOOKNO=? WHERE NO=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member.getGenre());
				pstmt.setString(2, member.getTitle());
				pstmt.setString(3, member.getAuthor());
				pstmt.setString(4, member.getPub());
				pstmt.setString(5, member.getBookno());
				pstmt.setString(6, member.getNo());

			} else {
				sql = "UPDATE CATALOGUE SET GENRE=?, TITLE=?, AUTHOR=?, PUB=?, BOOKNO=? , BOOKFILE=? WHERE NO=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member.getGenre());
				pstmt.setString(2, member.getTitle());
				pstmt.setString(3, member.getAuthor());
				pstmt.setString(4, member.getPub());
				pstmt.setString(5, member.getBookno());
				pstmt.setString(6, member.getBookfile());
				pstmt.setString(7, member.getNo());

			}
			updateCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("BookModify 에러 : " + ex);
		} finally {
			try {
				if (pstmt != null) {
					close(pstmt);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return updateCount;
	}

	// 도서등록 삭제
	public int deleteBook(String no) {
		String sql = "DELETE FROM CATALOGUE WHERE NO =?";
		int deleteCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, no);
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("BookDelete 에러 : " + ex);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return deleteCount;
	}

   public int selectBookList(BookBean bookBean) {
      // TODO Auto-generated method stub
      return 0;
   }
   
   
   public int selectListCounts(String search) { 
         int listCount= 0;
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         try{
            System.out.println("getConnection: "+con);
            pstmt=con.prepareStatement("select count(*) cnt from catagolue wheretitle like ? ");
            pstmt.setString(1, "%"+search+"%");
            rs = pstmt.executeQuery();
            if(rs.next()){
               listCount=rs.getInt("CNT");
            }
            
         }catch(Exception ex){
            System.out.println("getListCount 에러: " + ex);   
         }finally{
            close(rs);
            close(pstmt);
         }
         return listCount;
      }
   
   
   public ArrayList<BookBean> selectbooksearch(int page,int limit, String search){

         PreparedStatement pstmt = null;
         ResultSet rs = null;
         String sql="select * from (select rownum rnum, A.* from (select * from catalogue where title like '%'||?||'%' order by no desc ) A ) where rnum between ? and ?";
         ArrayList<BookBean> articleList = new ArrayList<BookBean>();
         int startrow=(page-1)*10+1; //읽기 시작할 row 번호..   
         int endrow=(page-1)*10+10;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, search);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BookBean book = new BookBean();
				book.setGenre(rs.getString("genre"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPub(rs.getString("pub"));
				book.setNo(rs.getString("no"));
				book.setBookno(rs.getString("bookno"));
				articleList.add(book);
			}
		} catch (Exception ex) {
			System.out.println("getbookList 에러ddd : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return articleList;
	}

	public String updateBookFile(String no, String BookFile) { // 파일 수정 위한 업데이트
		String df = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement("UPDATE CATALOGUE SET BOOKFILE = ? WHERE NO = ?");
			pstmt.setString(1, BookFile);
			pstmt.setString(2, no);
			rs = pstmt.executeQuery();

			int updateCount = 0;

			if (updateCount > 0) {
				System.out.println("파일 업데이트 성공!");
			} else {
				System.out.println("파일 업데이트 실패!");
			}

		} catch (Exception e) {
			System.out.println("파일 업데이트 에러 : " + e);
		} finally {
			close(rs);
			close(pstmt);
		}

		return df;
	}

	public String deleteBookFile(String no) { // 파일 삭제
		String df = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement("SELECT BOOKFILE FROM CALATLOGUE WHERE NO = ?");
			pstmt.setString(1, "no");
			rs = pstmt.executeQuery();

			if (rs.next())
				df = rs.getString("bookfile");

		} catch (Exception e) {
			System.out.println("파일 삭제 에러 : " + e);
		} finally {
			close(rs);
			close(pstmt);
		}

		return df;
	}
}