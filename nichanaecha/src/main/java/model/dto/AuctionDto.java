package model.dto;

public class AuctionDto {
	private int ano;			//#경매 번호
	private String atitle;		//#경매 제목 
	private String acontent;	// #경매 내용 
	private String astartdate;	// #경매 등록 날짜 
	private String aenddate;	// #경매 종료 날짜 
	private int aprice;			// #경매 등록 가격 
	private int astate;			//  #경매 상태 [ 0: 경매중, 1:거래중(낙찰 후 판매자와 거래중) 2:경매 종료
	


	public AuctionDto() {}
	
	
	public AuctionDto(int ano, String atitle, String acontent, String astartdate, String aenddate, int aprice,
			int astate) {
		super();
		this.ano = ano;
		this.atitle = atitle;
		this.acontent = acontent;
		this.astartdate = astartdate;
		this.aenddate = aenddate;
		this.aprice = aprice;
		this.astate = astate;
	}
	
	
	// 규리 마이페이지에서 등록매물정보 출력을 위한 생성자
	// 풀생성자에서 경매 내용, 경매 등록날짜만 빠짐 
	public AuctionDto(int ano, String atitle, String aenddate, int aprice, int astate) {
		super();
		this.ano = ano;
		this.atitle = atitle;
		this.aenddate = aenddate;
		this.aprice = aprice;
		this.astate = astate;
	}


	public int getAno() {
		return ano;
	}


	public void setAno(int ano) {
		this.ano = ano;
	}


	public String getAtitle() {
		return atitle;
	}


	public void setAtitle(String atitle) {
		this.atitle = atitle;
	}


	public String getAcontent() {
		return acontent;
	}


	public void setAcontent(String acontent) {
		this.acontent = acontent;
	}


	public String getAstartdate() {
		return astartdate;
	}


	public void setAstartdate(String astartdate) {
		this.astartdate = astartdate;
	}


	public String getAenddate() {
		return aenddate;
	}


	public void setAenddate(String aenddate) {
		this.aenddate = aenddate;
	}


	public int getAprice() {
		return aprice;
	}


	public void setAprice(int aprice) {
		this.aprice = aprice;
	}


	public int getAstate() {
		return astate;
	}


	public void setAstate(int astate) {
		this.astate = astate;
	}


	@Override
	public String toString() {
		return "AuctionDto [ano=" + ano + ", atitle=" + atitle + ", acontent=" + acontent + ", astartdate=" + astartdate
				+ ", aenddate=" + aenddate + ", aprice=" + aprice + ", astate=" + astate + "]";
	}
	
	
}
