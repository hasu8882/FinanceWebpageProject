package com.user.dto;

import java.sql.Date;

public class AssetVo {
	private String code;
	private String name;
	private int price;
	private Date last_update;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getLast_update() {
		return last_update;
	}
	public void setLast_update(Date last_update) {
		this.last_update = last_update;
	}
	@Override
	public String toString() {
		return "AssetVo [code=" + code + ", name=" + name + ", price=" + price + ", last_update=" + last_update + "]";
	}
	
	

}
