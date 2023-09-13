package team_2jo.nichanaecha.model.dao;

public class AuctionDao extends Dao{
	// 싱글톤 객체
	private static AuctionDao auctionDao = new AuctionDao();
	public static AuctionDao getInstence() {return auctionDao;}
	private AuctionDao() {}
}
