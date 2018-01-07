package com.shipments.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.shipments_detail.model.ShipdetailVO;

public class ShipVO implements Serializable {

	private String ship_id;
	private String ord_id;
	private Date   ship_date; 
	private String rec_addr;
	private String rec_name; 
	private String key_id;
	private Date   key_date;
	private String remark;
	
	//hibernate
	private Set<ShipdetailVO> shipdetails=new HashSet<ShipdetailVO>();
	
	public Set<ShipdetailVO> getShipdetails() {
		return shipdetails;
	}
	public void setShipdetails(Set<ShipdetailVO> shipdetails) {
		this.shipdetails = shipdetails;
	}
	public String getShip_id() {
		return ship_id;
	}
	public void setShip_id(String ship_id) {
		this.ship_id = ship_id;
	}
	public String getOrd_id() {
		return ord_id;
	}
	public void setOrd_id(String ord_id) {
		this.ord_id = ord_id;
	}
	public Date getShip_date() {
		return ship_date;
	}
	public void setShip_date(Date ship_date) {
		this.ship_date = ship_date;
	}
	public String getRec_addr() {
		return rec_addr;
	}
	public void setRec_addr(String rec_addr) {
		this.rec_addr = rec_addr;
	}
	public String getRec_name() {
		return rec_name;
	}
	public void setRec_name(String rec_name) {
		this.rec_name = rec_name;
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
}
