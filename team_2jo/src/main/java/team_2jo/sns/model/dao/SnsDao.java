package team_2jo.sns.model.dao;

import team_2jo.sns.model.dto.SnsDto;

public class SnsDao extends Dao{
	// 싱글톤 객체
	private static SnsDao snsDao = new SnsDao();
	public static SnsDao getInstence() {return snsDao;}
	private SnsDao() {}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	// 글 수정
	public boolean update(SnsDto dto) {
		try {
			String sql = "update board set bfile = ?, bdate = now(), bcontent = ? where bno = ? and bpwd ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getBfile());
			ps.setString(2, dto.getBcontent());
			ps.setInt(3, dto.getBno());
			ps.setString(4, dto.getBpwd());
			
			if(ps.executeUpdate()==1) {
				return true;
			}else {
				return false;
			}
			
			
		}catch (Exception e) {
			System.out.println("수정 sql문 예외 : "+e);
			return false;
		}
		
	}
	
	// 비밀번호 일치 여부
	public boolean pwdCheck(int bno, String bpwd) {
		try {
			String sql = "select * from board where bno =? bpwd = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bno);
			ps.setString(2, bpwd);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
			
		}catch (Exception e) {
			System.out.println("비밀번호 유효검 검사 sql 예외 : "+e);
			return false;
		}
			
	}

	
	//게시물 삭제(이성호)
	public boolean ondelete(int bno) {
		try {
			String sql="delete from board where bno = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bno);
			int count = ps.executeUpdate();
			if( count == 1) return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
}
