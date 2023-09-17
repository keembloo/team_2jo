package model.dao;

import model.dto.MemberDto;

public class MemberDao extends Dao {
	private static MemberDao memberDao = new MemberDao();
	public static MemberDao getInstence() {return memberDao;}
	private MemberDao() {}
	
	
	//규리
	public MemberDto mview( int dto) {
		return null;
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
	public boolean login( String mid, String mpwd) {
		try {
			String sql = "";
		
			
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	
	// 3. 아이디 찾기 
	
	
	// 4. 비밀번호 찾기
	
	
	// 5. 내정보 호출 
	public MemberDto info( String mid ) {
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
