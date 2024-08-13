package borrow.controller;

import java.io.IOException;
import static borrow.btn.Timertest.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import borrow.action.Action;
import borrow.action.BookListAction;
import borrow.action.BookReturnAction;
import borrow.action.BookSearchAction;
import borrow.action.BorrowDeletW;
import borrow.action.BorrowDetailAction;
import borrow.action.BorrowExtend;
import borrow.action.BorrowListAction;
import borrow.action.BorrowWaiting;
import borrow.action.DelayL;
import borrow.action.BookBorrowAction;
import borrow.action.BookDetailAction;
import borrow.vo.ActionForward;

@WebServlet("*.ao")
public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String RequestURI = request.getRequestURI(); // 자기 자신의 url정보 전체
		String contextPath = request.getContextPath(); // 자신의 상위폴더 까지의 경로
		String command = RequestURI.substring(contextPath.length()); // substring()으로 잘라내기
		ActionForward forward = null;
		Action action = null;
		HttpSession session = request.getSession();

		if (command.equals("/bookBorrowForm.ao")) {
			action = new BookBorrowAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/bookList.ao")) {
				action = new BookListAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		} else if (command.equals("/bookDetail.ao")) {
			action = new BookDetailAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		} else if (command.equals("/bookReturn.ao")) {
			action = new BookReturnAction();
			try {
				forward=action.execute(request, response);				
			}catch(Exception e){
				e.printStackTrace();
			}
		} else if(command.equals("/borrowList.ao")) {
			if(session.getAttribute("active") != null && session.getAttribute("active").equals("S") || session.getAttribute("active").equals("A")){
				action = new BorrowWaiting();
			}else {
				action = new BorrowListAction();
			}
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		} else if(command.equals("/borrowDetail.ao")) {
			action = new BorrowDetailAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		} else if(command.equals("/deleteWait.ao")) {
			
			action = new BorrowDeletW();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		} else if(command.equals("/borrowExtend.ao")) {
			action = new BorrowExtend();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		} else if(command.equals("/Timer.ao")) {
			
			getTimerTask();
			
		}else if(command.equals("/delayL.ao")) {
			action = new DelayL();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		} else if(command.equals("/bookSearch.ao")) {
			action = new BookSearchAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
		if (forward != null) { // forward 객체에 무엇이라도 담겨있으면 실행

			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath()); // 쿼리스트링 형식으로 데이터 전송.(url까지 변경)
			} else {
				// forward객체의 path에 담겨있는 경로를 가져와서 dispatcher 객체에 담기.(주소값)
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				// url을 변경하지 않고 페이지만 바뀐 상태로 값만 전달.
				dispatcher.forward(request, response);
			}

		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 받은 request와 response정보를 doProcess()메소드의 매개변수로 넣어 실행
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doProcess(request, response);
	}

}
