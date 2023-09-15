package model.dao;

public class AuctionDao extends Dao {
	private static AuctionDao auctionDao = new AuctionDao();
	public static AuctionDao getInstence() {return auctionDao;}
	private AuctionDao() {}
}
