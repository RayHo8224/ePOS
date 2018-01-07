package com.shipments.model;

import java.sql.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shipments_detail.model.ShipdetailVO;

public class ShipService {

	private ShipDAO_interface dao=null;
	
	public ShipService(){
//		dao=new ShipDAO();

		ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
		dao =(ShipDAO_interface) context.getBean("ShipDAO");
	}
	
	public void insertOne(ShipVO shipVO,List<ShipdetailVO> list){
		dao.insert(shipVO, list);
	}
	
	public List<ShipVO> getByDate(Date dateBegin,Date dateEnd){
		return dao.findByDate(dateBegin, dateEnd);
	}
	
	public ShipVO getByShipId(String ship_id){
		return dao.findByPrimaryKey(ship_id);
	}
	
	public List<ShipVO> getAll(){
		return dao.getAll();
	}
	
	public void delete(String ship_id){
		dao.delete(ship_id);
	}
	
	public List<ShipVO> getByOrderId(String ord_id){
		return dao.findByOrderId(ord_id);
	}
}
