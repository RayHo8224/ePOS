package com.valuation.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.order_detail.model.Order_DetailVO;
import com.valuation_detail.model.Valuation_DetailVO;

public class ValuationVO implements Serializable{

	private String vlt_id;
	private Date vlt_date;
	private Date delivery_date;
	private double total_price;
	private String status;
	private String remark;
	private String key_id;
	private Date key_date;
	private Date exp_date;
	
	// hibernate
	private Set<Valuation_DetailVO> valuationdetails = new HashSet<Valuation_DetailVO>();

	public Set<Valuation_DetailVO> getValuationdetails() {
		return valuationdetails;
	}

	public void setValuationdetails(Set<Valuation_DetailVO> valuationdetails) {
		this.valuationdetails = valuationdetails;
	}
	
	public String getVlt_id() {
		return vlt_id;
	}
	public void setVlt_id(String vlt_id) {
		this.vlt_id = vlt_id;
	}
	public Date getVlt_date() {
		return vlt_date;
	}
	public void setVlt_date(Date vlt_date) {
		this.vlt_date = vlt_date;
	}
	public Date getDelivery_date() {
		return delivery_date;
	}
	public void setDelivery_date(Date delivery_date) {
		this.delivery_date = delivery_date;
	}
	public double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public Date getExp_date() {
		return exp_date;
	}
	public void setExp_date(Date exp_date) {
		this.exp_date = exp_date;
	}
	
}
