package com.user.dto;

public class StockVo extends AssetVo{
	private float range_day;
	private float change_day;
	private int EPS;
	private float PER;
	private int fwdEPS;
	private float fwdPER;
	private int BPS;
	private float PBR;
	private int dividend;
	private float dividend_yield;
	private String maketType;
	
	
	public String getMaketType() {
		return maketType;
	}
	public void setMaketType(String maketType) {
		this.maketType = maketType;
	}
	public float getRange_day() {
		return range_day;
	}
	public void setRange_day(float range_day) {
		this.range_day = range_day;
	}
	public float getChange_day() {
		return change_day;
	}
	public void setChange_day(float change_day) {
		this.change_day = change_day;
	}
	public int getEPS() {
		return EPS;
	}
	public void setEPS(int ePS) {
		EPS = ePS;
	}
	public float getPER() {
		return PER;
	}
	public void setPER(float pER) {
		PER = pER;
	}
	public int getFwdEPS() {
		return fwdEPS;
	}
	public void setFwdEPS(int fwdEPS) {
		this.fwdEPS = fwdEPS;
	}
	public float getFwdPER() {
		return fwdPER;
	}
	public void setFwdPER(float fwdPER) {
		this.fwdPER = fwdPER;
	}
	public int getBPS() {
		return BPS;
	}
	public void setBPS(int bPS) {
		BPS = bPS;
	}
	public float getPBR() {
		return PBR;
	}
	public void setPBR(float pBR) {
		PBR = pBR;
	}
	public int getDividend() {
		return dividend;
	}
	public void setDividend(int dividend) {
		this.dividend = dividend;
	}
	public float getDividend_yield() {
		return dividend_yield;
	}
	public void setDividend_yield(float dividend_yield) {
		this.dividend_yield = dividend_yield;
	}
	
	
	@Override
	public String toString() {
		return "StockVo [range_day=" + range_day + ", change_day=" + change_day + ", EPS=" + EPS + ", PER=" + PER
				+ ", fwdEPS=" + fwdEPS + ", fwdPER=" + fwdPER + ", BPS=" + BPS + ", PBR=" + PBR + ", dividend="
				+ dividend + ", dividend_yield=" + dividend_yield + ", maketType=" + maketType + "]";
	}
	
	
	
	
	

}
