package team_2jo.sns.model.dao;

public class CommentDao extends Dao {
	
	private static CommentDao commentDao = new CommentDao();
	public static CommentDao getInstence() {return commentDao;}
	private CommentDao() {}

//댓글삭제[고연진]
	public boolean cdelte(String cpwd) {
		try {
			String sql="select cpwd from comment where cno=?";
			con
		} catch (Exception e) {System.out.println("Dao오류: "+e);}
		return false;
	}//f()
	
	
	
}
