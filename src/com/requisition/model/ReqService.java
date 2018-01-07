package com.requisition.model;

import java.sql.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.product.model.ProdVO;
import com.requisition_detail.model.ReqDetailVO;

public class ReqService {
	
	private Req_Interface dao;
	
	public ReqService(){
//		dao = new ReqDAO();	
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
		dao =(Req_Interface) context.getBean("ReqDAO");
	}
	
	public ReqVO addReq(ReqVO reqVO,List<ReqDetailVO> list){
		dao.insert(reqVO,list);
		return reqVO;
	}
	
	public void updateReq(ReqVO reqVO){
		
		dao.update(reqVO);

	}
	
	public void delete(String req_id){
		dao.delete(req_id);
	}
	
	public ReqVO getByReqId(String req_id){
		return dao.findByPrimaryKey(req_id);
	}
	
	public List<ReqVO> getAll(){
		return dao.getAll();
	}
	
//	public Set<ReqDetailVO> getDetls(String req_id){
//		return dao.getDetails(req_id);
//	}
	public void setStatus(String status, String req_id) throws Exception {

		dao.setStatus(status, req_id);
	}
	
	public List<ReqVO> getByDate(Date beginDate, Date endDate){
		return dao.findByDate(beginDate, endDate);
	}
	
	public List<ReqVO> selectOfN() {
		return dao.selectOfN();
	}
	
	public List<ProdVO> getAllProd(){
		return dao.getAllProd();
	}

}
