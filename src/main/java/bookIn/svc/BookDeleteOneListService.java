package bookIn.svc;

import bookIn.dao.*;
import bookIn.vo.*;

public class BookDeleteOneListService {
	
	public BookBean bookDeleteOne(String no) {
		BookDAO dao = BookDAO.getInstance();
		BookBean book = dao.oneBook(no);
		return book;
	}
}