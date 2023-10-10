package model.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.auction.BattingSocket;
import controller.member.AlarmSocket;
import model.dto.AlarmDto;
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
				sql="select m.mid \r\n"
						+ "	from car c , auctioninfo auc  , member m , buymember buy\r\n"
						+ "where \r\n"
						+ "	c.cno = auc.cno  and\r\n"
						+ "    c.mno = m.mno   and\r\n"
						+ "    buy.ano = auc.ano   and \r\n"
						+ "    bno = "+newPk;
				//System.out.println("count값 sql문으로 잘 들어옴"+sql);
				ps=conn.prepareStatement(sql);
				//System.out.println("ps까진 ㄱㅊ???");
				
				ResultSet rs2 =  ps.executeQuery();
				rs2.next();
			
			/*
				String alarm="참여중인 경매의 가격 변동이 있습니다";
				AlarmSocket socket= new AlarmSocket();
			*/
				
				String msg="";
			/*
			 	if(rs.next()) {
					System.out.println("if문 안으로 들어오긴함?");
					msg+=rs.getLong("bprice")+"<br>"+rs.getString("bdate");
				}
			 */	
				//입찰내역을 실시간으로 보여주기 위해 사용
				BattingSocket socket = new BattingSocket();
				socket.onMessage(null, msg);
				
//???????????????????????????????????????????????
	//null에 뭘넣어야될지 ,,, AlarmSoketdptj session 어떻게 비교해야할지 모르겠... 			
				//작성자에게 업데이트된 상황을 알려줄 메세지 작성
				String mid=rs2.getString("mid");
				String alarm="입찰가 업데이트 되었습니다";
				
				AlarmDto alarmDto= new AlarmDto( mid, alarm);
				
				ObjectMapper mapper=new ObjectMapper();
				String json=mapper.writeValueAsString(alarmDto);				
				
				AlarmSocket alarmSocket=new AlarmSocket();
				alarmSocket.onMessage(null, json);
				
				return true;
			
			
			}
		
		} catch (Exception e) {System.out.println("batting()오류"+e);}
		return false;
	}//f()
	
	
//기존입찰출력(상위count개)------------------------------------------------------------------
	public ArrayList<BattingDto> batView(int ano,int count){
		ArrayList<BattingDto> battingList= new ArrayList<>();
		try {
			String sql="select*from buymember where ano=? order by bdate desc limit "+count;
			ps=conn.prepareStatement(sql);
			ps.setInt(1, ano);
			rs=ps.executeQuery();
			while(rs.next()) {
			System.out.println("List 구하는건데 whlile문 들어옴? ");
			BattingDto dto= new BattingDto(rs.getLong("bprice"), rs.getString("bdate"));
			 battingList.add(dto);
			System.out.println("while문 안에서 List 잘 더해지고 있음? "+battingList);	
			}
			System.out.println("DAO에서 battingList > "+battingList);
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

// 최신 입찰자 회원정보를 가져오는 함수 [10월6일 고연진]
		// 10/08 오류 : carinfo.js  getBuyTop함수에게 null 값을 반환했을때 오류 발생 .. 문제 파악은 되셨쬬??
	
		public BattingDto getBuyTop(int ano){
			System.out.println("입찰자함수 실행> ");
			try {
				String sql="select*from buymember where ano=? order by bno desc limit 1";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, ano);
				rs=ps.executeQuery();
				
				if(rs.next()) {
					System.out.println("if안으로 들어오나요?? ");
					BattingDto dto = new BattingDto(rs.getInt("mno"), rs.getLong("bprice"));
					System.out.println("전달받은 dto > "+dto);
					return dto; // carinfo.js  getBuyTop함수에게 반환되는 값 
				}
				
			} catch (Exception e) {System.out.println("getBuyMember()오류> "+e);
			}
			
			return null; // carinfo.js  getBuyTop함수에게 반환되는 값 
		}//f()
		
		
// 입찰자가 참여한 모든 경매글 조회
		public List<BattingDto> getBuyAuation(int mno) {
			System.out.println("모든 입찰자 회원정보 가져오는 함수 실행 ");
			List<BattingDto> list = new ArrayList<>();
			try {
				String sql="select distinct ano from buymember natural join member where mno= "+mno;
				System.out.println("sql> "+sql);
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				while(rs.next()) {
					BattingDto dto = new BattingDto(mno, rs.getInt("ano"));
					list.add(dto);
					System.out.println("입찰자가 참여한 모든 경매글 번호> "+list);
				}
				return list;
			} catch (Exception e) {System.out.println("AllbuyMember()오류 > "+e);
			}
			
			return null;
			
		}
		
		
		
}//c
