package controller.member;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dto.AlarmDto;



@ServerEndpoint("/AlarmSocket/{mid}") //서버소켓
public class AlarmSocket {
	
	//들어와있는클라이언트소켓
	public static Map<Session,String> list = new HashMap<>();
	
	@OnOpen
	public void onOpen(Session session,@PathParam("mid") String mid) {
		System.out.println("알람소켓. java> 접속한 클라이언트 소켓 > " +session);
		System.out.println("접속한클라이언트소켓아이디> "+mid);
		list.put(session, mid);
		System.out.println("접속된소켓리스트> "+list);
		
	}//f()
	
	  @OnClose
	   public void onClose(Session session) {
		  //리스트안에 있는 모든 아이디를 배열로 반환.
		 list.remove(session);
		 System.out.println("한명 빠졌을때 list 줄어듦? > "+list);
		 
	  }//f()
	
	  @OnMessage
	  public void onMessage(Session session,String alarm) throws IOException {
		  
		  // 1. 받은 string -> dto 
		  ObjectMapper mapper = new ObjectMapper();
		  AlarmDto msg = mapper.readValue( alarm , AlarmDto.class );
		  System.out.println("22222");
		  // 2. 
		  list.keySet().forEach( (s)->{
			 
			  if( list.get( s ).equals( msg.getMid() ) ) {
				  
				  try {
					s.getBasicRemote().sendText(alarm);
					System.out.println("111111");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
			  
		  });
	  }//f()
	
}//c
