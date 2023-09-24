package model.dto;

import javax.websocket.Session;

public class BattingDto {

	Session session; //클라이언트소켓
	int mno; //클라이언트소켓 사용하는 회원
	int ano; //경매글번호
	long bPrice; // 경매참여금액
	String bDate;  //참여날짜

	
	public BattingDto() {}


	public BattingDto(Session session, int mno, int ano, long bPrice, String bDate) {
		super();
		this.session = session;
		this.mno = mno;
		this.ano = ano;
		this.bPrice = bPrice;
		this.bDate = bDate;
	}


	public BattingDto(int mno, int ano, long bPrice, String bDate) {
		super();
		this.mno = mno;
		this.ano = ano;
		this.bPrice = bPrice;
		this.bDate = bDate;
	}


	public BattingDto(int mno, int ano, long bPrice) {
		super();
		this.mno = mno;
		this.ano = ano;
		this.bPrice = bPrice;
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


	public long getbPrice() {
		return bPrice;
	}


	public void setbPrice(long bPrice) {
		this.bPrice = bPrice;
	}


	public String getbDate() {
		return bDate;
	}


	public void setbDate(String bDate) {
		this.bDate = bDate;
	}


	@Override
	public String toString() {
		return "BattingDto [session=" + session + ", mno=" + mno + ", ano=" + ano + ", bPrice=" + bPrice + ", bDate="
				+ bDate + "]";
	}



}//c
