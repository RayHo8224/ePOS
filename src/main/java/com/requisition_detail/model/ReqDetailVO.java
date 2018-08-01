package com.requisition_detail.model;

import com.requisition.model.ReqVO;

public class ReqDetailVO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private ReqVO reqVO;
	
	private String prod_name;
	
	private Integer prod_quantity;

	
	public ReqVO getReqVO() {
		return reqVO;
	}

	public void setReqVO(ReqVO reqVO) {
		this.reqVO = reqVO;
	}

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
}
