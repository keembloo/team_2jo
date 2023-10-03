package controller.auction;

import java.io.IOException;
import java.util.ArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import model.dao.BattingDao;
import model.dto.BattingDto;

@ServerEndpoint("/BattingSocket") //서버소켓 
public class BattingSocket {

   public static ArrayList<Session> clientList = new ArrayList<>();
  
   @OnOpen
   public void onOpen(Session session) {
      System.out.println("접속한 클라이언트 소켓> "+session);
      clientList.add(session);
      System.out.println("접속명단 > "+clientList);
      
   }//f()
   
   @OnClose
   public void onClose(Session session) {
	   clientList.remove(session);
   }//f()
	 
   @OnMessage
   public void onMessage(Session session, String bprice) {
	   System.out.println("[서버소켓]클라이언트소켓으로부터 받은 금액> "+bprice);
	   
	   
	   
	   for(Session s :clientList) {
		   try {
			s.getBasicRemote().sendText(bprice);
		} catch (IOException e) {e.printStackTrace();System.out.println("클라이언트소켓으로부터 받은메시지 오류: "+e);}
	   }//f
	   
   }//f()
   
}//c
