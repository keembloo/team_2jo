package model.dto;

import javax.websocket.Session;

public class AlarmDto {
	private String alarmdate;	//날짜
	private String alarmcontent; 	
	private	 int mno;	//받는사람
	


//DB JOIN 사용
	private Session alramsession;
	private String mid;	//클라이언트소켓 아이디
	private String buyMid; // 입찰자 아이디
	private String alarmtype; // 안쓸거임,,;
	

// 생성자
public AlarmDto() {}


public AlarmDto(Session alramsession, String alarmdate, String alarmcontent, int mno, String mid, String buyMid,
		String alarmtype) {
	super();
	this.alramsession = alramsession;
	this.alarmdate = alarmdate;
	this.alarmcontent = alarmcontent;
	this.mno = mno;
	this.mid = mid;
	this.buyMid = buyMid;
	this.alarmtype = alarmtype;
}


// 입찰자에게 출금 상태 메시지 알림(등록) [10월7일 고연진]---------------------
public AlarmDto(String alarmcontent, int mno) {
	super();
	this.alarmcontent = alarmcontent;
	this.mno = mno;
}


public String getAlarmdate() {
	return alarmdate;
}


public void setAlarmdate(String alarmdate) {
	this.alarmdate = alarmdate;
}


public String getAlarmcontent() {
	return alarmcontent;
}


public void setAlarmcontent(String alarmcontent) {
	this.alarmcontent = alarmcontent;
}


public int getMno() {
	return mno;
}


public void setMno(int mno) {
	this.mno = mno;
}


public Session getAlramsession() {
	return alramsession;
}


public void setAlramsession(Session alramsession) {
	this.alramsession = alramsession;
}


public String getMid() {
	return mid;
}


public void setMid(String mid) {
	this.mid = mid;
}


public String getBuyMid() {
	return buyMid;
}


public void setBuyMid(String buyMid) {
	this.buyMid = buyMid;
}


public String getAlarmtype() {
	return alarmtype;
}


public void setAlarmtype(String alarmtype) {
	this.alarmtype = alarmtype;
}


@Override
public String toString() {
	return "AlarmDto [alarmdate=" + alarmdate + ", alarmcontent=" + alarmcontent + ", mno=" + mno + ", alramsession="
			+ alramsession + ", mid=" + mid + ", buyMid=" + buyMid + ", alarmtype=" + alarmtype + "]";
}


	
	
}
