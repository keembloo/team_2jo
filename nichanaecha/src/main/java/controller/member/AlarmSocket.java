package controller.member;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

//index.js랑 통신

@ServerEndpoint("/AlarmSocket") //서버소켓
public class AlarmSocket {
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("접속한 클라이언트 소켓 > " +session);
	}//f()
	
	  @OnClose
	   public void onClose(Session session) {
		 System.out.println("소켓종료> 로그아웃한 상태일때 나와야됨");
	   }//f()
	
	
	
	
	
}//c
