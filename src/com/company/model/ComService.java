package com.company.model;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ComService {
	
	private ComDAO_interface dao=null;
	
	public ComService() {
//		dao=new ComDAO();
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
		dao =(ComDAO_interface) context.getBean("ComDAO");

	}
	
	public ComVO getOne(String com_id) {
		return dao.findByPrimaryKey(com_id);
	}
	
	public  List getAll(){
		return dao.getAll();
	}
	
	public void insertOne(ComVO comVO){

		dao.insert(comVO);
	}
	
	public List getByName(String com_name){
		return dao.findByName(com_name);
	}
	
	public void update(ComVO comVO){
		dao.update(comVO);
	}
	
	public void delete(String com_id){
		dao.delete(com_id);
		
	}
	
}
