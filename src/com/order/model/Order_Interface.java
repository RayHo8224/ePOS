package com.order.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.employee.model.EmpVO;
import com.order_detail.model.Order_DetailVO;
import com.product.model.ProdVO;

public interface Order_Interface {

	//public LinkedList Select_order_id(String ord_id) throws Exception;
	public OrderVO Select_order_id(String ord_id) throws Exception;
	public List Select_ord_date(Date s_ord_date,Date e_ord_date) throws Exception;
	//public OrderVO insert(OrderVO orderVO,Order_DetailVO order_detailVO) throws Exception;//新增一筆訂單&一筆商品
	public OrderVO addOrder(OrderVO orderVO, List<Order_DetailVO> order_detailVO_list) throws Exception;//新增一筆訂單&多筆商品
	public void update(OrderVO orderVO,Order_DetailVO order_detailVO) throws Exception;
	public void delete(String order_id) throws Exception;
	public List<OrderVO> getAll() throws Exception;
	public List GetProdNameCount(String prod_id) throws Exception; //商品名稱總數
	public void setStatus(String status,String ord_id) throws Exception;
	
	public ProdVO getOneProdid(String prod_id)throws Exception;
	public EmpVO getOneMemid(String mem_id)throws Exception;
	public double GetDayTotalPrice() throws Exception;
	public long GetDayTotalPeople() throws Exception;
	
	public int getMaxInvoiceId() throws Exception;
	
	public OrderVO getOneTopOrdId() throws Exception;
	public List<OrderVO> Select_ord_date_shift(Date s_ord_date, String shift) throws Exception;
	
	public List<OrderVO> getOrdPrice();
	List<OrderVO> getAllWeather() throws Exception;
	public List<OrderVO> getAllWeather(String weather) throws Exception;
	
}
