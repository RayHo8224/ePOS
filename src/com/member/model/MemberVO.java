package com.member.model;

import java.sql.Date;

public class MemberVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String mem_id;
	private String mem_pwd;
	private String mem_name;
	private String mem_idnum;
	private Date mem_bday;
	private String mem_phone;
	private String mem_addr;
	private String mem_mail;
	private Date mem_due;
	private String key_id;
	private Date key_date;
	private String mem_um;
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_pwd() {
		return mem_pwd;
	}
	public void setMem_pwd(String mem_pwd) {
		this.mem_pwd = mem_pwd;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_idnum() {
		return mem_idnum;
	}
	public void setMem_idnum(String mem_idnum) {
		this.mem_idnum = mem_idnum;
	}
	public Date getMem_bday() {
		return mem_bday;
	}
	public void setMem_bday(Date mem_bday) {
		this.mem_bday = mem_bday;
	}
	public String getMem_phone() {
		return mem_phone;
	}
	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}
	public String getMem_addr() {
		return mem_addr;
	}
	public void setMem_addr(String mem_addr) {
		this.mem_addr = mem_addr;
	}
	public String getMem_mail() {
		return mem_mail;
	}
	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}
	public Date getMem_due() {
		return mem_due;
	}
	public void setMem_due(Date mem_due) {
		this.mem_due = mem_due;
	}
	public String getKey_id() {
		return key_id;
	}
	public void setKey_id(String key_id) {
		this.key_id = key_id;
	}
	public Date getKey_date() {
		return key_date;
	}
	public void setKey_date(Date key_date) {
		this.key_date = key_date;
	}
	public String getMem_um() {
		return mem_um;
	}
	public void setMem_um(String mem_um) {
		this.mem_um = mem_um;
	}
	
}
