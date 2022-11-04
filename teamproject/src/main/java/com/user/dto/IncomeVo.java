package com.user.dto;

public class IncomeVo {
	private String id;
	private int num;
	private String use2;
	private int amount;
	private String bankbook;
	private String import_disposal;
	private String date2;
	private String memo2;
	
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
	public String getUse2() {
		return use2;
	}
	public void setUse2(String use2) {
		this.use2 = use2;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getBankbook() {
		return bankbook;
	}
	public void setBankbook(String bankbook) {
		this.bankbook = bankbook;
	}
	public String getImport_disposal() {
		return import_disposal;
	}
	public void setImport_disposal(String import_disposal) {
		this.import_disposal = import_disposal;
	}
	public String getDate2() {
		return date2;
	}
	public void setDate2(String date2) {
		this.date2 = date2;
	}
	public String getMemo2() {
		return memo2;
	}
	public void setMemo2(String memo2) {
		this.memo2 = memo2;
	}
	public int getOld_num() {
		return old_num;
	}
	public void setOld_num(int old_num) {
		this.old_num = old_num;
	}
	
	
}
