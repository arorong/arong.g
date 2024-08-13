package inform.controller;

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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inform.action.Action;
import inform.action.InformDeleteProAction;
import inform.action.InformDeleteProAction2;
import inform.action.InformDetailAction;
import inform.action.InformDetailAction2;
import inform.action.InformList2Action;
import inform.action.InformListAction;
import inform.action.InformModifyFormAction;
import inform.action.InformModifyProAction;
import inform.action.InformReplyFormAction;
import inform.action.InformReplyProAction;
import inform.action.InformWrite2ProAction;
import inform.action.InformWriteProAction;
import inform.vo.ActionForward;

@WebServlet("*.bo")
public class InformFrontController extends javax.servlet.http.HttpServlet 
{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String RequestURI=request.getRequestURI();  
		//Http://localhost:8090/servletproject/informWriteForm
		String contextPath=request.getContextPath();
		String command=RequestURI.substring(contextPath.length());
		//contextPath길이만큼 잘라냄 
		///informWriteForm
		ActionForward forward=null;
		Action action=null;

		if(command.equals("/informWriteForm.bo")){ 
			forward=new ActionForward();
			forward.setPath("/inform/semi_inform_write.jsp");
			
		}else if(command.equals("/informWritePro.bo")){
			action  = new InformWriteProAction();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/informWriteForm2.bo")){ 
			forward=new ActionForward();
			forward.setPath("/inform/semi_inform_write2.jsp");
			
		}else if(command.equals("/informWrite2Pro.bo")){
			action  = new InformWrite2ProAction();
			try {
				forward=action.execute(request, response );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 공지사항
		else if(command.equals("/informList.bo")){
			action = new InformListAction();

			
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/informList1.bo")){
		action = new InformListAction();

			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		// 게시판
		else if(command.equals("/informList2.bo")){
			action = new InformList2Action();

			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/informList3.bo")){
			action = new InformList2Action();

			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/informsearch.bo")){
			action = new InformListAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		// 공지사항
		else if(command.equals("/informDetail.bo")){
			action = new InformDetailAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		// 게시판
		else if(command.equals("/informDetail1.bo")){
			action = new InformDetailAction2();

			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/informReplyForm.bo")){
			action = new InformReplyFormAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/informReplyPro.bo")){ 
			action = new InformReplyProAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/informModifyForm.bo")){
			action = new InformModifyFormAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/informModifyPro.bo")){
			action = new InformModifyProAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			// 공지사항 삭제
		}else if(command.equals("/informDeleteForm.bo")){
			String nowPage = request.getParameter("page");
			request.setAttribute("page", nowPage);
			int inform_num=Integer.parseInt(request.getParameter("inform_num"));
			request.setAttribute("inform_num",inform_num);
			forward=new ActionForward();
			forward.setPath("/inform/semi_inform_delete.jsp");
		}
		//게시판 삭제
		else if(command.equals("/informDeleteForm1.bo")){
			String nowPage = request.getParameter("page");
			request.setAttribute("page", nowPage);
			int inform_num=Integer.parseInt(request.getParameter("inform_num"));
			request.setAttribute("inform_num",inform_num);
			forward=new ActionForward();
			forward.setPath("/inform/semi_inform_delete2.jsp");
		}
		else if(command.equals("/informDeletePro.bo")){
			
			String nowPage = request.getParameter("page");
			request.setAttribute("page", nowPage);
			int inform_num=Integer.parseInt(request.getParameter("inform_num"));
			request.setAttribute("inform_num",inform_num);
			
			action = new InformDeleteProAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/informDeletePro2.bo")){
			
			String nowPage = request.getParameter("page");
			request.setAttribute("page", nowPage);
			int inform_num=Integer.parseInt(request.getParameter("inform_num"));
			request.setAttribute("inform_num",inform_num);
			
			action = new InformDeleteProAction2();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
		}
			
		}
		else {
			//파일 다운로드 하기
			 try {
		            // fileName 파라미터로 파일명을 가져온다.
		            String fileName = request.getParameter("fname");

		            // 파일이 실제 업로드 되어있는(파일이 존재하는) 경로를 지정한다.
		            String filePath = "C:\\jspwork\\semiproject\\src\\main\\webapp\\informUpload\\";

		            // 경로와 파일명으로 파일 객체를 생성한다.
		            File dFile = new File(filePath, fileName);

		            // 파일 길이를 가져온다.
		            int fSize = (int) dFile.length();

		            // 파일이 존재
		            if (fSize > 0) {

		                // 파일명을 URLEncoder 하여 attachment, Content-Disposition Header로 설정
		                String encodedFilename = "attachment; filename*=" + "UTF-8" + "''" + URLEncoder.encode(fileName, "UTF-8");

		                // ContentType 설정 (8비트로 된 데이터라는 의미임. 1바이트 단위의 데이터들을 핸들링하는 타입을 의미함.)
		                response.setContentType("application/octet-stream; charset=utf-8");

		                // Header 설정
		                response.setHeader("Content-Disposition", encodedFilename);

// ContentLength 설정
		                response.setContentLengthLong(fSize);

		                BufferedInputStream in = null;
		                BufferedOutputStream out = null;

		                in = new BufferedInputStream(new FileInputStream(dFile));
		                out = new BufferedOutputStream(response.getOutputStream());

		                try {
		                    byte[] buffer = new byte[4096];
		                    int bytesRead = 0;

		                    /*
		                    모두 현재 파일 포인터 위치를 기준으로 함 (파일 포인터 앞의 내용은 없는 것처럼 작동)
		                    int read() : 1byte씩 내용을 읽어 정수로 반환
		                    int read(byte[] b) : 파일 내용을 한번에 모두 읽어서 배열에 저장
		                    int read(byte[] b. int off, int len) : 'len'길이만큼만 읽어서 배열의 'off'번째 위치부터 저장
		                    */
		                    while ((bytesRead = in .read(buffer)) != -1) {
		                        out.write(buffer, 0, bytesRead);
		                    }

		                    // 버퍼에 남은 내용이 있다면, 모두 파일에 출력
		                    out.flush();
		                } finally {
		                    /*
		                    현재 열려 in,out 스트림을 닫음
		                    메모리 누수를 방지하고 다른 곳에서 리소스 사용이 가능하게 만듬
		                    */
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
		
		if(forward != null){
			
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			}else{
				RequestDispatcher dispatcher= request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
			
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doProcess(request,response);
	}  	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doProcess(request,response);
	}   
	
}