package com.quotation.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.quotation_detail.model.QuoDetailVO;

public class QuoVO implements java.io.Serializable{
	private String quo_id;
	private String req_id;
	private String key_id;
	private Date key_date;
	private String remark;
	private String status;
	private Set<QuoDetailVO> quoDetails =new HashSet<QuoDetailVO>();
	
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

	//hibernate

	public String getQuo_id() {
		return quo_id;
	}
	
	public void setQuo_id(String quo_id) {
		this.quo_id = quo_id;
	}
	
	public String getReq_id() {
		return req_id;
	}
	
	public void setReq_id(String req_id) {
		this.req_id = req_id;
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
	
	public Set<QuoDetailVO> getQuoDetails() {
		return quoDetails;
	}
	
	public void setQuoDetails(Set<QuoDetailVO> quoDetails) {
		this.quoDetails = quoDetails;
	}
}
