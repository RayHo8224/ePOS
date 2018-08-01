package com.order_detail.model;

import java.io.Serializable;

import com.order.model.OrderVO;
import com.product.model.ProdVO;

public class Order_DetailVO implements Serializable {

	// private String ord_id;
	// private String prod_id;
	private String prod_name;
	private int prod_quantity;
	private double prod_price;

	// hibernate
	private OrderVO orderVO;
	private ProdVO prodVO;

	public OrderVO getOrderVO() {
		return orderVO;
	}

	public void setOrderVO(OrderVO orderVO) {
		this.orderVO = orderVO;
	}

	// public String getOrd_id() {
	// return ord_id;
	// }
	// public void setOrd_id(String ord_id) {
	// this.ord_id = ord_id;
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
