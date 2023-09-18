package model.dto;

public class AuctionDto {
	// 필드
	String btitle;
	String bprice;
	String bfile;
	int mno;
	int bcno;
	
	//생성자
	public AuctionDto() {}	//빈생성자
		//풀생성자
		public AuctionDto(String btitle, String bprice, String bfile, int mno, int bcno) {
			super();
			this.btitle = btitle;
			this.bprice = bprice;
			this.bfile = bfile;
			this.mno = mno;
			this.bcno = bcno;
		}
		
	
	//메소드
		public String getBtitle() {
			return btitle;
		}
		public void setBtitle(String btitle) {
			this.btitle = btitle;
		}
		public String getBprice() {
			return bprice;
		}
		public void setBprice(String bprice) {
			this.bprice = bprice;
		}
		public String getBfile() {
			return bfile;
		}
		public void setBfile(String bfile) {
			this.bfile = bfile;
		}
		public int getMno() {
			return mno;
		}
		public void setMno(int mno) {
			this.mno = mno;
		}
		public int getBcno() {
			return bcno;
		}
		public void setBcno(int bcno) {
			this.bcno = bcno;
		}
		
		
		@Override
		public String toString() {
			return "AuctionDto [btitle=" + btitle + ", bprice=" + bprice + ", bfile=" + bfile + ", mno=" + mno
					+ ", bcno=" + bcno + "]";
		}
	
		
}
