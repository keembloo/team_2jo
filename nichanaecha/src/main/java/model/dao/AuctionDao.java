package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
	public ArrayList<Object> auctionPrint(int ano) {
		
		ArrayList<Object> list = new ArrayList<>(); //List는 []형식으로 들어옴
		
		try {
			String sql="select cno, ccompany,csize,cc,coil,cname,cdate,ckm,cads,atitle,acontent,astartdate,aenddate,aprice,astate from car c natural join auctioninfo  where ano=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, ano);
			rs = ps.executeQuery();
			
			if(rs.next()) {
			
				Map<Integer, String> imglist = new HashMap<>(); //Map은 {} 형식으로 들어옴.
				
				sql="select*from carimg where cno= "+ rs.getInt("cno");
				PreparedStatement ps1=conn.prepareStatement(sql);
				ResultSet rs1=ps1.executeQuery();
				int count = 0;	
				
				while(rs1.next()) {
						imglist.put(rs1.getInt("cino"), rs1.getString("ciimg"));
						}//w
				

				System.out.println( "imglist.size()"+imglist.size() );
				
				CarDto carDto = new CarDto();
				carDto.setCno(rs.getInt("cno"));
				carDto.setCcompany(rs.getString("ccompany"));
				carDto.setCsize(rs.getString("csize"));
				carDto.setCc(rs.getInt("cc"));
				carDto.setCoil(rs.getString("coil"));
				carDto.setCname(rs.getString("cname"));
				carDto.setCdate(rs.getString("cdate"));
				carDto.setCkm(rs.getInt("ckm"));
				carDto.setCads(rs.getString("cads"));
				carDto.setImglist(imglist);
				list.add(carDto);
				System.out.println("List안에 포함된 CarDto: "+list);
				
				AuctionDto auctionDto=new AuctionDto();
				auctionDto.setAtitle(rs.getString("atitle"));
				auctionDto.setAcontent(rs.getString("acontent"));
				auctionDto.setAstartdate(rs.getString("astartdate"));
				auctionDto.setAenddate(rs.getString("aenddate"));
				auctionDto.setAprice(rs.getInt("aprice"));
				auctionDto.setAstate(rs.getInt("astate"));
				list.add(auctionDto);
				System.out.println("List안에 포함된 것들: "+list);
					
			}
			
				return list;

						
				
		
			
		} catch (Exception e) {System.out.println("Dao- auctionPrint()오류"+e);}
		
		
		
		return null;
	}
	
	
	
}//c
