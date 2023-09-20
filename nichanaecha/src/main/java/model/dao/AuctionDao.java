package model.dao;


import model.dto.CarDto;

public class AuctionDao extends Dao {
	private static AuctionDao auctionDao = new AuctionDao();
	public static AuctionDao getInstence() {return auctionDao;}
	private AuctionDao() {}
	
	// 1.차 등록 성호
	public boolean bcarsubmit(CarDto carDto) {
		try {
			String sql= "insert into member(cno,ccompany,cnum,csize,cc,coil,cname,cdate,ckm,cads) "
					+ " values(?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			
			
			
			return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	
	//2. 게시물 상세조회 [9월19일 고연진]
	public boolean auctionPrint(int ano) {
		try {
			String sql="";
			ps=conn.prepareStatement(sql);
			ps.executeQuery();
			
		} catch (Exception e) {System.out.println("Dao- auctionPrint()오류"+e);}
		
		
		
		return false;
	}
	
	
	
}//c
