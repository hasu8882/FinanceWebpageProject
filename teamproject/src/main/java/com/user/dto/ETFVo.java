package com.user.dto;

import java.util.Arrays;

public class ETFVo extends AssetVo{
	private int range_day;
	private float change_day;
	private double NAV;
	private int open;
	private int high;
	private int low;
	private int volume;
	private double tradeAMT;
	private double marketCap;
	private double netAsset;
	private int qty;
	private String indexName;
	private float closeIndex;
	private float rangeIndex;
	private float changeIndex;
	private String[] fieldNames;
	public int getRange_day() {
		return range_day;
	}
	public void setRange_day(int range_day) {
		this.range_day = range_day;
	}
	public float getChange_day() {
		return change_day;
	}
	public void setChange_day(float change_day) {
		this.change_day = change_day;
	}
	public double getNAV() {
		return NAV;
	}
	public void setNAV(double nAV) {
		NAV = nAV;
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
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public double getTradeAMT() {
		return tradeAMT;
	}
	public void setTradeAMT(double tradeAMT) {
		this.tradeAMT = tradeAMT;
	}
	public double getMarketCap() {
		return marketCap;
	}
	public void setMarketCap(double marketCap) {
		this.marketCap = marketCap;
	}
	public double getNetAsset() {
		return netAsset;
	}
	public void setNetAsset(double netAsset) {
		this.netAsset = netAsset;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getIndexName() {
		return indexName;
	}
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	public float getCloseIndex() {
		return closeIndex;
	}
	public void setCloseIndex(float closeIndex) {
		this.closeIndex = closeIndex;
	}
	public float getRangeIndex() {
		return rangeIndex;
	}
	public void setRangeIndex(float rangeIndex) {
		this.rangeIndex = rangeIndex;
	}
	public float getChangeIndex() {
		return changeIndex;
	}
	public void setChangeIndex(float changeIndex) {
		this.changeIndex = changeIndex;
	}
	public String[] getFieldNames() {
		return fieldNames;
	}
	public void setFieldNames(String[] fieldNames) {
		this.fieldNames = fieldNames;
	}
	@Override
	public String toString() {
		return "ETFVo [range_day=" + range_day + ", change_day=" + change_day + ", NAV=" + NAV + ", open=" + open
				+ ", high=" + high + ", low=" + low + ", volume=" + volume + ", tradeAMT=" + tradeAMT + ", marketCap="
				+ marketCap + ", netAsset=" + netAsset + ", qty=" + qty + ", indexName=" + indexName + ", closeIndex="
				+ closeIndex + ", rangeIndex=" + rangeIndex + ", changeIndex=" + changeIndex + ", fieldNames="
				+ Arrays.toString(fieldNames) + "]";
	}
	
	
	
	
}
