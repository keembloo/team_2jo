package model.dao;

import model.dto.AuctionDto;

public class AuctionDao extends Dao {
	private static AuctionDao auctionDao = new AuctionDao();
	public static AuctionDao getInstence() {return auctionDao;}
	private AuctionDao() {}
	
	// 1.차 등록 성호
	public boolean bcarsubmit(AuctionDto auctionDto) {
		try {
			String sql= "";
			ps = conn.prepareStatement(sql);
			
			
			return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
}
