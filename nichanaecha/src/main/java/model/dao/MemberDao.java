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
}
