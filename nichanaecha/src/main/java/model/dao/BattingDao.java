package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.dto.BattingDto;

public class BattingDao extends Dao {
	private static BattingDao battingDao = new BattingDao();
	public static BattingDao getInstence() {return battingDao;}
	private BattingDao() {}


	//상위 3개 출력
	public List<BattingDto> topByBatting(int ano) {
	   List<BattingDto> list= new ArrayList<>();
	   try {
	      String sql="select * from buymember where ano= ? order by bdate desc limit 3";
	      ps=conn.prepareStatement(sql);
	      ps.setInt(1, ano);
	      rs=ps.executeQuery();
	      
	      while(rs.next()) {
	         BattingDto dto = new BattingDto(rs.getInt("mno"), rs.getInt("ano"), rs.getLong("bprice"), rs.getString("bdate"));
	         
	         list.add(dto); 
	      }
	      System.out.println("Dao의 리스트: "+list);
	      return list;
	   } catch (Exception e) {System.out.println("TopByBatting(): "+e);return null;}
	   
	}//f()

	//배팅금액유효성검사
	public BattingDto priceVal(int ano,long bprice) {
	   try {
	      String sql="select * from buymember where ano=? order by bdate desc";
	      ps=conn.prepareStatement(sql);
	      ps.setInt(1, ano);
	      rs=ps.executeQuery();
	      if(rs.next()) {
	         BattingDto dto= new BattingDto(rs.getInt("mno"), rs.getInt("ano"), rs.getLong("bprice"));
	         return dto;
	      }
	      
	   } catch (Exception e) {System.out.println("priceVal()"+e);}
	   
	   return null;
	}//f()


















}//c
