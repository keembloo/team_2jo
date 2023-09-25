package controller.auction;

import java.util.ArrayList;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/BattingSocket") //서버소켓
public class BattingSocket {

	public static ArrayList<Session> batMember = new ArrayList<>();
	@OnOpen
	
	
	
}//c
