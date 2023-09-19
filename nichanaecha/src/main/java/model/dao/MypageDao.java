package model.dao;

import java.util.ArrayList;

import model.dto.AuctionDto;
import model.dto.MemberDto;

public class MypageDao extends Dao {
	private static MypageDao mypageDao = new MypageDao();
	public static MypageDao getInstence() {return mypageDao;}
	private MypageDao() {}
	
	
	/*
	// ----------------- 새로운 출력 코드 -----------------
	public ArrayList<MypageDto> mview( int mno) {		
		// 경매, 차량정보, 회원 정보 레코드 dto 여러개를 저장하는 리스트
		System.out.println("다오 mno : "+mno);
		ArrayList<MypageDto> list = new ArrayList<>();
		try {
			String sql ="select * from member"
					+ " inner join car as c on member.mno = c.mno"
					+ " inner join auctionInfo as a on c.cno = a.cno"
					+ " where member.mno = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, mno);
			rs = ps.executeQuery();
			System.out.println("sql"+sql);
			while ( rs.next()) {
				MypageDto mypageDto = new MypageDto();
				
					    // 회원 정보 설정
						mypageDto.setMno(rs.getInt("mno"));
					   
						mypageDto.setMid(rs.getString("mid"));
					    mypageDto.setMpw(rs.getString("mpw"));
					    mypageDto.setMphone(rs.getString("mphone"));
					    mypageDto.setMname(rs.getString("mname"));
					    mypageDto.setMads(rs.getString("mads"));
					    mypageDto.setMcash(rs.getInt("mcash"));
		
					    // 차량 정보 설정
					    mypageDto.setCno(rs.getInt("cno"));
					    mypageDto.setCcompany(rs.getString("ccompany"));
					    mypageDto.setCnum(rs.getString("cnum"));
					    mypageDto.setCsize(rs.getString("csize"));
					    mypageDto.setCc(rs.getInt("cc"));
					    mypageDto.setCoil(rs.getString("coil"));
					    mypageDto.setCname(rs.getString("cname"));
					    mypageDto.setCdate(rs.getString("cdate"));
					    mypageDto.setCkm(rs.getInt("ckm"));
					    mypageDto.setCads(rs.getString("cads"));
					    mypageDto.setClat(rs.getString("clat"));
					    mypageDto.setClng(rs.getString("clng"));
		
					    // 경매글 정보 설정
					    mypageDto.setAno(rs.getInt("ano"));
					    mypageDto.setAtitle(rs.getString("atitle"));
					    mypageDto.setAcontent(rs.getString("acontent"));
					    mypageDto.setAstartdate(rs.getString("astartdate"));
					    mypageDto.setAenddate(rs.getString("aenddate"));
					    mypageDto.setAprice(rs.getInt("aprice"));
					    mypageDto.setAstate(rs.getInt("astate"));
					    
						
						//회원
						rs.getInt("mno"), rs.getString("mid"), rs.getString("mpw"),
						rs.getString("mphone"), rs.getString("mname"), rs.getString("mads"), 
						rs.getInt("mcash") ,
						//차량
						rs.getInt("cno"), rs.getString("ccompany") , rs.getString("cnum") ,
						rs.getString("csize"), rs.getInt("cc"), rs.getString("coil") ,
						rs.getString("cname") , rs.getString("cdate") ,rs.getInt("ckm"),
						rs.getString("cads") , rs.getString("clat") ,rs.getString("clng"),
						//경매글
						rs.getInt("ano"), rs.getString("atitle") , rs.getString("acontent") ,
						rs.getString("astartdate") , rs.getString("aenddate") ,
						rs.getInt("aprice") , rs.getInt("astate"));
						
						

				list.add(mypageDto);
			}
			System.out.println("list : "+list);
			return list;
		} catch (Exception e) {System.out.println(e);}
		return null;
	}
	*/
	// ----------------- 기존코드  -----------------
	//규리 정보호출
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
	
	// 규리 등록매물정보 출력
	public ArrayList<AuctionDto> myAuctionView(int mno) {
		ArrayList<AuctionDto> list = new ArrayList<>();
		try {
			String sql = "";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, mno);
			rs = ps.executeQuery();
			while (rs.next()) {
				//등록된 경매정보 dto에 차례대로 넣고 list로 출력해야함! 
			}
			
			return list;
		} catch (Exception e) { System.out.println(e);	}
		return list;
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
