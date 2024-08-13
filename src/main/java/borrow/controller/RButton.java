package borrow.controller;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import borrow.btn.Timertest;

import static bookIn.db.JdbcUtil.*;
import static borrow.btn.Overdue.*;

@WebServlet("/rbutton")
public class RButton extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      Connection con = null;
      PreparedStatement pstmt = null;

      String no = request.getParameter("no");
      System.out.println("no: "+no);
      con = getConnection();
      
      // 대출 여부 F(대출하지 않음)로 바꾸기
      String sql ="update SIGN set B_PASS='F' where NO=?";
      
      try {
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, no);
         
         int i = pstmt.executeUpdate();
         System.out.println("executeUpdate");
         
         if(i > 0) {
            
            // 반납 버튼을 누른 날짜 
            sql = "update SIGN set R_DATE = sysdate where NO = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,no);
            i = pstmt.executeUpdate();
            
            if(i > 0) {
               Date bDate = od(con,no);
               Date rDate = odr(con,no);
               
               System.out.println("bDate"+bDate);
               System.out.println("rDate"+rDate);

               System.out.println("b_date"+bDate.getTime());
               System.out.println("r_date"+rDate.getTime());
               
               
               long Sec = (rDate.getTime() - bDate.getTime());
               
               System.out.println("r_date.getTime()"+rDate.getTime());
               System.out.println("b_date.getTime()"+bDate.getTime());
               
               
               long Days = Sec / (24*60*60*1000); // 일자수
               System.out.println(Days);
               
               // 연체 여부 
               if(Days > 14) {
                  long sum = Days-14;
                  odB(con,no,sum);
               }else{
                  System.out.println("삭제");
                  sql = "DELETE SIGN WHERE NO = ? AND OVER = 0" ;
                  pstmt = con.prepareStatement(sql);
                  pstmt.setString(1,no);
                  pstmt.executeUpdate();
               }
            }
            
         }
         
         
         
         
      } catch (SQLException e) {
         e.printStackTrace();
      }
      
   }
       
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      doProcess(request,response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doProcess(request,response);
   }

}