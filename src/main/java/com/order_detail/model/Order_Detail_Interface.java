package com.order_detail.model;

import java.util.LinkedList;
import java.util.List;

import com.order.model.OrderVO;

public interface Order_Detail_Interface {

	public List Select_order_detailALL(String ord_id) throws Exception;//select該筆訂單全部商品
	//public LinkedList Select_order_detail(String ord_id,String prod_id) throws Exception;//select該筆訂單中的其中一筆商品
	public Order_DetailVO Select_order_detail(String ord_id,String prod_id) throws Exception;//select該筆訂單中的其中一筆商品
	public void insert(Order_DetailVO order_detailVO,OrderVO orderVO) throws Exception;//insert該筆訂單的商品
	public void update_Detail(Order_DetailVO order_detailVO ,OrderVO orderVO) throws Exception;//update該筆訂單的商品
	public void delete(String order_id,String prod_id) throws Exception;//delete該筆訂單的某筆商品
	public List<Order_DetailVO> getAll();
	
	
}
