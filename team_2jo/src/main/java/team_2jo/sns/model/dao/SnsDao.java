package team_2jo.sns.model.dao;

import java.util.ArrayList;

import team_2jo.sns.model.dto.SnsDto;

public class SnsDao extends Dao{
	// 싱글톤 객체
	private static SnsDao snsDao = new SnsDao();
	public static SnsDao getInstence() {return snsDao;}
	private SnsDao() {}
	
	//글등록[고연진]
	public boolean bwrite(SnsDto dto) {
		System.out.println("Dao에서 객체화된 dto:"+dto);
		try {
			String sql="insert into board (bfile,bcontent,bpwd) values (?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, dto.getBfile());
			ps.setString(2, dto.getBcontent());
			ps.setString(3, dto.getBpwd());
			int count = ps.executeUpdate();
			if(count==1) {return true;}
		} catch (Exception e) {System.out.println("Dao오류: "+e);}
		
		return false;
	}//f()
	
	// 글출력 규리
	public ArrayList<SnsDto> onView() {
		ArrayList<SnsDto> list = new ArrayList<>();
		try {
			String sql = "select * from board order by bdate desc";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				SnsDto snsDto = new SnsDto(
						rs.getInt(1), rs.getString(2), 
						rs.getString(3), rs.getString(4), rs.getString(5));
				list.add(snsDto);
				//System.out.println(snsDto);
			}
			return list;
		}catch (Exception e) {System.out.println(e);}
		return list;
	}
	
	
	
	
	
	// 개별 조회
	public SnsDto uprint(int bno) {
		try {
			String sql = "select * from board where bno = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bno);
			rs = ps.executeQuery();
			
			rs.next();
			SnsDto dto = new SnsDto();
			dto.setBno(rs.getInt(1));
			dto.setBfile(rs.getString(2));
			dto.setBdate(rs.getString(3));
			dto.setBcontent(rs.getString(4));
			return dto;
			
		}catch (Exception e) {
			System.out.println("업데이트 개별 출력 sql문 예외 : "+e);
			return null;
		}
		
		
	}
	
	

	// 글 수정
	public boolean update(SnsDto dto) {
		try {
			String sql = "update board set bfile = ?, bdate = now(), bcontent = ? where bno = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getBfile());
			ps.setString(2, dto.getBcontent());
			ps.setInt(3, dto.getBno());
			
			System.out.println("sqldto : "+dto);
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
			String sql = "select * from board where bno =? and bpwd = ?";
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
	public boolean ondelete(int bno, String bpwd) {
		try {
			String sql="delete from board where bno = ? and bpwd= ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bno);
			ps.setString(2, bpwd);
			int count = ps.executeUpdate();
			if( count == 1) return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
}
