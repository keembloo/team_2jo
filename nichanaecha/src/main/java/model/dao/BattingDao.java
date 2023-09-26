package model.dao;

import model.dto.BattingDto;

public class BattingDao extends Dao {
	private static BattingDao battingDao = new BattingDao();
	public static BattingDao getInstence() {return battingDao;}
	private BattingDao() {}


//입찰등록
	public boolean batting(BattingDto dto) {
		try {
			String sql="insert into buymember (bprice,mno,ano) values(?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setLong(1, dto.getBprice());
			ps.setInt(2, dto.getMno());
			ps.setInt(3, dto.getAno());
			int count=ps.executeUpdate();
			if(count==1) {return true;}
		} catch (Exception e) {System.out.println("batting()오류"+e);}
		return false;
	}//f()













}//c
