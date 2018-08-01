package com.requisition_detail.model;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.quotation_detail.model.QuoDetail_Interface;

public class ReqDetailService {
	
	private ReqDetail_Interface dao;
	
	public ReqDetailService(){
//		dao = new ReqDetailDAO();	
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
		dao =(ReqDetail_Interface) context.getBean("ReqDetailDAO");
	}

//	public ReqDetailVO addDetail(ReqDetailVO reqDetailVO){
//		
//		dao.insert(reqDetailVO);
//		return reqDetailVO;
//	}
	
//	public List<ReqDetailVO> updateDetailVO(String req_id, String prod_name,Integer prod_quantity){
//		
//		ReqDetailVO detailVO = new ReqDetailVO();
//		
//		detailVO.setProd_quantity(prod_quantity);
//		dao.update(detailVO);
//		
//		return dao.getone(req_id,prod_name);
//	}
	
	public void delete(String req_id,String prod_name){
		dao.delete(req_id,prod_name);
	}
	
	public List<ReqDetailVO> getByReqId(String req_id){
		return dao.findByReqId(req_id);
	}
//	public List<ReqDetailVO> getoneProdnameDetail(String prod_name){
//		return dao.findByProdname(prod_name);
//	}
	
//	public List<ReqDetailVO> getAll(){
//		return dao.getAll();
//	}
	
}
