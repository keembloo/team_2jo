package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.dto.AuctionDto;
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
						rs.getInt(1), rs.getString(2), 
						rs.getString(3) , rs.getString(4) , 
						rs.getString(5) , rs.getString(6) , 
						rs.getInt(7));
				
				return memberDto;
			}
		} catch (Exception e) {System.out.println(e);}
		return null;
	}
	
	// 규리 등록매물 정보 출력
	public ArrayList<AuctionDto> myAuctionView(int mno) {
		mno = 3; // 테스트
		ArrayList<AuctionDto> list = new ArrayList<>();
		try {
			String sql = "select * from car as c inner join auctionInfo as a on c.cno = a.cno where c.mno = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, mno);
			rs = ps.executeQuery();
			while (rs.next()) {
				//등록된 경매정보 dto에 차례대로 넣고 list로 출력해야함! 
				// 등록번호 , 경매 제목 , 경매 종료 날짜 , 경매 등록 가격 , 경매 종료 시간 , 경매 상태  + 차량이미지
				AuctionDto auctionDto = new AuctionDto(
						rs.getInt("ano"), rs.getString("atitle"), 
						rs.getString("aenddate"), rs.getInt("aprice"),
						rs.getInt("astate"), getMycarImg(rs.getInt("cno"))
						);

				list.add(auctionDto); // 리스트에 추가
			}
			//System.out.println("다오에서 list 출력 : "+list);
			return list;
		} catch (Exception e) { System.out.println(e);	}
		return list;
	}
	
	//규리 제품에 해당하는 이미지만 출력
	public Map<Integer, String> getMycarImg(int cno){
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
	
	
	//규리 회원 포인트 입금, 출금
	public boolean PointUpdate( int type ,  int mno , int gold ) {
		try {
			String sign = "";
			if (type ==1) { // 입금
				sign = "+";
			} else if (type ==2){ // 출금
				sign = "-";
			}
			//System.out.println("기호 :"+sign);
			String sql ="update member set mcash = mcash "+sign+"? where mno = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(2, mno);
			ps.setInt(1, gold);
			int result = ps.executeUpdate();
			if (result==1) {
				return true;
			}
		} catch (Exception e) {System.out.println(e);}
		return false;
	}
	
}
