package model.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	
//2. 게시물 상세조회 [9월19일 고연진]------------------------------------------------------------
	public List<AuctionDto> auctionPrint(int ano) {
		List<AuctionDto> list= new ArrayList<>();
		try {
			String sql="select ccompany,csize,cc,coil,cname,cdate,ckm,cads,atitle,acontent,astartdate,aenddate,aprice from car c natural join auctioninfo  where ano=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, ano);
			rs= ps.executeQuery();
			if(rs.next()) {
				Map<Integer, String> imglist = new HashMap<>();
				sql="select*from carimg where cno= "+ rs.getInt("cno");
				ps=conn.prepareStatement(sql);
				ResultSet rs1=ps.executeQuery();
					while(rs1.next()) {
						imglist.put(rs1.getInt("cno"), rs1.getString("ciimg"));
						}//w
				
				AuctionDto dto= new AuctionDto();
				dto.setCcompany(rs.getString("ccompany"));
				dto.setCsize(rs.getString("csize"));
				dto.setCc(rs.getInt("cc"));
				dto.setCoil(rs.getString("coil"));
				dto.setCname(rs.getString("cname"));
				dto.setCdate(rs.getString("cdate"));
				dto.setCkm(rs.getInt("ckm"));
				dto.setCads(rs.getString("cads"));
				dto.setAtitle(rs.getString("atitle"));
				dto.setAcontent(rs.getString("acontent"));
				dto.setAstartdate(rs.getString("astartdate"));
				dto.setAenddate(rs.getString("aenddate"));
				dto.setAprice(rs.getInt("aprice"));
					
				list.add(dto);
						
			}//w
			return list;
		} catch (Exception e) {System.out.println("Dao- auctionPrint()오류"+e);}
		
		
		
		return null;
	}
	
	
	
}//c
