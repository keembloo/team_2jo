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
	
	
//게시물 상세조회 [9월19일 고연진]------------------------------------------------------------
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
				//Car 정보
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
				//경매정보
				AuctionDto auctionDto=new AuctionDto();
				auctionDto.setAtitle(rs.getString("atitle"));
				auctionDto.setAcontent(rs.getString("acontent"));
				auctionDto.setAstartdate(rs.getString("astartdate"));
				auctionDto.setAenddate(rs.getString("aenddate"));
				auctionDto.setAprice(rs.getInt("aprice"));
				auctionDto.setAstate(rs.getInt("astate"));//경매상태
				list.add(auctionDto);
				System.out.println("List안에 포함된 것들: "+list);
					
			}
				return list;

		} catch (Exception e) {System.out.println("Dao- auctionPrint()오류"+e);}
		
		return null;
	}
	
//스크랩(찜) 테이블 추가[9월21일 고연진]----------------------------------------------------------------	
	public boolean clipping(int mno, int ano) {
		try {//스크랩 항목 추가하기
			//스크랩 눌린 상태면 true로 아니면 false.( 현재 상태 확인을 하기 위한 함수 필요)
			System.out.println("Dao에 전달된 값 : "+ mno+ano);
			String sql=	clipState(mno,ano)?
						"delete from wishlist where mno=? and ano=?":
						"insert into wishlist value (?,?)" ;
			
			ps=conn.prepareStatement(sql); 
			ps.setInt(1, mno);
			ps.setInt(2, ano);
			int count= ps.executeUpdate();
			if(count==1) {return true;}
		} catch (Exception e) {System.out.println("clipping() 오류: "+e);}
		System.out.println("테이블추가 실패함");return false;
	}//f()
	

//현재 회원과 글의 상태를 확인하는 함수[9월21일 고연진]---------------------------------
	public boolean clipState(int mno, int ano) {
		try {//테이블에 있으면 true, 없으면 false
			System.out.println("회원과글상태확인하는함수 들어옴 =성공");
			String sql="select*from wishlist where mno=? and ano=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, mno);
			ps.setInt(2, ano);
			rs=ps.executeQuery();
			if(rs.next()) {return true;}
		} catch (Exception e) {System.out.println("clipState() 오류: "+e);}
		return false;
	}//f()
	
	
	
	
	
	
	
	
}//c
