package com.shipments_detail.model;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.employee.model.EmpDAO_interface;
import com.shipments_detail.model.ShipdetailVO;

public class ShipdetailService {

	private ShipdetailDAO_interface dao=null;
	
	public ShipdetailService(){
//		dao=new ShipdetailDAO();

		ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
		dao =(ShipdetailDAO_interface) context.getBean("ShipdetailDAO");
	}
	
	public List<ShipdetailVO> getByShipId(String ship_id){
		return dao.findByShipId(ship_id);
	}
	
	public void delete (String ship_id,String prod_id){

		dao.delete(ship_id, prod_id);
	}
}
