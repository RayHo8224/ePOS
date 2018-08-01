package com.promoting.model;

import java.sql.Date;

public class PromotingVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String pro_prod_id;
	private String pro_prod_name;
	private Date pro_begin;
	private Date pro_end;
	private String pro_neirong;
	
	public String getPro_prod_id() {
		return pro_prod_id;
	}
	public void setPro_prod_id(String pro_prod_id) {
		this.pro_prod_id = pro_prod_id;
	}
	public String getPro_prod_name() {
		return pro_prod_name;
	}
	public void setPro_prod_name(String pro_prod_name) {
		this.pro_prod_name = pro_prod_name;
	}
	public Date getPro_begin() {
		return pro_begin;
	}
	public void setPro_begin(Date pro_begin) {
		this.pro_begin = pro_begin;
	}
	public Date getPro_end() {
		return pro_end;
	}
	public void setPro_end(Date pro_end) {
		this.pro_end = pro_end;
	}
	public String getPro_neirong() {
		return pro_neirong;
	}
	public void setPro_neirong(String pro_neirong) {
		this.pro_neirong = pro_neirong;
	}	
	
}
