package com.coupon.model;

import java.sql.Date;

public class CouponVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String cpon_id;
	private String cpon_name;
	private Date release_date;
	private Date cpon_period;
	private int cpon_dollar;
	private String status;

	public String getCpon_id() {
		return cpon_id;
	}

	public void setCpon_id(String cpon_id) {
		this.cpon_id = cpon_id;
	}

	public String getCpon_name() {
		return cpon_name;
	}

	public void setCpon_name(String cpon_name) {
		this.cpon_name = cpon_name;
	}

	public Date getRelease_date() {
		return release_date;
	}

	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}

	public Date getCpon_period() {
		return cpon_period;
	}

	public void setCpon_period(Date cpon_period) {
		this.cpon_period = cpon_period;
	}

	public Integer getCpon_dollar() {
		return cpon_dollar;
	}

	public void setCpon_dollar(Integer cpon_dollar) {
		this.cpon_dollar = cpon_dollar;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
