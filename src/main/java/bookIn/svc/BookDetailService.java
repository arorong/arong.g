package bookIn.svc;

import static bookIn.db.JdbcUtil.*;
import java.sql.*;
import bookIn.vo.*;
import bookIn.dao.*;
import java.util.*;

public class BookDetailService {
	public BookBean getBookDetail(String bookNo) throws Exception {
		BookBean book = null;
		Connection con = null;
		try {
			con = getConnection();
			BookDAO bookDAO = BookDAO.getInstance(); // BookDAO의 인스턴스를 얻습니다.
			bookDAO.setConnection(con); // DAO에 데이터베이스 연결을 설정합니다.
			book = bookDAO.oneBook(bookNo); // DAO 메서드를 조정하여 단일 BookBean을 반환하도록 합니다.
		} catch (Exception e) {
			throw new Exception("도서 상세 정보를 가져오는 중 오류 발생", e); // 예외 발생 시 상세 정보 가져오기 오류를 던집니다.
		} finally {
			close(con); // 데이터베이스 연결을 안전하게 종료합니다.
		}
		return book; // 조회한 도서 정보를 반환합니다.
	}
}