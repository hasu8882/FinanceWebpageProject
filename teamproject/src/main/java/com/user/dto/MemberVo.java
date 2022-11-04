package com.user.dto;

public class MemberVo {

	private String id;
	private String pw;
	private String name;
	private String phone1;
	private String phone2;
	private String phone3;
	private String email;
	private int pay;
	private int gender;
	private int admin;
	
	
	public MemberVo() {
		
	}
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getPhone3() {
		return phone3;
	}
	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPay() {
		return pay;
	}
	public void setPay(int pay) {
		this.pay = pay;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	@Override
	public String toString() {
		return "MemberVo [id=" + id + ", pw=" + pw + ", name=" + name + ", phone1=" + phone1 + ", phone2=" + phone2
				+ ", phone3=" + phone3 + ", email=" + email + ", pay=" + pay + ", gender=" + gender + ", admin=" + admin
				+ "]";
	}
	
	
}	