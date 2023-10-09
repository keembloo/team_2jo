package controller.member;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;



@ServerEndpoint("/AlarmSocket/{mid}") //서버소켓
public class AlarmSocket {
	
	//들어와있는클라이언트소켓
	public static Map<String,Session > list = new HashMap<>();
	
	@OnOpen
	public void onOpen(Session session,@PathParam("mid") String mid) {
		System.out.println("알람소켓. java> 접속한 클라이언트 소켓 > " +session);
		System.out.println("접속한클라이언트소켓아이디> "+mid);
		list.put(mid, session);
		System.out.println("접속된소켓리스트> "+list);
		
	}//f()
	
	  @OnClose
	   public void onClose(Session session) {
		  //리스트안에 있는 모든 아이디를 배열로 반환.
		 Set<String> keys= list.keySet();
		 list.remove(keys);
		 System.out.println("한명 빠졌을때 list 줄어듦? > "+list);
		 
	  }//f()
	
	  @OnMessage
	  public void onMessage(Session session,String alarm) {
		  try {
			  //접속명단에 있는 아이디와  경매글 작성자 아이디가 동일한 경우 메세지 전송
			  
			  
		} catch (Exception e) {e.printStackTrace();}
		  
	  }
	
	
	
}//c
