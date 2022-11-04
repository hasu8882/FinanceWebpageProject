package com.user.dto;

public class FactorVo {
	
	private float perL;
	private float perU;
	private float pbrL;
	private float pbrU;
	private float divYield;
	private int nStocks;
	
	
	public FactorVo() {
		this.perL = 0;
		this.perU = 1000;
		this.pbrL = 0;
		this.pbrU = 100;
		this.nStocks = 50;
		this.divYield = 0;
	}
	public float getPerL() {
		return perL;
	}
	public void setPerL(float perL) {
		this.perL = perL;
	}
	public float getPerU() {
		return perU;
	}
	public void setPerU(float perU) {
		this.perU = perU;
	}
	public float getPbrL() {
		return pbrL;
	}
	public void setPbrL(float pbrL) {
		this.pbrL = pbrL;
	}
	public float getPbrU() {
		return pbrU;
	}
	public void setPbrU(float pbrU) {
		this.pbrU = pbrU;
	}
	public int getnStocks() {
		return nStocks;
	}
	public void setnStocks(int nStocks) {
		this.nStocks = nStocks;
	}
	
	
	public float getDivYield() {
		return divYield;
	}
	public void setDivYield(float divYield) {
		this.divYield = divYield;
	}
	
	@Override
	public String toString() {
		return "FactorVo [perL=" + perL + ", perU=" + perU + ", pbrL=" + pbrL + ", pbrU=" + pbrU + ", divYield="
				+ divYield + ", nStocks=" + nStocks + "]";
	}
	
	
	
	
	

}
