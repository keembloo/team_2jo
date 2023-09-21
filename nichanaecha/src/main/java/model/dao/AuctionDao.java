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
	
	// 자동차 정보 dto 반환 함수
	public CarDto carDto(int cno) {
		try {
			String sql = "select * from car where cno = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cno);
			rs = ps.executeQuery();
			
			rs.next();
			
			CarDto carDto = new CarDto(
					rs.getInt("cno"),
					rs.getString("ccompany"),
					rs.getString("cnum"),
					rs.getString("csize"),
					rs.getInt("cc"),
					rs.getString("coil"),
					rs.getString("cname"),
					rs.getString("cdate"),
					rs.getInt("ckm"),
					rs.getString("cads"),
					rs.getString("clat"),
					rs.getString("clng"),
					rs.getInt("mno")
			);
			
			return carDto;
			
		} catch (Exception e) {
			System.out.println("carDto 반환 sql 예외 : "+e);
			return null;
		}
		
	}
	
	// 경매 정보 dto 반환 함수
	public AuctionDto auctionDto(int ano) {
		try {
			String sql = "select * from auctionInfo where ano = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, ano);
			rs = ps.executeQuery();
			
			rs.next();
			
			AuctionDto auctionDto = new AuctionDto(
					rs.getInt("ano"),
					rs.getString("atitle"),
					rs.getString("acontent"),
					rs.getString("astartdate"),
					rs.getString("aenddate"),
					rs.getLong("aprice"),
					rs.getInt("astate"),
					rs.getInt("cno")
			);
			
			return auctionDto;
			
			
		} catch (Exception e) {
			System.out.println("auctionDto 반환 sql문 예외 : "+e);
			return null;
		}
	}
	
	// 자동차 이미지 Map 반환 함수
	public Map<Integer,String> ciimg(int cno){
		try {
			String sql = "select * from carimg where cno = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cno);
			rs = ps.executeQuery();
			
			Map<Integer,String> ciimg = new HashMap<>();
			
			while(rs.next()) {
				ciimg.put(rs.getInt("cino"), rs.getString("ciimg"));
			}
			
			return ciimg;
			
		} catch (Exception e) {
			System.out.println("자동차 img Map 반환 함수 sql 예외");
			return null;
		}
		
		
	}
	
	
	
	
	
	
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
				ResultSet rs1 = ps1.executeQuery();
					while(rs1.next()) {
						imglist.put(rs1.getInt("cno"), rs1.getString("ciimg"));
						}//w
				
						
			}
			
		} catch (Exception e) {System.out.println("Dao- auctionPrint()오류"+e);}
		
		return null;
	}
	
	
	
	
	
	// 좌표 영역 내 옥션,자동차 정보 반환
	public AuctionDto mapAreaPrint(int east, int west, int south, int north) {
		
		
		
		
		
	}
	
	
	
	
	
	
	
} // 클래스 종료
