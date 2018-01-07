package com.shipments_detail.model;

import java.io.Serializable;

import com.product.model.ProdVO;
import com.shipments.model.ShipVO;

public class ShipdetailVO implements Serializable  {

//	private String ship_id;
//	private String prod_id;
	private String prod_name;
	private int prod_quantity;
	

	//hibernate
	private ShipVO shipVO;
	private ProdVO prodVO;
	
	public ShipVO getShipVO() {
		return shipVO;
	}
	public void setShipVO(ShipVO shipVO) {
		this.shipVO = shipVO;
	}
	//	public String getShip_id() {
//		return ship_id;
//	}
//	public void setShip_id(String ship_id) {
//		this.ship_id = ship_id;
//	}
//	public String getProd_id() {
//		return prod_id;
//	}
//	public void setProd_id(String prod_id) {
//		this.prod_id = prod_id;
//	}
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
}
