package bookIn.svc;

import bookIn.dao.*;
import bookIn.vo.*;

public class BookSelectOneListService {
	
	public BookBean bookSelectOne(String no) {
		BookDAO dao = BookDAO.getInstance();
		BookBean book =  dao.oneBook(no);
		return book;
	}
}

