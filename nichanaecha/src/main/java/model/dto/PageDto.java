package model.dto;

import java.util.ArrayList;

public class PageDto {
	
	private int page; //현재페이지 번호
	private int listsize; // 페이지당 최대게시물수
	private int startrow; //현재 페이지에서 시작되는 게시물 
	private int totalsize; // 총 게시물수 or 카테고리별 게시물수
	private int totalpage; //총 페이지수
	private int startbtn;	// 페이지 번호버튼 시작번호 
	private int endbtn;		// 페이지 번호버튼 끝번호
	
	ArrayList<MemberPointDto> pointList;
	
	public PageDto() {	}

	public PageDto(int page, int listsize, int startrow, int totalsize, int totalpage, int startbtn, int endbtn,
			ArrayList<MemberPointDto> pointList) {
		super();
		this.page = page;
		this.listsize = listsize;
		this.startrow = startrow;
		this.totalsize = totalsize;
		this.totalpage = totalpage;
		this.startbtn = startbtn;
		this.endbtn = endbtn;
		this.pointList = pointList;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getListsize() {
		return listsize;
	}

	public void setListsize(int listsize) {
		this.listsize = listsize;
	}

	public int getStartrow() {
		return startrow;
	}

	public void setStartrow(int startrow) {
		this.startrow = startrow;
	}

	public int getTotalsize() {
		return totalsize;
	}

	public void setTotalsize(int totalsize) {
		this.totalsize = totalsize;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public int getStartbtn() {
		return startbtn;
	}

	public void setStartbtn(int startbtn) {
		this.startbtn = startbtn;
	}

	public int getEndbtn() {
		return endbtn;
	}

	public void setEndbtn(int endbtn) {
		this.endbtn = endbtn;
	}

	public ArrayList<MemberPointDto> getPointList() {
		return pointList;
	}

	public void setPointList(ArrayList<MemberPointDto> pointList) {
		this.pointList = pointList;
	}

	@Override
	public String toString() {
		return "PageDto [page=" + page + ", listsize=" + listsize + ", startrow=" + startrow + ", totalsize="
				+ totalsize + ", totalpage=" + totalpage + ", startbtn=" + startbtn + ", endbtn=" + endbtn
				+ ", pointList=" + pointList + "]";
	}
	
	
	
}
