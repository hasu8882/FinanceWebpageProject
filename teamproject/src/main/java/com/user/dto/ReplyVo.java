package com.user.dto;

import java.sql.Date;

public class ReplyVo {
	int code;
	int comment_code;
	String id;
	String content;
	Date reg_date;
	@Override
	public String toString() {
		return "ReplyVo [code=" + code + ", comment_code=" + comment_code + ", id=" + id + ", content=" + content
				+ ", reg_date=" + reg_date + "]";
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getComment_code() {
		return comment_code;
	}
	public void setComment_code(int comment_code) {
		this.comment_code = comment_code;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
}
