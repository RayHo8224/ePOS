package com.company.model;

import java.io.Serializable;
import java.sql.Date;


public class ComVO implements Serializable {
	private  String com_id;
	private  String com_name;
	private  String com_um;
	private  String com_phone;
	private  String com_addr;
	private  String com_mail;
	private  String picture;
	private  String key_id;
	private  Date   key_date;
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	public String getCom_um() {
		return com_um;
	}
	public void setCom_um(String com_um) {
		this.com_um = com_um;
	}
	public String getCom_phone() {
		return com_phone;
	}
	public void setCom_phone(String com_phone) {
		this.com_phone = com_phone;
	}
	public String getCom_addr() {
		return com_addr;
	}
	public void setCom_addr(String com_addr) {
		this.com_addr = com_addr;
	}
	public String getCom_mail() {
		return com_mail;
	}
	public void setCom_mail(String com_mail) {
		this.com_mail = com_mail;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
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
	
	

}

//key_date		 date not null