package com.returns.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class RtnListVO implements java.io.Serializable{
	private String ret_id;
	private Date ret_date;
	private String com_id;
	private String com_name;
	private String key_id;
	private Date key_date;
	private String remark;
	private String status;
	private Set<RtnDetailVO> rtnDetail = new HashSet<RtnDetailVO>();
	
	public String getRet_id() {
		return ret_id;
	}
	public void setRet_id(String ret_id) {
		this.ret_id = ret_id;
	}
	public Date getRet_date() {
		return ret_date;
	}
	public void setRet_date(Date ret_date) {
		this.ret_date = ret_date;
	}
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
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

	public Set<RtnDetailVO> getRtnDetail() {
		return rtnDetail;
	}
	public void setRtnDetail(Set<RtnDetailVO> rtnDetail) {
		this.rtnDetail = rtnDetail;
	}

}
