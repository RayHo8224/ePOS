package com.returns.model;

public class RtnDetailVO implements java.io.Serializable{
//	private String ret_id;
//	private String prod_name;
	private String ret_reason;
	private Integer prod_quantity;
	
	private RtnItemsVO rtnItemsVO;
	private RtnListVO rtnListVO;
	
//	public String getRet_id() {
//		return ret_id;
//	}
//	public void setRet_id(String ret_id) {
//		this.ret_id = ret_id;
//	}
//	public String getProd_name() {
//		return prod_name;
//	}
//	public void setProd_name(String prod_name) {
//		this.prod_name = prod_name;
//	}
	public String getRet_reason() {
		return ret_reason;
	}
	public void setRet_reason(String ret_reason) {
		this.ret_reason = ret_reason;
	}
	public Integer getProd_quantity() {
		return prod_quantity;
	}
	public void setProd_quantity(Integer prod_quantity) {
		this.prod_quantity = prod_quantity;
	}
	
	
	public RtnItemsVO getRtnItemsVO() {
		return rtnItemsVO;
	}
	public void setRtnItemsVO(RtnItemsVO rtnItemsVO) {
		this.rtnItemsVO = rtnItemsVO;
	}
	public RtnListVO getRtnListVO() {
		return rtnListVO;
	}
	public void setRtnListVO(RtnListVO rtnListVO) {
		this.rtnListVO = rtnListVO;
	}


}
