package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import model.dto.AuctionDto;
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
	
	
//2. 게시물 상세조회 [9월19일 고연진]------------------------------------------------------------
	public AuctionDto auctionPrint(int ano) {
		Map<Integer, String> imglist = new HashMap<>();
		
		try {
			String sql="select cno, ccompany,csize,cc,coil,cname,cdate,ckm,cads,atitle,acontent,astartdate,aenddate,aprice from car c natural join auctioninfo  where ano=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, ano);
			rs = ps.executeQuery();
			
			if(rs.next()) {
			
				
				sql="select*from carimg where cno= "+ rs.getInt("cno");
				PreparedStatement ps1=conn.prepareStatement(sql);
				ResultSet rs1=ps1.executeQuery();
					while(rs1.next()) {
						imglist.put(rs1.getInt("cno"), rs1.getString("ciimg"));
						}//w
				
						
			}
			
				

						
				
		
			
		} catch (Exception e) {System.out.println("Dao- auctionPrint()오류"+e);}
		
		
		
		return null;
	}
	
	
	
}//c
