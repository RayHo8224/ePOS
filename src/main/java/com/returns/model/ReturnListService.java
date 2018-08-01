package com.returns.model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ReturnListService {
	
	private RtnList_Interface dao;
	
	public ReturnListService(){
//		dao = new RtnListJNDI();
//		dao = new RtnListDAO_Hibernate();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
		dao =(RtnList_Interface) context.getBean("RtnListDAO");
		
	}
	
	public void addRtnList(RtnListVO rtnListVO,List<RtnDetailVO> list){
		
		dao.insert(rtnListVO,list);
		//return rtnListVO;
	}
	
	public RtnListVO updateRtnList(String ret_id,java.sql.Date ret_date,String com_id,String com_name,String key_id,
			java.sql.Date key_date,String remark,String status){
		
		RtnListVO  updateRtnListVO= new RtnListVO();
		
		updateRtnListVO.setRet_id(ret_id);
		updateRtnListVO.setRet_date(ret_date);
		updateRtnListVO.setCom_id(com_id);
		updateRtnListVO.setCom_name(com_name);
		updateRtnListVO.setKey_id(key_id);
		updateRtnListVO.setKey_date(key_date);
		updateRtnListVO.setRemark(remark);
		updateRtnListVO.setStatus(status);
		
		dao.update(updateRtnListVO);
		
		return dao.findByPrimaryKey(ret_id);
	}
	
	public void delete(String ret_id){
		dao.delete(ret_id);
	}
	
	public RtnListVO getOneRtnList(String ret_id){
		return dao.findByPrimaryKey(ret_id);
	}
	
	public List<RtnListVO> findByDate(Date ret_date){
		return dao.findByDate(ret_date);
	}
	
	public RtnListVO findById(String ret_id){
		return dao.findByPrimaryKey(ret_id);
	}
	
	public List<RtnListVO> findByCom(String com_name){
		return dao.findByCom(com_name);
	}
	
	public List<RtnDetailVO> findByDetail(String ret_id){
		return dao.findByDetail(ret_id);
	}
	
	public List<RtnListVO> getAll(){
		return dao.getAll();
	}
	
	public List<RtnListVO> getfindById(String ret_id){
		return dao.getfindById(ret_id);
	}


}
