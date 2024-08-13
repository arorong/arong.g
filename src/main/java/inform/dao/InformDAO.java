package inform.dao;

import static bookIn.db.JdbcUtil.getConnection;
import static bookIn.db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import inform.vo.InformBean;
import oracle.net.aso.e;

public class InformDAO {

	DataSource ds;
	Connection con;
	private static InformDAO informDAO;

	private InformDAO() {
	}

	public static InformDAO getInstance(){
		if(informDAO == null){
			informDAO = new InformDAO();
		}
		return informDAO;
	}

	public void setConnection(Connection con){
		this.con = con;
	}

	//글의 개수 구하기.
	public int selectListCount() {
		int listCount= 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt=con.prepareStatement("select count(*) from inform where inform_INF=0");
			rs = pstmt.executeQuery();
			if(rs.next()){
				listCount=rs.getInt(1);
			}
		}catch(Exception ex){
			System.out.println("getListCount 에러: " + ex);			
		}finally{
			close(rs);
			close(pstmt);
		}
		return listCount;
	}
	
	//게시판 글 개수 구하기
	public int selectListCount1() {
		int listCount= 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt=con.prepareStatement("select count(*) from inform where inform_INF=1");
			rs = pstmt.executeQuery();
			if(rs.next()){
				listCount=rs.getInt(1);
			}
		}catch(Exception ex){
			System.out.println("getListCount 에러: " + ex);			
		}finally{
			close(rs);
			close(pstmt);
		}
		return listCount;
	}
	// 공지사항 검색
	public int selectListCounts(String search) {
		int listCount= 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt=con.prepareStatement("select count(*) cnt from inform where inform_inf = 0 and inform_title like ? ");
			pstmt.setString(1, "%"+search+"%");
			rs = pstmt.executeQuery();
			if(rs.next()){
				listCount=rs.getInt("CNT");
			}
			
		}catch(Exception ex){
			System.out.println("getListCount 에러: " + ex);	
		}finally{
			close(rs);
			close(pstmt);
		}
		return listCount;
	}
	// 게시판 검색
	public int selectListCount1(String search) {
		int listCount= 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt=con.prepareStatement("select count(*) cnt from inform where inform_inf = 1 and inform_title like ?");
			pstmt.setString(1, "%"+search+"%");
			rs = pstmt.executeQuery();
			if(rs.next()){
				listCount=rs.getInt("CNT");
			}
			
		}catch(Exception ex){
			System.out.println("getListCount 에러: " + ex);	
		}finally{
			close(rs);
			close(pstmt);
		}
		return listCount;
	}

	//공지사항 목록 보기.	
	public ArrayList<InformBean> selectarticleList(int page,int limit){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String inform_list_sql="select * from (select rownum rnum, A.* from (select * from inform  where inform_inf = 0 order by inform_NUM desc ) A ) where rnum between ? and ?";
//			String inform_list_sql="select * from (select rownum rnum, A.* from (select * from inform order by inform_RE_SEQ desc ,inform_NUM desc ) A ) where rnum between ? and ?";
		ArrayList<InformBean> articleList = new ArrayList<InformBean>();
		int startrow=(page-1)*10+1; //읽기 시작할 row 번호..	
		int endrow=(page-1)*10+10;
		

		try{
			pstmt = con.prepareStatement(inform_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();

			while(rs.next()){
				InformBean inform = new InformBean();
				inform.setInform_NUM(rs.getInt("inform_NUM"));
				inform.setInform_PASS(rs.getString("inform_PASS"));
				inform.setInform_NM(rs.getString("inform_NM"));
				inform.setInform_TITLE(rs.getString("inform_TITLE"));
				inform.setInform_TEXTS(rs.getString("inform_TEXTS"));
				inform.setInform_FILES(rs.getString("inform_FILES"));
				inform.setRE_REF(rs.getInt("RE_REF"));
				inform.setRE_LEV(rs.getInt("RE_LEV"));
				inform.setRE_SEQ(rs.getInt("RE_SEQ"));
				inform.setREADCOUNT(rs.getInt("READCOUNT"));
				inform.setCREATE_DATE(rs.getDate("CREATE_DATE"));
				articleList.add(inform);

			}

		}catch(Exception ex){
			System.out.println("getinformList1 에러 : " + ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return articleList;

	}

		
	//게시판  목록 보기.	
	public ArrayList<InformBean> selectArticleList2(int page,int limit){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String inform_list_sql="select * from (select rownum rnum, A.* from (select * from inform  where inform_inf = 1 order by inform_NUM desc ) A ) where rnum between ? and ?";
//			String inform_list_sql="select * from (select rownum rnum, A.* from (select * from inform order by inform_RE_SEQ desc ,inform_NUM desc ) A ) where rnum between ? and ?";
		ArrayList<InformBean> articleList = new ArrayList<InformBean>();
		int startrow=(page-1)*10+1; //읽기 시작할 row 번호..	
		int endrow=(page-1)*10+10;
		

		try{
			pstmt = con.prepareStatement(inform_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();

			while(rs.next()){
				InformBean inform = new InformBean();
				inform.setInform_NUM(rs.getInt("inform_NUM"));
				inform.setInform_PASS(rs.getString("inform_PASS"));
				inform.setInform_NM(rs.getString("inform_NM"));
				inform.setInform_TITLE(rs.getString("inform_TITLE"));
				inform.setInform_TEXTS(rs.getString("inform_TEXTS"));
				inform.setInform_FILES(rs.getString("inform_FILES"));
				inform.setRE_REF(rs.getInt("RE_REF"));
				inform.setRE_LEV(rs.getInt("RE_LEV"));
				inform.setRE_SEQ(rs.getInt("RE_SEQ"));
				inform.setREADCOUNT(rs.getInt("READCOUNT"));
				inform.setCREATE_DATE(rs.getDate("CREATE_DATE"));
				articleList.add(inform);
				
			}

		}catch(Exception ex){
			System.out.println("getinformList1 에러 : " + ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return articleList;

	}

	//글 내용 보기.
	public InformBean selectArticle(int inform_num){
		InformBean inform = null;
		
		con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from inform where inform_num =?";

		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, inform_num);
			rs= pstmt.executeQuery();

			if(rs.next()){

				inform  = new InformBean();
				inform.setInform_NUM(rs.getInt("inform_NUM"));
				inform.setInform_PASS(rs.getString("inform_PASS"));
				inform.setInform_NM(rs.getString("inform_NM"));
				inform.setInform_TITLE(rs.getString("inform_TITLE"));
				inform.setInform_TEXTS(rs.getString("inform_TEXTS"));
				inform.setInform_FILES(rs.getString("inform_FILES"));
				inform.setREADCOUNT(rs.getInt("READCOUNT"));
				inform.setCREATE_DATE(rs.getDate("CREATE_DATE"));
				

			}
		}catch(Exception ex){
			System.out.println("getDetail 에러 : " + ex);
		}finally{
			
			close(rs);
			close(pstmt);
		}

		return inform;

	}

	//공지사항 글 등록.
	@SuppressWarnings("resource")
	public int insertArticle(InformBean article){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num =0;
		String sql="";
		int insertCount=0;

		try{
			pstmt=con.prepareStatement("select max(inform_num) from inform");
			rs = pstmt.executeQuery();

			if(rs.next())
				num =rs.getInt(1)+1;  
			//글이 등록되어 있으면 글번호 +1
			else
				num=1;
			//아니면 글번호 1로 출력

			sql="insert into inform (inform_num , inform_TITLE, inform_TEXTS,";
			sql+="inform_FILES "+
					", READCOUNT,"+
					"CREATE_DATE) values(?,?,?,?,?,SYSDATE)";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, article.getInform_TITLE());
			pstmt.setString(3, article.getInform_TEXTS());
			pstmt.setString(4, article.getInform_FILES());
			pstmt.setInt(5, 0);
   
			insertCount=pstmt.executeUpdate();

		}catch(Exception ex){
			System.out.println("informInsert 에러 : "+ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return insertCount;

	}
	
	//게시판 글 등록.
		@SuppressWarnings("resource")
		public int insertArticle1(InformBean article){

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int num =0;
			String sql="";
			int insertCount=0;

			try{
				pstmt=con.prepareStatement("select max(inform_num) from inform where INFORM_INF = 1 ");
				rs = pstmt.executeQuery();

				if(rs.next())
					num =rs.getInt(1)+1;  
				//글이 등록되어 있으면 글번호 +1
				else
					num=1;
				//아니면 글번호 1로 출력

				sql="insert into inform (inform_num, inform_PASS, inform_NM, inform_TITLE, inform_TEXTS,";
				sql+="inform_FILES, RE_REF,"+
						"RE_LEV, RE_SEQ, READCOUNT,"+
						"CREATE_DATE, INFORM_INF) values(?,?,?,?,?,?,?,?,?,?,SYSDATE,?)";

				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.setString(2, article.getInform_PASS());
				pstmt.setString(3, article.getInform_NM());
				pstmt.setString(4, article.getInform_TITLE());
				pstmt.setString(5, article.getInform_TEXTS());
				pstmt.setString(6, article.getInform_FILES());
				pstmt.setInt(7, num);
				pstmt.setInt(8, 0);
				pstmt.setInt(9, 0);
				pstmt.setInt(10, 0);
				pstmt.setInt(11, 1);
				
				

				insertCount=pstmt.executeUpdate();

			}catch(Exception ex){
				System.out.println("informInsert 에러 : "+ex);
			}finally{
				close(rs);
				close(pstmt);
			}

			return insertCount;
		}


	//글 수정.
	public int updateArticle(InformBean article){

		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql="update inform set inform_TITLE=?,inform_TEXTS=? where inform_NUM=?";

		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getInform_TITLE());
			pstmt.setString(2, article.getInform_TEXTS());
			pstmt.setInt(3, article.getInform_NUM());
			updateCount = pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("informModify 에러 : " + ex);
		}finally{
			close(pstmt);
		}

		return updateCount;

	}

	//글 삭제.
	public int deleteArticle(int inform_num){

		PreparedStatement pstmt = null;
		String inform_delete_sql="delete from inform where inform_num=?";
		int deleteCount=0;

		try{
			pstmt=con.prepareStatement(inform_delete_sql);
			pstmt.setInt(1, inform_num);
			deleteCount=pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("informDelete 에러 : "+ex);
		}	finally{
			close(pstmt);
		}

		return deleteCount;

	}

	//조회수 업데이트.
	public int updateReadCount(int inform_num){

		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql="update inform set READCOUNT = "+
				"READCOUNT+1 where inform_NUM = "+inform_num;

		try{
			pstmt=con.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		}catch(SQLException ex){
			System.out.println("setReadCountUpdate 에러 : "+ex);
		}
		finally{
			close(pstmt);

		}

		return updateCount;

	}

	//글쓴이인지 확인.
	public boolean isArticleinformWriter(int inform_num){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String inform_sql="select * from inform where inform_NUM=?";
		boolean isWriter = false;

		try{
			pstmt=con.prepareStatement(inform_sql);
			pstmt.setInt(1, inform_num);
			rs=pstmt.executeQuery();
			rs.next();

			isWriter = true;
		}catch(SQLException ex){
			System.out.println("isinformWriter 에러 : "+ex);
		}
		finally{
			close(pstmt);
		}

		return isWriter;

	}
	
	//공지사항 검색
	public ArrayList<InformBean> selectArticlesearch(int page,int limit, String search){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String inform_list_sql="select * from (select rownum rnum, A.* from (select * from inform where inform_TITLE like '%'||?||'%' and inform_inf = 0 order by inform_NUM desc ) A ) where rnum between ? and ?";
		ArrayList<InformBean> articleList = new ArrayList<InformBean>();
		int startrow=(page-1)*10+1; //읽기 시작할 row 번호..	
		int endrow=(page-1)*10+10;

		try{
			pstmt = con.prepareStatement(inform_list_sql);
			pstmt.setString(1, search);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();

			while(rs.next()){
				InformBean inform = new InformBean();
				inform.setRownum(rs.getInt("rnum"));
				inform.setInform_NUM(rs.getInt("inform_NUM"));
				inform.setInform_PASS(rs.getString("Inform_PASS"));
				inform.setInform_NM(rs.getString("inform_NM"));
				inform.setInform_TITLE(rs.getString("inform_TITLE"));
				inform.setInform_TEXTS(rs.getString("inform_TEXTS"));
				inform.setInform_FILES(rs.getString("inform_FILES"));
				inform.setRE_REF(rs.getInt("RE_REF"));
				inform.setRE_LEV(rs.getInt("RE_LEV"));
				inform.setRE_SEQ(rs.getInt("RE_SEQ"));
				inform.setREADCOUNT(rs.getInt("READCOUNT"));
				inform.setCREATE_DATE(rs.getDate("CREATE_DATE"));
				inform.setInform_INF(rs.getString("inform_INF"));
				articleList.add(inform);
			}

		}catch(Exception ex){
			System.out.println("getinformList 에러 : " + ex);
		}finally{
			close(rs);
			close(pstmt);
		}
		return articleList;
	}
	
	// 게시판 검색
	public ArrayList<InformBean> selectArticlesearch2(int page,int limit, String search){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String inform_list_sql="select * from (select rownum rnum, A.* from (select * from inform where inform_TITLE like '%'||?||'%' and inform_inf = 1 order by inform_NUM desc ) A ) where rnum between ? and ?";
		ArrayList<InformBean> articleList = new ArrayList<InformBean>();
		int startrow=(page-1)*10+1; //읽기 시작할 row 번호..	
		int endrow=(page-1)*10+10;

		try{
			pstmt = con.prepareStatement(inform_list_sql);
			pstmt.setString(1, search);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();

			while(rs.next()){
				InformBean inform = new InformBean();				
				inform.setRownum(rs.getInt("rnum"));
				inform.setInform_NUM(rs.getInt("inform_NUM"));
				inform.setInform_PASS(rs.getString("Inform_PASS"));
				inform.setInform_NM(rs.getString("inform_NM"));
				inform.setInform_TITLE(rs.getString("inform_TITLE"));
				inform.setInform_TEXTS(rs.getString("inform_TEXTS"));
				inform.setInform_FILES(rs.getString("inform_FILES"));
				inform.setRE_REF(rs.getInt("RE_REF"));
				inform.setRE_LEV(rs.getInt("RE_LEV"));
				inform.setRE_SEQ(rs.getInt("RE_SEQ"));
				inform.setREADCOUNT(rs.getInt("READCOUNT"));
				inform.setCREATE_DATE(rs.getDate("CREATE_DATE"));
				inform.setInform_INF(rs.getString("inform_INF"));
				articleList.add(inform);
			}

		}catch(Exception ex){
			System.out.println("getinformList 에러 : " + ex);
		}finally{
			close(rs);
			close(pstmt);
		}
		return articleList;
	}

}
