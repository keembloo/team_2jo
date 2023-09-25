package controller.auction;

import java.util.ArrayList;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/BattingSocket/{mno}") //서버소켓 
public class BattingSocket {

   public static ArrayList<Session> batMember = new ArrayList<>();
   @OnOpen
   public void onOpen(Session session, @PathParam("mno") String mno) {
      System.out.println("접속한 클라이언트 소켓> "+session);
      System.out.println("참여 중인 회원> "+mno);
      
   }//f()
   
   @OnClose
   public
	
}//c
