package login.dao;

import static bookIn.db.JdbcUtil.close;
import static bookIn.db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//import bookIn.vo.BookBean;
import bookIn.db.JdbcUtil;
import login.vo.MemberBean;

public class MemberDAO {
	public static MemberDAO instance;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;

	public static MemberDAO getInstance(){
		if(instance == null){
			instance = new MemberDAO();
		}
		return instance;
	}
	public void setConnection(Connection con){
		this.con = con;
	}
	
	//회원로그인
	public String selectLoginId(MemberBean member){
		String loginId = null;
		String active = null;
		String sql="SELECT * FROM MEMBER WHERE ID=? AND PW=? AND ACTIVE in('Y','S','A')";
		
		try{
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				loginId = rs.getString("Id");
				active = rs.getString("active");
			}
		}catch(Exception ex){
			System.out.println(" 에러: " + ex);			
		}finally{
			close(rs);
			close(pstmt);
		}
		
		return active;
	}
	//회원로그인 - 아이디 중복확인 AJAX
    public MemberDAO() {
    try {
            Context initCtx = new InitialContext();
                    Context envCtx = (Context)initCtx.lookup("java:comp/env");
                    ds = (DataSource)envCtx.lookup("jdbc/OracleDB");
    } catch (NamingException e) {
        e.printStackTrace();
    }

    }
	public boolean idCheck(MemberBean mv) {
	    if(mv.getId() == null || mv.getId().length() == 0) {
	            System.out.println("idCheck 아이디가 없습니다.");
	    }
	    boolean idchk = false;
	    String SQL = "select count(*) as result from member where id = ? ";
	
	    try {
	            con = ds.getConnection();
	        pstmt = con.prepareStatement(SQL);
	        pstmt.setString(1, mv.getId());
	
	        ResultSet rs = pstmt.executeQuery();
	
	        while(rs.next()) {
	                int result = rs.getInt(1);
	                if(result > 0) idchk = true;
	        }
	        return idchk;
	
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }finally{
			close(pstmt);
			close(con);
		}
	    return idchk;
	}
	
	public boolean phCheck(MemberBean mv) {
		if(mv.getPh() == null || mv.getPh().length() == 0) {
			System.out.println("phCheck 아이디가 없습니다.");
		}
		boolean phchk = false;
		String SQL = "select count(*) as result from member where ph = ? ";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, mv.getPh());
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int result = rs.getInt(1);
				if(result > 0) phchk = true;
			}
			return phchk;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
			close(con);
		}
		return phchk;
	}

	
	
	//회원가입
	public int insertMember(MemberBean member){
		String sql="INSERT INTO MEMBER VALUES (?,?,?,?,?,?,?,?,?,'Y',SEQ_MEMBER_UNM.NEXTVAL)";
		int insertCount=0;
		
		try{
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			pstmt.setString(3, member.getNm());
			pstmt.setString(4, member.getBirth());
			pstmt.setString(5, member.getPh());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getAddr());
			pstmt.setString(8, member.getPostcode());
			pstmt.setString(9, member.getAddr1());
			
			insertCount=pstmt.executeUpdate();
			
		}catch(Exception ex){
			System.out.println("joinMember 에러: " + ex);			
		}finally{
			close(pstmt);
		}
		return insertCount;
	}
	
	//회원정보수정 - 어드민
	public int modifyMember(MemberBean member){
		String sql="UPDATE MEMBER SET PW=?,NM=?,BIRTH=?,PH=?,EMAIL=?,ADDR=?,POSTCODE=?,ADDR1=?, ACTIVE=?  WHERE ID = ?";
		int modifyCount=0;
		
		try{
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, member.getPw());
			pstmt.setString(2, member.getNm());
			pstmt.setString(3, member.getBirth());
			pstmt.setString(4, member.getPh());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getAddr());
			pstmt.setString(7, member.getPostcode());
			pstmt.setString(8, member.getAddr1());
			pstmt.setString(9, member.getActive());
			pstmt.setString(10, member.getId());
			
			modifyCount=pstmt.executeUpdate();
			
		}catch(Exception ex){
			System.out.println("modifyMember 에러: " + ex);			
		}finally{
			close(pstmt);
		}
		return modifyCount;
	}
	
	//회원정보수정 - 유저
		public int modifyMemberUser(MemberBean member){
			String sql="UPDATE MEMBER SET PW=?,NM=?,BIRTH=?,PH=?,EMAIL=?,ADDR=?,POSTCODE=?,ADDR1=? WHERE ID = ?";
			int modifyCount=0;
			
			try{
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, member.getPw());
				pstmt.setString(2, member.getNm());
				pstmt.setString(3, member.getBirth());
				pstmt.setString(4, member.getPh());
				pstmt.setString(5, member.getEmail());
				pstmt.setString(6, member.getAddr());
				pstmt.setString(7, member.getPostcode());
				pstmt.setString(8, member.getAddr1());
				pstmt.setString(9, member.getId());
				
				modifyCount=pstmt.executeUpdate();
				
			}catch(Exception ex){
				System.out.println("modifyMember 에러: " + ex);			
			}finally{
				close(pstmt);
			}
			return modifyCount;
		}
	
	//회원탈퇴(관리자, 사용자 동일)
	public int deleteMember(String id){
		String sql="UPDATE MEMBER SET ACTIVE='N' WHERE ID = ?";
		int deleteCount = 0;
		
		try{
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			deleteCount = pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("deleteMember 에러: " + ex);	
		}finally{
			close(pstmt);
		}
		
		return deleteCount;
	}

	//로그인시 - 전체 리스트 가져오기
	public ArrayList<MemberBean> selectMemberList(){
		String sql="SELECT * FROM MEMBER";
		ArrayList<MemberBean> memberList=new ArrayList<MemberBean>();
		MemberBean mb = null;
		try{
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				do{
				mb=new MemberBean();
				mb.setId(rs.getString("id"));
				mb.setPw(rs.getString("pw"));
				mb.setNm(rs.getString("nm"));
				mb.setBirth(rs.getString("birth"));
				mb.setPh(rs.getString("ph"));
				mb.setEmail(rs.getString("email"));
				mb.setAddr(rs.getString("addr"));
				mb.setPostcode(rs.getString("postcode"));
				mb.setAddr1(rs.getString("addr1"));
				mb.setActive(rs.getString("active"));
				memberList.add(mb);
				}while(rs.next());
			}
		}catch(Exception ex){
			System.out.println("getDetailMember 에러: " + ex);			
		}finally{
			close(rs);
			close(pstmt);
		}
		return memberList;
	}
	
	
	// 유저 검색 - 전체 리스트 가져오기
	public ArrayList<MemberBean> selectMemberList(int page, int limit){
	      
	      String sql="select * from (select rownum rnum, A.* from (select * from member order by unm asc) A ) where rnum between ? and ?";
	      ArrayList<MemberBean> memberList=new ArrayList<MemberBean>();
	      MemberBean mb = null;
	      int startrow = (page - 1) * 10 + 1; // 읽기 시작할 row 번호..
	      int endrow = (page - 1) * 10 + 10;
	      try{
	         
	         pstmt=con.prepareStatement(sql);
	         pstmt.setInt(1, startrow);
	         pstmt.setInt(2, endrow);
	         rs=pstmt.executeQuery();
	                  
	         if(rs.next()){
	            do{
	            mb=new MemberBean();
	            mb.setId(rs.getString("id"));
	            mb.setPw(rs.getString("pw"));
	            mb.setNm(rs.getString("nm"));
	            mb.setBirth(rs.getString("birth"));
	            mb.setPh(rs.getString("ph"));
	            mb.setEmail(rs.getString("email"));
	            mb.setAddr(rs.getString("addr"));
	            mb.setPostcode(rs.getString("postcode"));
	            mb.setAddr1(rs.getString("addr1"));
	            mb.setActive(rs.getString("active"));
	            memberList.add(mb);
	            }while(rs.next());
	         }
	      }catch(Exception ex){
	         System.out.println("getDeatilMember 에러: " + ex);         
	      }finally{
	         close(rs);
	         close(pstmt);
	      }
	      return memberList;
	   }
	
	
	////////////////////특정 아이디 회원정보 가져오기
	public MemberBean selectMember(String id){
		String sql="SELECT * FROM MEMBER WHERE ID=?";
		MemberBean mb = null;
		try{
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				mb=new MemberBean();
				mb.setId(rs.getString("id"));
				mb.setPw(rs.getString("pw"));
				mb.setNm(rs.getString("nm"));
				mb.setBirth(rs.getString("birth"));
				mb.setPh(rs.getString("ph"));
				mb.setEmail(rs.getString("email"));
				mb.setAddr(rs.getString("addr"));
				mb.setPostcode(rs.getString("postcode"));
				mb.setAddr1(rs.getString("addr1"));
				mb.setActive(rs.getString("active"));
				mb.setUnm(rs.getString("unm"));
			}
		}catch(Exception ex){
			System.out.println("getDetailMember 에러: " + ex);			
		}finally{
			close(rs);
			close(pstmt);
		}
		
		return mb;
	}
	
	
	public String findId(String nm, String ph){ //	id찾기
		String id = null;	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MemberBean mb = null;
		try{
			con = JdbcUtil.getConnection();
			pstmt=con.prepareStatement("select id from member where nm=? and ph=?");
			pstmt.setString(1, nm);
			pstmt.setString(2, ph);

			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				mb = new MemberBean();
				mb.setId(rs.getString("id"));
			}
				
			id = mb.getId();
			
		}catch(Exception ex){
			System.out.println("id찾기 에러: " + ex);			
		}finally{
			close(rs);
			close(pstmt);
		}
		
		return id;
	}
	

	public boolean userCheck(String id, String nm, String ph) {	 //비밀번호 찾기
	if(id == null || id.length() == 0) {
    	System.out.println("가입하신 아이디를 입력하세요");
	}else if(nm == null || nm.length() == 0) {
		System.out.println("가입하신 이름을 입력하세요");
	}else if(ph == null || ph.length() == 0) {
		System.out.println("가입하신 번호를 입력하세요");
	}
	boolean userChk = false;
    String SQL = "select count(*) from member where id=? and nm=? and ph=?";

    try {
        con = ds.getConnection();
        pstmt = con.prepareStatement(SQL);
        pstmt.setString(1, id);
        pstmt.setString(2, nm);
        pstmt.setString(3, ph);

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()) {
                int result = rs.getInt(1); //결과 값
                if(result > 0) {
                	userChk = true;
                }
        }       
    } catch (SQLException e) {
        e.printStackTrace();
    }finally{
		close(pstmt);
		close(con);
	}
    return userChk;
	}
	
	
	public int modifyPw(String id, String pw){ //비밀번호찾기-비밀번호 재설정
		String sql="UPDATE MEMBER SET PW=? WHERE ID = ?";
		int modifyCount=0;
		boolean modifyChk = false;
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2, id);
			
			modifyCount=pstmt.executeUpdate();
			
			if(modifyCount>0) {
                   	modifyChk = true;
            }  
			else{System.out.println("업데이트 실패했습니다.");}  
		}catch(Exception ex){
			System.out.println("modifyPw 에러: " + ex);			
		}finally{
			close(pstmt);
			close(con);
		}
		return modifyCount;
	}
	
	//어드민-회원관리 전체 리스트 가져오기
    public int selectListCount(){
    int listCount = 0;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    con = getConnection();

    try {
       pstmt = con.prepareStatement("SELECT count(*) FROM MEMBER");
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
          if (con != null) {
              close(con);
           }
       } catch (Exception ex) {
          ex.printStackTrace();
       }
    }
    return listCount;
 }
    
 // 유저 조회
    public ArrayList<MemberBean> MemberSearch(String answer, String whr) {
       PreparedStatement pstmt = null;
       ResultSet rs = null;
       con = getConnection();
       ArrayList<MemberBean> memberList = new ArrayList<MemberBean>();
       try {
          pstmt = con.prepareStatement("SELECT * FROM member WHERE "+ whr +" LIKE ?");
          pstmt.setString(1, "%" + answer + "%");
          rs = pstmt.executeQuery();
          
          while (rs.next()) {
             MemberBean memb = new MemberBean();
             // 객체에 데이터 설정
             memb.setId(rs.getString("id"));
             memb.setUnm(rs.getString("unm"));
             memb.setNm(rs.getString("nm"));
             memb.setPh(rs.getString("ph"));
             memb.setActive(rs.getString("active"));
             memberList.add(memb);
          }
       } catch (Exception e) {
          e.printStackTrace();
       } finally {
          close(rs);
          close(pstmt);
          close(con);
       }
       return memberList;
    }
    
  //검색된 리스트 수
    public int selectSearchCount(String search,String option){
    	int listCount = 0;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	con = getConnection();
    	
    	try {
    		pstmt = con.prepareStatement("SELECT count(*) FROM MEMBER where " + option + "like '%?%'");
    		pstmt.setString(1, search);
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
    			if (con != null) {
    				close(con);
    			}
    		} catch (Exception ex) {
    			ex.printStackTrace();
    		}
    	}
    	return listCount;
    }
	
}