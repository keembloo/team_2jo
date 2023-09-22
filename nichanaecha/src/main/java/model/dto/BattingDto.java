package model.dto;

import javax.websocket.Session;

public class BattingDto {

	Session session; //클라이언트소켓
	int mno; //클라이언트소켓 사용하는 회원
	int ano; //경매글번호
	long batPay; // 경매참여금액
	String batDate;  //참여날짜

	
	public BattingDto() {}

	public BattingDto(Session session, int mno, int ano, long batPay, String batDate) {
		super();
		this.session = session;
		this.mno = mno;
		this.ano = ano;
		this.batPay = batPay;
		this.batDate = batDate;
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

	public long getBatPay() {
		return batPay;
	}

	public void setBatPay(long batPay) {
		this.batPay = batPay;
	}

	public String getBatDate() {
		return batDate;
	}

	public void setBatDate(String batDate) {
		this.batDate = batDate;
	}

	@Override
	public String toString() {
		return "BattingDto [session=" + session + ", mno=" + mno + ", ano=" + ano + ", batPay=" + batPay + ", batDate="
				+ batDate + "]";
	}







}//c
