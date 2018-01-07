package com.product.model;

import java.io.Serializable;
import java.sql.Date;


public class ProdVO implements Serializable {
	
	private  String prod_id;
	private  String prod_name;
	private  String com_id;
	private  String prod_group;
	private  int prod_mkprice;
	private  int prod_cost;
	private  int prod_stock;
	private  int prod_q_safty;
	private  String   prod_spec;
	private  String   picture;
	private  String   remark;
	private  String   status;
	
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
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	public String getProd_group() {
		return prod_group;
	}
	public void setProd_group(String prod_group) {
		this.prod_group = prod_group;
	}
	public int getProd_mkprice() {
		return prod_mkprice;
	}
	public void setProd_mkprice(int prod_mkprice) {
		this.prod_mkprice = prod_mkprice;
	}
	public int getProd_cost() {
		return prod_cost;
	}
	public void setProd_cost(int prod_cost) {
		this.prod_cost = prod_cost;
	}
	public int getProd_stock() {
		return prod_stock;
	}
	public void setProd_stock(int prod_stock) {
		this.prod_stock = prod_stock;
	}
	public int getProd_q_safty() {
		return prod_q_safty;
	}
	public void setProd_q_safty(int prod_q_safty) {
		this.prod_q_safty = prod_q_safty;
	}
	public String getProd_spec() {
		return prod_spec;
	}
	public void setProd_spec(String prod_spec) {
		this.prod_spec = prod_spec;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


}
