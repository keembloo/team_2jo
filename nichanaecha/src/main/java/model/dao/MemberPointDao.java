package model.dao;

import java.util.ArrayList;

import model.dto.MemberPointDto;

public class MemberPointDao extends Dao {
	private static MemberPointDao memberpointDao = new MemberPointDao();
	public static MemberPointDao getInstence() {return memberpointDao;}
	private MemberPointDao() {}
	
	// 규리 페이징 처리를 위한 포인트입출금내역 수 출력
	public int totalSize (int mno , String type) {
		try {
			String sql = "select count(*) from pointrecord where mno =?";
			// 만약에 전체보기가 아니면 
			
			if (type.equals("PointInput")) {
				sql += " and pointhistory = '입금'";
			} else if (type.equals("PointOutput")) {
				sql += " and pointhistory = '출금'";
			}
			System.out.println(sql);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next())return rs.getInt(1);
		}catch (Exception e) {System.out.println(e);}
		return 0;
	}
	
	
	
	
	// 규리 전체 포인트입출금내역 출력
	public ArrayList<MemberPointDto> PointAllView(int mno , String type , int startrow , int listsize ){ 
		//System.out.println("다오 실행 ");
		ArrayList<MemberPointDto> list = new ArrayList<>();
		
		try {
			String sql ="select * from pointrecord where mno =?";
			
			if (type.equals("PointInput")) {
				sql += " and pointhistory = '입금'";
			} else if (type.equals("PointOutput")) {
				sql += " and pointhistory = '출금'";
			}
			sql += " order by pointdate desc limit ? , ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, mno);
			ps.setInt(2, startrow);
			ps.setInt(3, listsize);
			rs = ps.executeQuery();
			System.out.println("sql : "+sql);
			while (rs.next()) {
				MemberPointDto memberPointDto = new MemberPointDto(
						rs.getString("pointno"), 
						rs.getInt("mno"), 
						rs.getInt("mpoint"), 
						rs.getString("pointdate"), 
						rs.getString("pointhistory"));
				list.add(memberPointDto);
			}
			return list;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	
	//규리 회원 포인트 입금, 출금
	public boolean PointUpdate( String type ,  int mno , int gold , String mpno) {
		try {
			String sign = "";
			if (type.equals("입금")) { // 입금
				sign = "+";
			} else if (type.equals("출금")){ // 출금
				sign = "-";
			}
			//System.out.println("기호 :"+sign);
			String sql ="update member set mcash = mcash "+sign+"? where mno = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(2, mno);
			ps.setInt(1, gold);
			int result = ps.executeUpdate();
			
			if (result==1) return true;
	
		} catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	// 규리 포인트 내역 관련 저장함수
	public boolean setPoint( String type ,  int mno , int gold , String mpno ) {
		try {
			//System.out.println("다오 mpno : "+mpno);
			String sql = "insert into pointrecord(pointno , mno , mpoint , pointhistory ) values(?, ? , ? , ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, mpno);
			ps.setInt(2, mno);
			ps.setInt(3, gold);
			ps.setString(4, type);
			int result = ps.executeUpdate();
				if (result == 1) {
					return true;
				} else {
					return false;
				}
		} catch (Exception e) {
			e.getStackTrace();
			//System.out.println(e);
		}
		return false;
	}
	
	
	
}
