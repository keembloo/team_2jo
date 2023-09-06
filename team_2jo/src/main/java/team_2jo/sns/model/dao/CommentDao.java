package team_2jo.sns.model.dao;

import team_2jo.sns.model.dto.SnsDto;

public class CommentDao extends Dao {
	
	private static CommentDao commentDao = new CommentDao();
	public static CommentDao getInstence() {return commentDao;}
	private CommentDao() {}

	
	
	// 답글 등록 규리
	public boolean oncoment(SnsDto comentdto){
		try {
			String sql ="insert into comment(ccontent,cpwd,bno) values(? , ? , ?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, comentdto.getCcontent() );
			ps.setString(2, comentdto.getCpwd());
			ps.setInt(3, comentdto.getBno());
			int row = ps.executeUpdate();
			if (row==1) return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	
	
	
	
	
	
	
//댓글삭제[고연진]
	public boolean cdelte(int cno, String cpwd) {
		try {
			String sql="delete from comment where cno=? and cpwd=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cno);
			ps.setString(2, cpwd);
			int row=ps.executeUpdate();
			if(row==1) {return true;
			}
		} catch (Exception e) {System.out.println("Dao오류: "+e);}
		return false;
	}//f()
	
	
	
}
