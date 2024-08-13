package borrow.btn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import static bookIn.db.JdbcUtil.*;

public class Overdue {

//책의 반납 예정일 갖고오기
   public static Date od(Connection con,String no) {
      Date bDate = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      String sql ="select R_DUE from SIGN where no=?";
      
      try {
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, no);
         rs = pstmt.executeQuery();
 
         if(rs.next()) {
            bDate = rs.getDate(1);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      finally {
    	  close(rs);
    	  close(pstmt);

      }
      
      return bDate;
      
   }
   
   public static Date odr(Connection con,String no) {
      Date rDate=null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      String sql ="select R_DATE from SIGN where no=?";
      
      try {
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, no);
         rs = pstmt.executeQuery();
         
         if(rs.next()) {
            rDate = rs.getDate("R_DATE");
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      finally {
    	  close(pstmt);
      }

      return rDate;
      
   }
   
   public static int odB(Connection con,String no,long sum) {
      
      PreparedStatement pstmt = null;
      int r = 0;
      String sql ="update SIGN set over = ? where no = ?";
      
      try {
         pstmt = con.prepareStatement(sql);
         pstmt.setLong(1, sum);
         pstmt.setString(2, no);
         r = pstmt.executeUpdate();
         con.commit();

      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
    	  close(pstmt);
      }
      return r;
   }
}