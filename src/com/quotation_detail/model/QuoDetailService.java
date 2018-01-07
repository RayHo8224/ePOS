package com.quotation_detail.model;


import java.util.LinkedList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class QuoDetailService {
	
	private QuoDetail_Interface dao;
	
	public QuoDetailService(){
//		dao = new QuoDetailDAO();
//		dao = new QuoDetailJNDI();
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
		dao =(QuoDetail_Interface) context.getBean("QuoDetailDAO");
	}
	//預留給 Struts 2 或 Spring MVC 用
	public void addQuoDetail(List<QuoDetailVO> list) {
		dao.insert(list);
	}

	public void delete(String quo_id,String prod_name){
		dao.delete(quo_id,prod_name);
	}
	
	public List<QuoDetailVO> getByQuoId(String quo_id){
		return dao.findByQuoId(quo_id);
	}
	public List<QuoDetailVO> getAll(){
		return dao.getAll();
	}
}
