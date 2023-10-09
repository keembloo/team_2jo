package controller.member;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;



@ServerEndpoint("/AlarmSocket/{loginMid}") //서버소켓
public class AlarmSocket {
	
	public static Map<String,Session > list = new HashMap<>();
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("접속한 클라이언트 소켓 > " +session);
	}//f()
	
	  @OnClose
	   public void onClose(Session session) {
		 System.out.println("소켓종료> 로그아웃한 상태일때 나와야됨");
	   }//f()
	
	  @OnMessage
	  public void onMessage(Session session,String alarm) {
		  try {
		} catch (Exception e) {e.printStackTrace();}
		  
	  }
	
	
	
}//c
