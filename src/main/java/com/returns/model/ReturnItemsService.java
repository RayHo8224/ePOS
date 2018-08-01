package com.returns.model;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ReturnItemsService {
	
	private RtnItems_Interface dao=null;
	
	public ReturnItemsService(){
//		dao = new RtnItemsJNDI();
//		dao = new RtnItemsDAO_Hibernate();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
		dao =(RtnItems_Interface) context.getBean("RtnItemsDAO");
	}
	

	public void addRtnItem(RtnItemsVO rtnItemsVO){
		dao.insert(rtnItemsVO);
	}
	
	
	public RtnItemsVO updateRtnItem(String prod_name,String com_id,Integer re_quantity,String remark){

		RtnItemsVO updateRtnItems = new RtnItemsVO();
		
		updateRtnItems.setProd_name(prod_name);
		updateRtnItems.setCom_id(com_id);
		updateRtnItems.setRe_quantity(re_quantity);
		updateRtnItems.setRemark(remark);
		
		dao.update(updateRtnItems);
		
		return dao.findByPrimaryKey(prod_name);
		
	}
	
	public void delete(String prod_name){
		dao.delete(prod_name);
	}
	
	public RtnItemsVO getOneRtnItem(String prod_name){
		return dao.findByPrimaryKey(prod_name);
	}
	
	public List<RtnItemsVO> getAll(){
		return dao.getAll();
	}
	
	public List<RtnItemsVO> findByName(String prod_name){
		return dao.findByName(prod_name);
	}
    public List<RtnItemsVO> findById(String com_id){
    	return dao.findById(com_id);
    }
    
    public RtnItemsVO byName(String prod_name){
		return dao.findByPrimaryKey(prod_name);
	}
    
}


