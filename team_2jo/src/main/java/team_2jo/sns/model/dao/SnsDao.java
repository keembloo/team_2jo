package team_2jo.sns.model.dao;

import java.util.ArrayList;

import team_2jo.sns.model.dto.SnsDto;

public class SnsDao extends Dao{
	// 싱글톤 객체
	private static SnsDao snsDao = new SnsDao();
	public static SnsDao getInstence() {return snsDao;}
	private SnsDao() {}
	
	
	
	
	
	
	
	
	// 글출력 규리
	public ArrayList<SnsDto> onView() {
		ArrayList<SnsDto> list = new ArrayList<>();
		try {
			String sql = "select * from board order by bno desc";
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
