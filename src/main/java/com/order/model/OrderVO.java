package com.order.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.order_detail.model.Order_DetailVO;

public class OrderVO implements Serializable {

	private String ord_id;
	private String vlt_id;
	private String mem_id;
	private java.sql.Date ord_date;
	private String discount;
	private double total_price;
	private double cash;
	private String status;
	private String invoice_id;
	private String ord_um;
	private String cpon_id;
	private int cpon_dollar;
	private String remark;
	private String shift;
	private String key_id;
	private Date key_date;
	private String weather;

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	// hibernate
	private Set<Order_DetailVO> orderdetails = new HashSet<Order_DetailVO>();

	public Set<Order_DetailVO> getOrderdetails() {
		return orderdetails;
	}

	public void setOrderdetails(Set<Order_DetailVO> orderdetails) {
		this.orderdetails = orderdetails;
	}

	public String getOrd_id() {
		return ord_id;
	}

	public void setOrd_id(String ord_id) {
		this.ord_id = ord_id;
	}

	public String getVlt_id() {
		return vlt_id;
	}

	public void setVlt_id(String vlt_id) {
		this.vlt_id = vlt_id;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String member_id) {
		this.mem_id = member_id;
	}

	public java.sql.Date getOrd_date() {
		return ord_date;
	}

	public void setOrd_date(java.sql.Date ord_date) {
		this.ord_date = ord_date;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}

	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
	}

	public String getOrd_um() {
		return ord_um;
	}

	public void setOrd_um(String ord_um) {
		this.ord_um = ord_um;
	}

	public String getCpon_id() {
		return cpon_id;
	}

	public void setCpon_id(String cpon_id) {
		this.cpon_id = cpon_id;
	}

	public int getCpon_dollar() {
		return cpon_dollar;
	}

	public void setCpon_dollar(int cpon_dollar) {
		this.cpon_dollar = cpon_dollar;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
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
