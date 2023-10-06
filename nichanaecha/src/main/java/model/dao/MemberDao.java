package model.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;

import model.dto.MemberDto;
import model.dto.MypageDto;

public class MemberDao extends Dao {
	private static MemberDao memberDao = new MemberDao();
	public static MemberDao getInstence() {return memberDao;}
	private MemberDao() {}
	
	
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
			String sql="select mno, mid ,mname , mads from member where mid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, mid);
			rs=ps.executeQuery();
			if (rs.next()) {
				MemberDto memberDto = new MemberDto();
				memberDto.setMno(rs.getInt(1));
				memberDto.setMid(rs.getString(2));
				memberDto.setMname(rs.getString(3));
				memberDto.setMads(rs.getString(4));

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
	
	
	// 규리 내정보수정페이지 정보 호출
	public MemberDto editview( int mno ) {
		try {
			String sql="select mno, mid ,mname , mads , mphone , mcash from member where mno = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, mno);
			rs=ps.executeQuery();
			if (rs.next()) {
				MemberDto memberDto = new MemberDto(
						rs.getInt("mno"), 
						rs.getString("mid"), 
						rs.getString("mphone"), 
						rs.getString("mname"), 
						rs.getString("mads"), 
						rs.getLong("mcash")
						);

				return memberDto;
			}
		}catch (Exception e) {System.out.println(e);}
		return null;
	}
	
	//규리 회원정보 수정
	public boolean memberEdit(int mno , String data , String pw , String type) {
		try {
			//System.out.println("mno"+mno+"주소"+data+"비번"+pw);
			if (type.equals("adressSend")) {
				type="mads";
			} else if (type.equals("phoneSend")) {
				type="mphone";
			} else if (type.equals("newpwSend")) {
				type="mpw";
			}
			System.out.println(type);
			String sql="update member set "+type+" = ? where mno = ? and mpw = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, data);
			ps.setInt(2, mno);
			ps.setString(3, pw);
			int rs= ps.executeUpdate();
			if (rs==1) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
	
}
