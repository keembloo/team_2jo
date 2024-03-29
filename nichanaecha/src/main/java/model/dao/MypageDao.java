package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.dto.AuctionDto;
import model.dto.BattingDto;
import model.dto.CarDto;
import model.dto.MemberDto;

public class MypageDao extends Dao {
	private static MypageDao mypageDao = new MypageDao();
	public static MypageDao getInstence() {return mypageDao;}
	private MypageDao() {}
	
	
	// ----------------- 기존코드  -----------------
	//규리 회원 정보호출
	public MemberDto mview( int mno) {
		try {
			String sql ="select * from member where mno = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, mno);
			rs = ps.executeQuery();
			if ( rs.next()) {
				MemberDto memberDto = new MemberDto(
						rs.getInt("mno"), rs.getString("mname"),  rs.getLong("mcash")
						);
				//System.out.println(memberDto);
				return memberDto;
			}
		} catch (Exception e) {System.out.println(e);}
		return null;
	}
	
	// 규리 1 등록매물 출력
	public ArrayList<AuctionDto> mySubmitcarView(int mno , String type) {
		//mno = 3; // 테스트
		ArrayList<AuctionDto> list = new ArrayList<>();
		try {
			if(type.equals("mySubmitcarView")) { // 타입이 mySubmitcarView면 등록한 매물출력
				/* 기존 쿼리
				String sql = "select * from car as c"
						+ " inner join auctionInfo as a on c.cno = a.cno"
						+ " inner join (select cno, MAX(ciimg) as img from carimg group by cno)"
						+ " as i on c.cno = i.cno where c.mno = ?";
						*/
				String sql ="select c.cno , c.mno , a.ano , a.atitle , a.aenddate , a.aprice , a.astate , i.img from car as c"
						+ " inner join auctionInfo as a on c.cno = a.cno"
						+ " inner join (select cno, MAX(ciimg) as img from carimg group by cno)"
						+ " as i on c.cno = i.cno where c.mno = ? order by a.astartdate desc limit 10";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, mno);
				rs = ps.executeQuery();}
			//System.out.println("mno : "+mno+"type : "+type);
			
			while (rs.next()) {
				//등록된 경매정보 dto에 차례대로 넣고 list로 출력해야함! 
				// 등록번호 , 경매 제목 , 경매 종료 날짜 , 경매 등록 가격 , 경매 종료 시간 , 경매 상태  + 차량이미지
				// 1. 이미지 생성하고 // 2. 카객체생성  // 3.생성한 이미지 카에 담고 // 4. 옥션에 카 추가 
				Map<Integer, String> carimglist = AuctionDao.getInstence().imglist(rs.getInt("cno"));
				CarDto carDto = AuctionDao.getInstence().carDto(rs.getInt("cno"));
				carDto.setImglist(carimglist);
				AuctionDto auctionDto = AuctionDao.getInstence().auctionDto(rs.getInt("cno"));
				auctionDto.setCar(carDto);
				
				//System.out.println("carimglist"+carimglist);
				//System.out.println("carDto"+carDto);
				//System.out.println("auctionDto"+auctionDto);
				list.add(auctionDto); // 리스트에 추가
				//System.out.println(list);
			}
			//System.out.println("다오에서 list 출력 : "+list);
			return list;
		} catch (Exception e) { System.out.println(e);	}
		return null;
	}
	
	// 규리 2 입찰한 매물 출력
	public ArrayList<AuctionDto> myAuctionView(int mno , String type) {
		//mno = 3; // 테스트
		ArrayList<AuctionDto> list = new ArrayList<>();
		try {
			String sql = "select c.cno , c.mno , a.ano , a.atitle , a.aenddate , a.aprice , a.astate from buymember as b"
					+ " inner join auctionInfo as a on b.ano = a.ano"
					+ " inner join car as c on c.cno = a.cno"
					+ " left join (select cno, MAX(ciimg) as img from carimg group by cno) as i on c.cno=i.cno"
					+ " where b.mno = ? order by a.astartdate desc limit 10";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, mno);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				// 1. 이미지 생성하고 // 2. 카객체생성  // 3.생성한 이미지 카에 담고 // 4. 옥션에 카 추가 
				Map<Integer, String> carimglist = AuctionDao.getInstence().imglist(rs.getInt("cno"));
				CarDto carDto = AuctionDao.getInstence().carDto(rs.getInt("cno"));
				carDto.setImglist(carimglist);
				AuctionDto auctionDto = AuctionDao.getInstence().auctionDto(rs.getInt("cno"));
				auctionDto.setCar(carDto);
				
				//System.out.println("carimglist"+carimglist);
				//System.out.println("carDto"+carDto);
				//System.out.println("auctionDto"+auctionDto);
				list.add(auctionDto); // 리스트에 추가
				//System.out.println(list);
			}
			//System.out.println("다오에서 list 출력 : "+list);
			return list;
		} catch (Exception e) { System.out.println(e);	}
		return null;
	}
		
	// 규리 3 찜한 매물 출력
	public ArrayList<AuctionDto> myWishlistView(int mno , String type) {
	//mno = 3; // 테스트
		ArrayList<AuctionDto> list = new ArrayList<>();
		try {
			String sql = "select c.cno , c.mno , a.ano , a.atitle , a.aenddate , a.aprice , a.astate , i.img from wishlist as w"
					+ " inner join auctionInfo as a on w.ano = a.ano"
					+ " inner join car as c on c.cno = a.cno"
					+ " inner join (select cno, MAX(ciimg) as img from carimg group by cno)"
					+ " as i on c.cno = i.cno where w.mno = ? order by a.astartdate desc limit 30";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, mno);
			rs = ps.executeQuery();
		
			while (rs.next()) {
				// 1. 이미지 생성하고 // 2. 카객체생성  // 3.생성한 이미지 카에 담고 // 4. 옥션에 카 추가 
				Map<Integer, String> carimglist = AuctionDao.getInstence().imglist(rs.getInt("cno"));
				CarDto carDto = AuctionDao.getInstence().carDto(rs.getInt("cno"));
				carDto.setImglist(carimglist);
				AuctionDto auctionDto = AuctionDao.getInstence().auctionDto(rs.getInt("cno"));
				auctionDto.setCar(carDto);
				
				//System.out.println("carimglist"+carimglist);
				//System.out.println("carDto"+carDto);
				//System.out.println("auctionDto"+auctionDto);
				list.add(auctionDto); // 리스트에 추가
				//System.out.println(list);
			}
			//System.out.println("다오에서 list 출력 : "+list);
			return list;
		} catch (Exception e) { System.out.println(e);	}
		return null;
	}
		
	//규리 제품에 해당하는 이미지만 출력
	public Map<Integer, String> getMycarImg(int cno){ // 차량 번호를 받아서 이미지파일명을 반환해줌 {1 : 셀토스.jpg} 형태로
		try {
			Map<Integer, String> carimglist = new HashMap<>(); // 같은 차번호의 다른이미지 여러개담을거
			String sql = "select * from carimg where cno = "+cno;	// 같은 차번호로 차이미지 테이블 조회
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				carimglist.put(rs.getInt("cino") , rs.getString("ciimg"));
			}
			return carimglist;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	// 규리 회원 연락처 출력 ( 최종입찰자를 찾아라 )
	public MemberDto findphone(int cno) {
		try {
			//System.out.println("입찰자다오cno : " +cno);
			String sql = "select * from buymember as b"
					+ " inner join auctionInfo as a on a.ano = b.ano"
					+ " inner join member as m on m.mno = b.mno"
					+ " where cno = ?"
					+ " order by b.bdate desc limit 1"; // 최신날짜의 내림차순으로 최종 1개만 조회
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cno);
			rs= ps.executeQuery();
			if(rs.next()) {
				MemberDto memberdto = new MemberDto(
						rs.getInt("mno") , 
						rs.getString("mid"), 
						rs.getString("mphone"));
				return memberdto;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	//규리 회원 연락처 출력 ( 판매자를 찾아라 )
	public MemberDto findSeller(int cno) {
		try {
			//System.out.println("판매자 다오cno : " +cno);
			String sql = "select * from car as c"
					+ " inner join member as m on m.mno = c.mno where cno = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cno);
			rs= ps.executeQuery();
			if(rs.next()) {
				MemberDto memberdto = new MemberDto(
						rs.getInt("mno") , 
						rs.getString("mid"), 
						rs.getString("mphone"));
				return memberdto;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
}
