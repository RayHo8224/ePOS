package com.discount.model;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DiscountService {

	private DiscountDAO_interface dao;
	
	public DiscountService(){
//		dao = new DiscountHIBERNATEDAO();
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
		dao =(DiscountDAO_interface) context.getBean("DiscountDAO");
	}
	
	public DiscountVO addDisc(String dis_id,Float dis_price){
		DiscountVO dis = new DiscountVO();
		dis.setDis_id(dis_id);
		dis.setDis_price(dis_price);
		dao.insert(dis);
		return dis;
	}

	public DiscountVO updDisc(String dis_id,Float dis_price){
		DiscountVO dis = new DiscountVO();
		dis.setDis_id(dis_id);
		dis.setDis_price(dis_price);
		dao.update(dis);
		return dao.findByPrimaryKey(dis_id);
	}

	public void delDisc(String dis_id){
		dao.delete(dis_id);
	}

	public DiscountVO getOneDisc(String dis_id){
		return dao.findByPrimaryKey(dis_id);
	}
	
	public List<DiscountVO> getAll() {
		return dao.getAll();
	}
	
	public List<DiscountVO> groupPrice() {
		return dao.GroupByPrice();
	}
	
	public List<DiscountVO> findByPrice(Float dis_price) {
		return dao.findByPrice(dis_price);
	}
}
