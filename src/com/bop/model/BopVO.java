package com.bop.model;

import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import com.bop_detail.model.Bop_detailVO;

public class BopVO implements java.io.Serializable {
	private String bop_id;
	private String pur_id;
	private Date bop_date;
	private String com_id;
	private String key_id;
	private Date key_date;
	private Integer remark;
	private String status;
	private Set<Bop_detailVO> bops = new LinkedHashSet<Bop_detailVO>();
	
	public String getBop_id() {
		return bop_id;
	}
	public void setBop_id(String bop_id) {
		this.bop_id = bop_id;
	}
	public String getPur_id() {
		return pur_id;
	}
	public void setPur_id(String pur_id) {
		this.pur_id = pur_id;
	}
	public Date getBop_date() {
		return bop_date;
	}
	public void setBop_date(Date bop_date) {
		this.bop_date = bop_date;
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
	public Integer getRemark() {
		return remark;
	}
	public void setRemark(Integer remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Set<Bop_detailVO> getBops() {
		return bops;
	}
	public void setBops(Set<Bop_detailVO> bops) {
		this.bops = bops;
	}
}
