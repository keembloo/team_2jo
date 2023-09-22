package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.dto.AuctionDto;
import model.dto.CarAddressDto;
import model.dto.CarDto;

public class AuctionDao extends Dao {
	private static AuctionDao auctionDao = new AuctionDao();
	public static AuctionDao getInstence() {return auctionDao;}
	private AuctionDao() {}
	
	// 자동차 정보 dto 반환 함수
	public CarDto carDto(int cno) {
		try {
			String sql = "select * from car where cno = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cno);
			ResultSet rs = ps.executeQuery();
			
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
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ano);
			ResultSet rs = ps.executeQuery();
			
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
	public Map<Integer,String> imglist(int cno){
		try {
			String sql = "select * from carimg where cno = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cno);
			ResultSet rs = ps.executeQuery();
			
			Map<Integer,String> imglist = new HashMap<>();
			
			while(rs.next()) {
				imglist.put(rs.getInt("cino"), rs.getString("ciimg"));
			}
			
			return imglist;
			
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
			ps = conn.prepareStatement( sql , Statement.RETURN_GENERATED_KEYS ); 
			
			
			
			return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	
//게시물 상세조회 [9월19일 고연진]------------------------------------------------------------
	public AuctionDto auctionPrint(int ano) {
		
		AuctionDto auctionDto = new AuctionDto();
		
		try {
			String sql = "select cno from auctioninfo where ano=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, ano);
			rs=ps.executeQuery(); 
			//경매 번호에 맞는 차량 번호 찾기
			if(rs.next()) {
				CarDto carDto = carDto(rs.getInt("cno"));//차량번호에 맞는 CarDto 객체 가져옴.
				Map<Integer, String> imglist= imglist(rs.getInt("cno"));//cno에 맞는 차량이미지 저장한거 가져옴
				carDto.setimglist(imglist);
				
				auctionDto =auctionDto(ano);
				auctionDto.setCar(carDto);
			}
			return auctionDto;
			
		} catch (Exception e) {System.out.println("auctionPrint() 오류: "+e);}
		
		
		
		return null;
	
	}//f()
	
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
	
	
	
	
	
	
	
	
	
	
	// 좌표 영역 내 옥션,자동차 정보 반환 [ 정용상 ]
	public List<CarAddressDto> mapAreaPrint(String east, String west, String south, String north) {
		List<CarAddressDto> list = new ArrayList<>();
		
		try {
			String sql = "select cacodename, count(cacodename) as count, avg(calat) as calat, avg(calng) as calng from caraddress where calat between ? and ? and calng between ? and ?\n"
					+ "group by cacodename;";

			
			//String sql = "select * from caraddress where calat between ? and ? and calng between ? and ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, west);
			ps.setString(2, east);
			ps.setString(3, south);
			ps.setString(4, north);
			rs = ps.executeQuery();
			
			System.out.println("sql : " +east);
			System.out.println("sql : " +west);
			System.out.println("sql : " +south);
			System.out.println("sql : " +north);
			
			while(rs.next()) {
				
				/*
				CarAddressDto carAddressDto = new CarAddressDto(
						rs.getString("cads"),
						rs.getString("calat"),
						rs.getString("calng"),
						rs.getString("cacode"),
						rs.getString("cacodename"),
						rs.getInt("cno")
						);
				*/
				CarAddressDto carAddressDto = new CarAddressDto();
				carAddressDto.setCalat(rs.getString("calat"));
				carAddressDto.setCalng(rs.getString("calng"));
				
				list.add(carAddressDto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("좌표 내 제품 검색 sql문 예외 : "+e);
			return null;
		}
	}

	
} // 클래스 종료
