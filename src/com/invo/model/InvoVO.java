package com.invo.model;

import java.sql.Date;

public class InvoVO implements java.io.Serializable{
	private String invoice_id;
	private String ord_id;
	private String new_invoice_number;
	private String new_ord_id;
	
	public String getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
	}
	public String getOrd_id() {
		return ord_id;
	}
	public void setOrd_id(String ord_id) {
		this.ord_id = ord_id;
	}
	public String getNew_invoice_number() {
		return new_invoice_number;
	}
	public void setNew_invoice_number(String new_invoice_number) {
		this.new_invoice_number = new_invoice_number;
	}
	public String getNew_ord_id() {
		return new_ord_id;
	}
	public void setNew_ord_id(String new_ord_id) {
		this.new_ord_id = new_ord_id;
	}
	
	
}
