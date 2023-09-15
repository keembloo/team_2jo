package model.dao;

import java.time.LocalDateTime;

import model.dto.MemberDto;

public class MemberDao extends Dao {
	private static MemberDao memberDao = new MemberDao();
	public static MemberDao getInstence() {return memberDao;}
	private MemberDao() {}
	
	
	//규리
	public MemberDto mview( int dto) {
		return null;
	}
	
	// 1. 회원가입
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 2. 로그인
	public boolean login( String mid, String mpwd) {
		try {
			String sql = "select * from member where mid = ? and mpwd = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, mid); 	ps.setString(2, mpwd);
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
	
	
}
