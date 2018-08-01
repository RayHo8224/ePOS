package com.valuation_detail.model;

import java.io.Serializable;

import com.order.model.OrderVO;
import com.product.model.ProdVO;
import com.valuation.model.ValuationVO;

public class Valuation_DetailVO implements Serializable{

	// private String vlt_id;
	// private String prod_id;
	private String prod_name;
	private int prod_quantity;
	private double prod_price;

	// hibernate
	private ValuationVO valuationVO;
	private ProdVO prodVO;

	public ValuationVO getValuationVO() {
		return valuationVO;
	}

	public void setValuationVO(ValuationVO valuationVO) {
		this.valuationVO = valuationVO;
	}

	// public String getVlt_id() {
	// return vlt_id;
	// }
	// public void setVlt_id(String vlt_id) {
	// this.vlt_id = vlt_id;
	// }
	// public String getProd_id() {
	// return prod_id;
	// }
	// public void setProd_id(String prod_id) {
	// this.prod_id = prod_id;
	// }
	
	public ProdVO getProdVO() {
		return prodVO;
	}

	public void setProdVO(ProdVO prodVO) {
		this.prodVO = prodVO;
	}
	
	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public int getProd_quantity() {
		return prod_quantity;
	}

	public void setProd_quantity(int prod_quantity) {
		this.prod_quantity = prod_quantity;
	}

	public double getProd_price() {
		return prod_price;
	}

	public void setProd_price(double prod_price) {
		this.prod_price = prod_price;
	}

}
