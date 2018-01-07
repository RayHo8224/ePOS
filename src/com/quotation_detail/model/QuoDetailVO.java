package com.quotation_detail.model;

import java.sql.Date;

import com.product.model.ProdVO;
import com.quotation.model.QuoVO;

public class QuoDetailVO implements java.io.Serializable{
//	private String quo_id;
	private String prod_name;
	private Integer prod_quantity;
	private String com_name;
	private  int prod_cost;
//	private Date delivery_date;
	private String remark;
//	private String key_id;
//	private Date key_date;
	
	//hibernate
	private QuoVO quoVO;
	
//	public String getQuo_id() {
//		return quo_id;
//	}
//	public void setQuo_id(String quo_id) {
//		this.quo_id = quo_id;
//	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	
	public QuoVO getQuoVO() {
		return quoVO;
	}
	public void setQuoVO(QuoVO quoVO) {
		this.quoVO = quoVO;
	}
	
	public Integer getProd_quantity() {
		return prod_quantity;
	}
	public void setProd_quantity(Integer prod_quantity) {
		this.prod_quantity = prod_quantity;
	}
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	public int getProd_cost() {
		return prod_cost;
	}
	public void setProd_cost(int prod_cost) {
		this.prod_cost = prod_cost;
	}
//	public Date getDelivery_date() {
//		return delivery_date;
//	}
//	public void setDelivery_date(Date delivery_date) {
//		this.delivery_date = delivery_date;
//	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
//	public String getKey_id() {
//		return key_id;
//	}
//	public void setKey_id(String key_id) {
//		this.key_id = key_id;
//	}
//	public Date getKey_date() {
//		return key_date;
//	}
//	public void setKey_date(Date key_date) {
//		this.key_date = key_date;
//	}

}
