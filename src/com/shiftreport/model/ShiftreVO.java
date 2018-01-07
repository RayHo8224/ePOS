package com.shiftreport.model;

import java.io.Serializable;
import java.sql.Date;

public class ShiftreVO implements Serializable {
	private  Date Date;
	private  String shift;
	private  String emp_id;
	private  int cash;
	private  int coupon;
	private  int discount;
	private  int coins;
	private  int deal_sum;
	private  int deal_cost;
	private  int deal_profit;
	private  int deal_num;
	private  int shift_sum;
	private  int real_cash;
	private  int real_coupon;
	private  String remark;
	
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getReal_cash() {
		return real_cash;
	}
	public void setReal_cash(int real_cash) {
		this.real_cash = real_cash;
	}
	public int getReal_coupon() {
		return real_coupon;
	}
	public void setReal_coupon(int real_coupon) {
		this.real_coupon = real_coupon;
	}
	public Date getDate() {
		return Date;
	}
	public void setDate(Date date) {
		Date = date;
	}
	public String getShift() {
		return shift;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
	public int getCoupon() {
		return coupon;
	}
	public void setCoupon(int coupon) {
		this.coupon = coupon;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getCoins() {
		return coins;
	}
	public void setCoins(int coins) {
		this.coins = coins;
	}
	public int getDeal_sum() {
		return deal_sum;
	}
	public void setDeal_sum(int deal_sum) {
		this.deal_sum = deal_sum;
	}
	public int getDeal_cost() {
		return deal_cost;
	}
	public void setDeal_cost(int deal_cost) {
		this.deal_cost = deal_cost;
	}
	public int getDeal_profit() {
		return deal_profit;
	}
	public void setDeal_profit(int deal_profit) {
		this.deal_profit = deal_profit;
	}
	public int getDeal_num() {
		return deal_num;
	}
	public void setDeal_num(int deal_num) {
		this.deal_num = deal_num;
	}
	public int getShift_sum() {
		return shift_sum;
	}
	public void setShift_sum(int shift_sum) {
		this.shift_sum = shift_sum;
	}
	
}
