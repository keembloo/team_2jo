package team_2jo.sns.model.dao;



public class SnsDao extends Dao{
	// 싱글톤 객체
	private static SnsDao snsDao = new SnsDao();
	public static SnsDao getInstence() {return snsDao;}
	private SnsDao() {}
	
	
	
}
