package model.dao;

import java.time.LocalDateTime;

import model.dto.MemberDto;

public class MemberDao extends Dao {
	private static MemberDao memberDao = new MemberDao();
	public static MemberDao getInstence() {return memberDao;}
	private MemberDao() {}
	
	
	//규리 정보호출
	public MemberDto mview( int dto) {
		/*
		#마이페이지 출력을 위한 join
		select * from member
		inner join car on member.mno = car.mno
		inner join auctionInfo on car.cno = auctionInfo.cno
		where member.mno = 3;
		*/
		try {
			String sql ="select * from member where mno = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, dto);
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
	
	// 1. 회원가입 [9월15일 고연진]
	public boolean signup(MemberDto dto) {
		try {
			String sql = "insert into member(mid,mpw,mphone,mname,mads) values(?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1,dto.getMid());
			ps.setString(2, dto.getMpw());
			ps.setString(3, dto.getMphone());
			ps.setString(4, dto.getMname());
			ps.setString(5, dto.getMads());
			int count= ps.executeUpdate();
			if(count==1) {
				return true;
			}
			
		} catch (Exception e) {System.out.println("Dao오류: "+e);}
		
	
		return false;
	}//f()
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 2. 로그인
	public boolean login( String mid, String mpw) {
		try {
			String sql = "select * from member where mid = ? and mpw = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, mid); 	ps.setString(2, mpw);
			rs = ps.executeQuery();
			if(rs.next()) return true;		
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	
	// 3. 아이디 찾기 
	
	
	// 4. 비밀번호 찾기
	
	
	// 5. 내정보 호출 
	public MemberDto info( String mid ) {
		try {
			String sql="select mno, mid from member where mid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, mid);
			rs=ps.executeQuery();
			if( rs.next() ) {
				MemberDto memberDto = new MemberDto(
						rs.getInt(1), rs.getString(2));
						
				return memberDto;
			}
		}catch (Exception e) {System.out.println(e);}
		return null;
	}
	
	
	
	// 중복성검사 [9월18일 고연진]
	
	public boolean dataCheck(String type, String data) {
		try {
			String sql="select * from member  where "+ type + " = ?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, data);
			rs=ps.executeQuery();
			
			if(rs.next()) {return true;}
		} catch (Exception e) { System.out.println( "Dao오류: "+e);}
		return false;
	}
}
