package com.user.dto;

public class SumVo {
 String id;
 int Sumcash;
 int Sumcard;
 String  used_disposal;
 
public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public int getSumcash() {
	return Sumcash;
}

public void setSumcash(int sumcash) {
	Sumcash = sumcash;
}

public int getSumcard() {
	return Sumcard;
}

public void setSumcard(int sumcard) {
	Sumcard = sumcard;
}

public String getUsed_disposal() {
	return used_disposal;
}

public void setUsed_disposal(String used_disposal) {
	this.used_disposal = used_disposal;
}

@Override
public String toString() {
	return "SumVo [id=" + id + ", Sumcash=" + Sumcash + ", Sumcard=" + Sumcard + ", used_disposal=" + used_disposal
			+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
}
 
}
