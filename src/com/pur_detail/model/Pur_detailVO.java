package com.pur_detail.model;

import com.product.model.ProdVO;
import com.pur.model.PurVO;

public class Pur_detailVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
//	private String pur_id;
//	private String prod_id;
	private String prod_name;
	private Integer prod_quantity;
	private Integer prod_price;
	private PurVO purVO;
	private ProdVO prodVO;
	private Integer prod_lsum;
	
	public Integer getProd_lsum() {
		return prod_lsum;
	}
	public void setProd_lsum(Integer prod_lsum) {
		this.prod_lsum = prod_lsum;
	}
	public ProdVO getProdVO() {
		return prodVO;
	}
	public void setProdVO(ProdVO prodVO) {
		this.prodVO = prodVO;
	}
//	public String getPur_id() {
//		return pur_id;
//	}
//	public void setPur_id(String pur_id) {
//		this.pur_id = pur_id;
//	}
//	public String getProd_id() {
//		return prod_id;
//	}
//	public void setProd_id(String prod_id) {
//		this.prod_id = prod_id;
//	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public Integer getProd_quantity() {
		return prod_quantity;
	}
	public void setProd_quantity(Integer prod_quantity) {
		this.prod_quantity = prod_quantity;
	}
	public Integer getProd_price() {
		return prod_price;
	}
	public void setProd_price(Integer prod_price) {
		this.prod_price = prod_price;
	}
	public PurVO getPurVO() {
		return purVO;
	}
	public void setPurVO(PurVO purVO) {
		this.purVO = purVO;
	}
}
