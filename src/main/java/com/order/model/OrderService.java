package com.order.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.employee.model.EmpVO;
import com.order_detail.model.Order_DetailVO;
import com.order_detail.model.Order_Detail_Interface;
import com.product.model.ProdVO;

public class OrderService {

	private Order_Interface ord_dao;
	private Order_Detail_Interface ordDtail_dao;

	public OrderService() {
		// ord_dao=new OrderHibernateDAO();
		// ordDtail_dao= new Order_detailHibernateDAO();

		ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
		ord_dao = (Order_Interface) context.getBean("OrderSpringDAO");
		ordDtail_dao = (Order_Detail_Interface) context.getBean("Order_detailSpringDAO");
	}

	public OrderVO getOneOrder(String ord_id) throws Exception {

		return ord_dao.Select_order_id(ord_id);

	}

	public List getOneOrderDate(Date s_ord_date, Date e_ord_date) throws Exception {

		return ord_dao.Select_ord_date(s_ord_date, e_ord_date);

	}

	// public OrderVO insert(OrderVO orderVO,Order_DetailVO order_detailVO)
	// throws Exception{//新增一筆訂單&一筆商品
	//
	// return ord_dao.insert(orderVO, order_detailVO);
	// }

	public void update(OrderVO orderVO, Order_DetailVO order_detailVO) throws Exception {
		ord_dao.update(orderVO, order_detailVO);
	}

	public void delete(String ord_id) throws Exception {
		ord_dao.delete(ord_id);
	}

	public void update_Detail(Order_DetailVO order_detailVO, OrderVO orderVO) throws Exception {
		ordDtail_dao.update_Detail(order_detailVO, orderVO);
	}

	public List getOrderDetailALL(String ord_id) throws Exception {

		return ordDtail_dao.Select_order_detailALL(ord_id);

	}

	public void insertDetail(Order_DetailVO order_detailVO, OrderVO orderVO) throws Exception {

		ordDtail_dao.insert(order_detailVO, orderVO);
	}

	public Order_DetailVO getOrderDetail(String ord_id, String prod_id) throws Exception {

		return ordDtail_dao.Select_order_detail(ord_id, prod_id);

	}

	public void deleteDetail(String ord_id, String prod_id) throws Exception {
		ordDtail_dao.delete(ord_id, prod_id);
	}

	public OrderVO addOrder(OrderVO orderVO, List<Order_DetailVO> order_detailVO_list) throws Exception {
		// Set<Order_DetailVO> x = orderVO.getOrderdetails();
		// for(Order_DetailVO order_DetailVO :x ){
		// System.out.println(order_DetailVO.getProd_name()+"========================");
		// }
		return ord_dao.addOrder(orderVO, order_detailVO_list);
	}

	public List<OrderVO> getAll() throws Exception {

		return ord_dao.getAll();
	}
	public List<OrderVO> getAllWeather(String weather) throws Exception {
		
		return ord_dao.getAllWeather(weather);
	}

	public List Select_order_detailALL(String ord_id) throws Exception {

		return ordDtail_dao.Select_order_detailALL(ord_id);
	}

	public OrderVO Select_order_id(String ord_id) throws Exception {

		return ord_dao.Select_order_id(ord_id);
	}

	public List GetProdNameCount(String prod_id) throws Exception {

		return ord_dao.GetProdNameCount(prod_id);
	}

	public void setStatus(String status, String ord_id) throws Exception {

		ord_dao.setStatus(status, ord_id);
	}

	public ProdVO getOneProdid(String prod_id) throws Exception {

		return ord_dao.getOneProdid(prod_id);
	}

	public EmpVO getOneMemid(String mem_id) throws Exception {
		
		return ord_dao.getOneMemid(mem_id);
	}
	
	public double GetDayTotalPrice() throws Exception {
		return ord_dao.GetDayTotalPrice();
	}
	
	public long GetDayTotalPeople() throws Exception {
		return ord_dao.GetDayTotalPeople();
	}
	
	public int getMaxInvoiceId() throws Exception{

		return ord_dao.getMaxInvoiceId();
	}
	
	public OrderVO getOneTopOrdId() throws Exception{
		
		return ord_dao.getOneTopOrdId();				
	}
	public List<OrderVO> getDateAndShift(Date s_ord_date,String shift) throws Exception{
		
		return ord_dao.Select_ord_date_shift(s_ord_date, shift);
	}
	
	public List<OrderVO> getOrdPrice(){
		return ord_dao.getOrdPrice();
	}
	
	public List<Order_DetailVO> getAllOrderDetail(){
		return ordDtail_dao.getAll();
	}
	
}
