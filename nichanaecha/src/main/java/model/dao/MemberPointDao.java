package model.dao;


public class MemberPointDao extends Dao {
	private static MemberPointDao memberpointDao = new MemberPointDao();
	public static MemberPointDao getInstence() {return memberpointDao;}
	private MemberPointDao() {}
	
	
	public void mpointView(){
		System.out.println("다오 실행 ");
		
		try {
			String sql ="";
		} catch (Exception e) {
			System.out.println(e);
		}
		
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
			
			if (result==1) {
				sql = "insert into pointrecord(pointno , mno , mpoint , pointhistory ) values(?, ? , ? , ? )";
				ps = conn.prepareStatement(sql);
				ps.setString(1, mpno);
				ps.setInt(2, mno);
				ps.setInt(3, gold);
				ps.setString(4, type);
				result = ps.executeUpdate();
				if (result == 1)
					return true;
			}else {
				return false;
			}

		} catch (Exception e) {System.out.println(e);}
		return false;
	}
	/*
	// 규리 포인트 내역 관련 저장함수
	public boolean setPoint( String type ,  int mno , int gold , String mpno ) {
		try {
			System.out.println("다오 mpno : "+mpno);
			String sql = "insert into pointrecord(pointno , mno , pointdate , pointhistory ) values(?, ? , ? , ?)";
			ps = conn.prepareStatement(sql);
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
	*/
	
	
}
