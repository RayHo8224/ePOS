package com.product.model;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.employee.model.EmpDAO_interface;

public class ProdService {
	
private ProdDAO_interface dao=null;
	
	public ProdService() {
//		dao=new ProdDAO();

		ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
		dao =(ProdDAO_interface) context.getBean("ProdDAO");
	}
	
	public ProdVO getOne(String prod_id) {
		return dao.findByPrimaryKey(prod_id);
	}
	
	public  List getAll(){
		return dao.getAll();
	}
	
	public void insertOne(ProdVO prodVO){
		dao.insert(prodVO);
	}
	
	public List<ProdVO> getByName(String prod_name){
		return dao.findByName(prod_name);
	}
	
	public void update(ProdVO prodVO){
		dao.update(prodVO);
	}
	
	public void delete(String prod_id){
		dao.delete(prod_id);
		
	}
	
	public List getByGroup(String prod_group){
		return dao.findByGroup(prod_group);
	}
	
	public void update2(Integer prod_stock, String prod_id){
		dao.update2(prod_stock, prod_id);
		return;
	}
	public List selgroup(){
		return dao.selectByGroup();
	
	}
}
