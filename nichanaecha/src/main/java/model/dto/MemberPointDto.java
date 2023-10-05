package model.dto;

public class MemberPointDto {
	private String pointno;
	private int mno;
	private long mpoint;
	private String pointdate;
	private String pointhistory;
	
	public MemberPointDto() {	}

	public MemberPointDto(String pointno, int mno, long mpoint, String pointdate, String pointhistory) {
		super();
		this.pointno = pointno;
		this.mno = mno;
		this.mpoint = mpoint;
		this.pointdate = pointdate;
		this.pointhistory = pointhistory;
	}

	public String getPointno() {
		return pointno;
	}

	public void setPointno(String pointno) {
		this.pointno = pointno;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public long getMpoint() {
		return mpoint;
	}

	public void setMpoint(long mpoint) {
		this.mpoint = mpoint;
	}

	public String getPointdate() {
		return pointdate;
	}

	public void setPointdate(String pointdate) {
		this.pointdate = pointdate;
	}

	public String getPointhistory() {
		return pointhistory;
	}

	public void setPointhistory(String pointhistory) {
		this.pointhistory = pointhistory;
	}

	@Override
	public String toString() {
		return "MemberPointDto [pointno=" + pointno + ", mno=" + mno + ", mpoint=" + mpoint + ", pointdate=" + pointdate
				+ ", pointhistory=" + pointhistory + "]";
	}

	
	
}
