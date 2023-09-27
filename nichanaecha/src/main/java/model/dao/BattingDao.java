package model.dao;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.dto.BattingDto;

public class BattingDao extends Dao {
	private static BattingDao battingDao = new BattingDao();
	public static BattingDao getInstence() {return battingDao;}
	private BattingDao() {}


//입찰등록-----------------------------------------------------------------------
	public boolean batting(BattingDto dto) {
		try {
			String sql="insert into buymember (bprice,mno,ano) values(?,?,?)";
			ps=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, dto.getBprice());
			ps.setInt(2, dto.getMno());
			ps.setInt(3, dto.getAno());
			int count=ps.executeUpdate();
			
			rs=ps.getGeneratedKeys();
			rs.next();
			int addPk=rs.getInt(1);
			
			if(count==1) {return true;}
		} catch (Exception e) {System.out.println("batting()오류"+e);}
		return false;
	}//f()

//기존입찰출력------------------------------------------------------------------
	public List<BattingDto> batView(int ano){
		List<BattingDto> battingList= new ArrayList<>();
		try {
			String sql="select*from buymember where ano=? order by bdate desc limit 5";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, ano);
			rs=ps.executeQuery();
			while(rs.next()) {
			 BattingDto dto= new BattingDto(rs.getLong("bprice"), rs.getString("bdate"));
			 battingList.add(dto);
				
			}
			return battingList;
		} catch (Exception e) {System.out.println("batView()오류>"+e);}
		return null;
	}//f()


//소켓입찰출력---------------------------------------------------------
	public boolean socketView() {
		try {
			String sql = "select*from buymember where ano=?";
			ps= conn.prepareStatement(sql);
			ps.setInt(1, 0);
			
		
		} catch (Exception e) {}
		
		
		return false;
	}









}//c
