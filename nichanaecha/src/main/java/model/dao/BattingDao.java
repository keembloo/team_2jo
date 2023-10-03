package model.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.auction.BattingSocket;
import model.dto.BattingDto;

public class BattingDao extends Dao {
	private static BattingDao battingDao = new BattingDao();
	public static BattingDao getInstence() {return battingDao;}
	private BattingDao() {}


//입찰등록-----------------------------------------------------------------------
	public boolean batting(BattingDto dto) {
		try {
			System.out.println("컨트롤러에서 Dao로 입찰등록 들어옴");
			String sql="insert into buymember (bprice,mno,ano) values(?,?,?)";
			ps=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, dto.getBprice());
			ps.setInt(2, dto.getMno());
			ps.setInt(3, dto.getAno());
			int count=ps.executeUpdate();
			
			rs=ps.getGeneratedKeys();
			rs.next();
			int newPk=rs.getInt(1);
			System.out.println("newPk번호 > "+newPk);
			if(count==1) {
				System.out.println("count==1 실행 "+count);
				sql="select*from buymember where bno= "+newPk;
				System.out.println("count값 sql문으로 잘 들어옴"+sql);
				ps=conn.prepareStatement(sql);
				System.out.println("ps까진 ㄱㅊ???");
				
				rs=ps.executeQuery();
				System.out.println("rs넘어옴??????"+rs);
				
				String nowContent="";
				System.out.println("nowContent 초기> "+nowContent);
				if(rs.next()) {
					System.out.println("if문 안으로 들어오긴함?");
					nowContent+=
							"<div class=\"aSocket\">"
							+"<li>"
						    +rs.getLong("bprice")
						    +"</li>"
						    +"<li>"
						    +rs.getString("bDate")
							+"</li>"
							+"</div>";
					System.out.println("변경된nowContent> "+nowContent);
				}
				BattingSocket socket=new BattingSocket();
				socket.onMessage(null, nowContent);
				return true;
			}
		} catch (Exception e) {System.out.println("batting()오류"+e);}
		return false;
	}//f()

//기존입찰출력(상위3)------------------------------------------------------------------
	public ArrayList<BattingDto> batView(int ano,int count){
		ArrayList<BattingDto> battingList= new ArrayList<>();
		try {
			String sql="select*from buymember where ano=? order by bdate desc limit "+count;
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

//입찰기록 전체 출력
	public ArrayList<BattingDto> buyView(int ano){
		try {
			System.out.println("Dao 들어옴> ");
			ArrayList<BattingDto> battingList = new ArrayList<>();
			String sql="select b.*, m.mid from buymember b natural join member m where ano=? order by bdate desc";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, ano);
			rs=ps.executeQuery();
			while(rs.next()) {
				BattingDto dto= new BattingDto(rs.getInt("mno"), rs.getInt("ano"), rs.getLong("bprice"), rs.getString("bdate"),  rs.getString("mid"));
				battingList.add(dto);
			}
			return battingList;
		} catch (Exception e) {System.out.println("경매전체출력오류"+e);}
		
		
		return null;
	}

//소켓입찰출력---------------------------------------------------------









}//c
