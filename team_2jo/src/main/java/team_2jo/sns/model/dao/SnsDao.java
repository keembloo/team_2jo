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
	
	
}
