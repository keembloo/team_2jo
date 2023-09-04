package team_2jo.sns.model.dao;



public class SnsDao extends Dao{
	// 싱글톤 객체
	private static SnsDao snsDao = new SnsDao();
	public static SnsDao getInstence() {return snsDao;}
	private SnsDao() {}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//게시물 삭제(이성호)
	public boolean ondelete(int bno) {
		try {
			String sql="delete from board where bno = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bno);
			int count = ps.executeUpdate();
			if( count == 1) return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
}
