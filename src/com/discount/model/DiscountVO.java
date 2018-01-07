package com.discount.model;

public class DiscountVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String dis_id;
	private Float dis_price;
	
	public String getDis_id() {
		return dis_id;
	}
	public void setDis_id(String dis_id) {
		this.dis_id = dis_id;
	}
	public Float getDis_price() {
		return dis_price;
	}
	public void setDis_price(Float dis_price) {
		this.dis_price = dis_price;
	}
	
}
