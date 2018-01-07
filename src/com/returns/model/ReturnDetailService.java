package com.returns.model;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ReturnDetailService {
	
	private RtnDetail_Interface dao;
	
	public ReturnDetailService(){
//		dao = new RtnDetailJNDI();
//		dao = new RtnDetailDAO();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
		dao =(RtnDetail_Interface) context.getBean("RtnDetailDAO");
		
	}
	
	public RtnDetailVO addDetail(String ret_id,String prod_name,String ret_reason,Integer prod_quantity){
		
		RtnDetailVO rtnDetailVO = new RtnDetailVO();
		RtnListVO rtnListVO = new RtnListVO();
		RtnItemsVO rtnItemsVO =new RtnItemsVO();

		rtnDetailVO.setRtnListVO(rtnListVO);
		rtnDetailVO.setRtnItemsVO(rtnItemsVO);
		
		rtnListVO.setRet_id(ret_id);
		rtnItemsVO.setProd_name(prod_name);
		rtnDetailVO.setRet_reason(ret_reason);
		rtnDetailVO.setProd_quantity(prod_quantity);
		
//		
		dao.insert(rtnDetailVO);
		return rtnDetailVO;

	}
	
	public RtnDetailVO updateDetailVO(String ret_id,String prod_name,String ret_reason,Integer prod_quantity){
		
		RtnDetailVO rtnDetailVO = new RtnDetailVO();
		RtnListVO rtnListVO = new RtnListVO();
		RtnItemsVO rtnItemsVO =new RtnItemsVO();

		rtnDetailVO.setRtnListVO(rtnListVO);
		rtnDetailVO.setRtnItemsVO(rtnItemsVO);
		
		rtnListVO.setRet_id(ret_id);
		rtnItemsVO.setProd_name(prod_name);
		rtnDetailVO.setRet_reason(ret_reason);
		rtnDetailVO.setProd_quantity(prod_quantity);
		dao.update(rtnDetailVO);
		
		return dao.findByPrimaryKey(ret_id,prod_name);
	}
	
	public void delete(String ret_id,String prod_name){
		dao.delete(ret_id,prod_name);
	}
	
	public RtnDetailVO getOneRtnDetail(String ret_id,String prod_name){
		System.out.println(ret_id);
		return dao.findByPrimaryKey(ret_id,prod_name);
	}
	
	public List<RtnDetailVO> getAll(){
		return dao.getAll();
	}
	
	public List<RtnDetailVO> getFindById(String ret_id){
		return dao.findById(ret_id);
	}
    public List<RtnDetailVO> getFindByName(String prod_name){
    	return dao.findByName(prod_name);
    }
	

}
