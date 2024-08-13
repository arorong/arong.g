package bookIn.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import bookIn.action.*;
import bookIn.vo.*;

@WebServlet("*.bok")
public class BookController extends javax.servlet.http.HttpServlet {

    protected void doProcess(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String RequestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = RequestURI.substring(contextPath.length());
        ActionForward forward = null;
        Action action = null;

        if (command.equals("/bookUpForm.bok")) {
            forward = new ActionForward();
            forward.setPath("/bookinsert/bookUpload.jsp");
            
        } else if (command.equals("/bookUpAc.bok")) { // 책 업로드
            action = new BookInsertAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (command.equals("/searchbook.bok")) { //책 검색 로직
            action = new BookSearchAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (command.equals("/searchbookAdmin.bok")) { //책 검색 로직
            action = new BookSearchAdminAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (command.equals("/bookListAdmin.bok")) {
            // 책 리스트(관리자 도서목록)
            action = new BookListAdminAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (command.equals("/bookListUser.bok")) { // 책 리스트(로그인 유저 및 사용자페이지 도서목록)
            action = new BookListUserAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (command.equals("/bookView.bok")) { // 책 상세보기
            action = new BookDetailAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (command.equals("/bookModifyForm.bok")) { // 책 정보 수정
            action = new BookModifyAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (command.equals("/bookModify.bok")) {
            action = new BookSelectOneListAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }

//        } else if (command.equals("/bookFileDel.bok")) { //파일 수정 및 삭제 관련
//            action = new BookFileAction();
//            try {
//                forward = action.execute(request, response);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

        } else if (command.equals("/bookDeleteForm.bok")) { // 책 삭제
            action = new BookDeleteAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (command.equals("/bookDelete.bok")) {
            action = new BookDeleteAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (command.equals("/search.bok")) { // 메인에서 책검색?
            action = new BookListAction1();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else { // 첨부 파일 관련
            try {
                String fileName = request.getParameter("fname");

                String filePath = "C:\\jspwork\\semiproject\\src\\main\\webapp\\bookcoverupload";
                File dFile = new File(filePath, fileName);
                int fSize = (int) dFile.length();
                if (fSize > 0) {
                    String encodedFilename = "attachment; filename*=" + "UTF-8" + "''" + URLEncoder.encode(fileName, "UTF-8");
                    response.setContentType("application/octet-stream; charset=utf-8");
                    response.setHeader("Content-Disposition", encodedFilename);

                    response.setContentLengthLong(fSize);

                    BufferedInputStream in = null;
                    BufferedOutputStream out = null;

                    in = new BufferedInputStream(new FileInputStream(dFile));
                    out = new BufferedOutputStream(response.getOutputStream());

                    try {
                        byte[] buffer = new byte[4096];
                        int bytesRead = 0;

                        while ((bytesRead = in.read(buffer)) != -1) {
                            out.write(buffer, 0, bytesRead);
                        }

                        out.flush();
                    } finally {
                        in.close();
                        out.close();
                    }
                } else {
                    throw new FileNotFoundException("파일이 없습니다.");
                }
            } catch (Exception e) {
                e.getMessage();
            }
        }

        if (forward != null) {

            if (forward.isRedirect()) {
                response.sendRedirect(forward.getPath());
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
                dispatcher.forward(request, response);
            }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doProcess(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doProcess(request, response);
    }

}