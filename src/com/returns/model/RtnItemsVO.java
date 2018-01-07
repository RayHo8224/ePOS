package com.returns.model;


public class RtnItemsVO implements java.io.Serializable{
	private String prod_name;
	private String com_id;
	private Integer re_quantity;
	private String remark;

	
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
	public int getRe_quantity() {
		return re_quantity;
	}
	public void setRe_quantity(int re_quantity) {
		this.re_quantity = re_quantity;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	
}
