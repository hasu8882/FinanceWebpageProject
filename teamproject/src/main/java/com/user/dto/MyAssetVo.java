package com.user.dto;

import java.sql.Date;

public class MyAssetVo extends AssetVo{
	private String userid;
	private int qty;
	private int tot_amount;
	private Date last_update;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getTot_amount() {
		return tot_amount;
	}
	public void setTot_amount() {
		this.tot_amount = this.getQty()*super.getPrice();
	}
	
	
	public Date getLast_update() {
		return last_update;
	}
	public void setLast_update(Date last_update) {
		this.last_update = last_update;
	}
	@Override
	public String toString() {
		return "MyAssetVo [userid=" + userid + ", qty=" + qty + ", tot_amount=" + tot_amount + ", last_update="
				+ last_update + "]";
	}
	
	
	
	

}
