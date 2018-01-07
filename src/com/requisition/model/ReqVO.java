package com.requisition.model;

import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import com.requisition_detail.model.ReqDetailVO;

public class ReqVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String req_id;
	
	private String key_id;
	
	private Date key_date;
	
	private String status;
	
	private Set<ReqDetailVO> reqDetails = new LinkedHashSet<ReqDetailVO>();

	public String getReq_id() {
		return req_id;
	}

	public void setReq_id(String req_id) {
		this.req_id = req_id;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<ReqDetailVO> getReqDetails() {
		return reqDetails;
	}

	public void setReqDetails(Set<ReqDetailVO> req_dtls) {
		this.reqDetails = req_dtls;
	}
}
