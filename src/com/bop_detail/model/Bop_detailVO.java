package com.bop_detail.model;

import com.bop.model.BopVO;

public class Bop_detailVO implements java.io.Serializable {
	private String bop_id;
	private String prod_id;
	private String prod_name;
	private Integer prod_quantity;
	private Integer prod_price;
	private Integer prod_lsum;
	public Integer getProd_lsum() {
		return prod_lsum;
	}
	public void setProd_lsum(Integer prod_lsum) {
		this.prod_lsum = prod_lsum;
	}
	private BopVO bopVO;
	
	public BopVO getBopVO() {
		return bopVO;
	}
	public void setBopVO(BopVO bopVO) {
		this.bopVO = bopVO;
	}
	public String getBop_id() {
		return bop_id;
	}
	public void setBop_id(String bop_id) {
		this.bop_id = bop_id;
	}
	public String getProd_id() {
		return prod_id;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public Integer getProd_quantity() {
		return prod_quantity;
	}
	public void setProd_quantity(Integer prod_quantity) {
		this.prod_quantity = prod_quantity;
	}
	public Integer getProd_price() {
		return prod_price;
	}
	public void setProd_price(Integer prod_price) {
		this.prod_price = prod_price;
	}
	

}
