package model.dto;

import javax.websocket.Session;

public class BattingDto {

	Session session; //클라이언트소켓
	int mno; //클라이언트소켓 사용하는 회원
	int ano; //경매글번호
	long bprice; // 경매참여금액
	String bDate;  //참여날짜
	long mcash; // 회원 보유 포인트

	
	String mid;//입찰회원아이디
	String amid;//안쓸꺼임,, 

	
//회원이 참여한 모든 경매글 가져올 때 사용
	
	
	public BattingDto(int mno, int ano) {
		super();
		this.mno = mno;
		this.ano = ano;
	}
	
	
	
//입찰관련 알람 보낼 시 사용
	
	public BattingDto(Session session, long bprice, String mid) {
		super();
		this.session = session;
		this.bprice = bprice;
		this.mid = mid;
	}

	public String getAmid() {
		return amid;
	}
	public void setAmid(String amid) {
		this.amid = amid;
	}	
	
	
	
	
	
//입찰 등록 보낼 시 사용 할 생성자
	
	public BattingDto(int mno, int ano, long bprice) {
		super();
		this.mno = mno;
		this.ano = ano;
		this.bprice = bprice;
	}
	
//상위 게시글 경매내역출력 생성자
	


	public BattingDto(long bprice, String bDate) {
		super();
		this.bprice = bprice;
		this.bDate = bDate;
	}
	
	
//전체 출력에 필요한 생성자
	
	public BattingDto(int mno, int ano, long bprice, String bDate, String mid) {
		super();
		this.mno = mno;
		this.ano = ano;
		this.bprice = bprice;
		this.bDate = bDate;
		this.mid = mid;
	}
	
	
//최근 입찰자에게 금액 돌려주기 위해 만든 함수 사용[10월6일 고연진]


public BattingDto(int mno, long bprice) {
	super();
	this.mno = mno;
	this.bprice = bprice;
}

 
	
	
	
	
//getter/setter	
public String getMid() {
		return mid;
	}


	public void setMid(String mid) {
		this.mid = mid;
	}

	
	
public BattingDto() {}


public BattingDto(Session session, int mno, int ano, long bprice, String bDate, long mcash) {
	super();
	this.session = session;
	this.mno = mno;
	this.ano = ano;
	this.bprice = bprice;
	this.bDate = bDate;
	this.mcash = mcash;
}

public Session getSession() {
	return session;
}

public void setSession(Session session) {
	this.session = session;
}

public int getMno() {
	return mno;
}

public void setMno(int mno) {
	this.mno = mno;
}

public int getAno() {
	return ano;
}

public void setAno(int ano) {
	this.ano = ano;
}

public long getBprice() {
	return bprice;
}

public void setBprice(long bprice) {
	this.bprice = bprice;
}

public String getbDate() {
	return bDate;
}

public void setbDate(String bDate) {
	this.bDate = bDate;
}

public long getMcash() {
	return mcash;
}

public void setMcash(long mcash) {
	this.mcash = mcash;
}

@Override
public String toString() {
	return "BattingDto [session=" + session + ", mno=" + mno + ", ano=" + ano + ", bprice=" + bprice + ", bDate="
			+ bDate + ", mcash=" + mcash + ", mid=" + mid + ", amid=" + amid + "]";
}



}//c
