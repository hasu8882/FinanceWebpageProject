package com.user.dto;

import java.sql.Date;

public class DailyAsset extends AssetVo{
	
	private Date date;
	private int close;
	private int open;
	private int high;
	private int low;
	private long tradeVol;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getClose() {
		return close;
	}
	public void setClose(int close) {
		this.close = close;
	}
	public int getOpen() {
		return open;
	}
	public void setOpen(int open) {
		this.open = open;
	}
	public int getHigh() {
		return high;
	}
	public void setHigh(int high) {
		this.high = high;
	}
	public int getLow() {
		return low;
	}
	public void setLow(int low) {
		this.low = low;
	}
	public long getTradeVol() {
		return tradeVol;
	}
	public void setTradeVol(long tradeVol) {
		this.tradeVol = tradeVol;
	}
	@Override
	public String toString() {
		return "DailyAsset [date=" + date + ", close=" + close + ", open=" + open + ", high=" + high + ", low=" + low
				+ ", tradeVol=" + tradeVol + "]";
	}
	
	

}
