package com.pur.model;

import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import com.pur_detail.model.Pur_detailVO;

public class PurVO implements java.io.Serializable{
	private String pur_id;
//	private String quo_id;
	private Date pur_date;
	private Date delivery_date;
	private String com_id;
	private String key_id;
	private Date key_date;
	private String remark;
	private String status;
	private Set<Pur_detailVO> purs = new LinkedHashSet<Pur_detailVO>();
	
	public Set<Pur_detailVO> getPurs() {
		return purs;
	}
	public void setPurs(Set<Pur_detailVO> purs) {
		this.purs = purs;
	}
	public String getPur_id() {
		return pur_id;
	}
	public void setPur_id(String pur_id) {
		this.pur_id = pur_id;
	}
//	public String getQuo_id() {
//		return quo_id;
//	}
//	public void setQuo_id(String quo_id) {
//		this.quo_id = quo_id;
//	}
	public Date getPur_date() {
		return pur_date;
	}
	public void setPur_date(Date pur_date) {
		this.pur_date = pur_date;
	}
	public Date getDelivery_date() {
		return delivery_date;
	}
	public void setDelivery_date(Date delivery_date) {
		this.delivery_date = delivery_date;
	}
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
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
