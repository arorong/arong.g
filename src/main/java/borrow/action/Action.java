package borrow.action;

import javax.servlet.http.*;

import borrow.vo.ActionForward;

public interface Action {
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception;
}
