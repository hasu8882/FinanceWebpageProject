package com.user.dto;

public class OutlayVo {
	private String id;
	private int num;
	private String use1;
	private int cash;
	private int card;
	private String used_disposal;
	private String date1;
	private String memo1;
	
	//update 할 때 사용하는 임시 필드
	private int old_num;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getUse1() {
		return use1;
	}
	public void setUse1(String use1) {
		this.use1 = use1;
	}
	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
	public int getCard() {
		return card;
	}
	public void setCard(int card) {
		this.card = card;
	}
	public String getUsed_disposal() {
		return used_disposal;
	}
	public void setUsed_disposal(String used_disposal) {
		this.used_disposal = used_disposal;
	}
	public String getDate1() {
		return date1;
	}
	public void setDate1(String date1) {
		this.date1 = date1;
	}
	public String getMemo1() {
		return memo1;
	}
	public void setMemo1(String memo1) {
		this.memo1 = memo1;
	}
	public int getOld_num() {
		return old_num;
	}
	public void setOld_num(int old_num) {
		this.old_num = old_num;
	}
	
	
}
