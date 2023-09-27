package controller.auction;

import java.util.ArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/BattingSocket") //서버소켓 
public class BattingSocket {

   public static ArrayList<Session> clientList = new ArrayList<>();
  
   @OnOpen
   public void onOpen(Session session) {
      System.out.println("접속한 클라이언트 소켓> "+session);
      System.out.println("접속명단 > "+clientList);
      
   }//f()
   
   @OnClose
   public void onClose(Session session) {
	   clientList.remove(session);
   }//f()
	 
   
   
}//c
