package com.quotation.model;

import java.sql.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.model.ComVO;
import com.product.model.ProdVO;
import com.quotation_detail.model.QuoDetailVO;
import com.requisition.model.ReqVO;

public class QuoService {
	
	private Quo_Interface dao;
	
	public QuoService(){
//		dao = new QuoDAO();
//		dao = new QuoJNDI();
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
		dao =(Quo_Interface) context.getBean("QuoDAO");
	}
	
	public List<QuoVO> selectOfN(){
		return dao.selectOfN();
	}
	
	public List<QuoVO> selectOfY(){
		return dao.selectOfY();
	}
	
	public String getComId(String com_name){
		return dao.getComId(com_name);
	}
	
	public void addQuo(QuoVO quoVO,List<QuoDetailVO> list){
		dao.insert(quoVO,list);
	}
	
	public void addProd(ProdVO prodVO){
		dao.insertProduct(prodVO);
	}
	
	public void updateQuo(QuoVO quoVO){
		
		dao.update(quoVO);
	}
	
	public void delete(String quo_id){
		dao.delete(quo_id);
	}
	
	public QuoVO getByQuoId(String quo_id){
		return dao.findByPrimaryKey(quo_id);
	}
	
//	public Set<QuoDetailVO> getQuoDetail(String quo_id) {
//		Set<QuoDetailVO> set = dao.findDetailByPrimaryKey(quo_id);
//		return set;
//	}
	
	public void setStatus(String status, String quo_id) throws Exception {

		dao.setStatus(status, quo_id);
	}
	
	public void setReqStatus(String status2, String req_id) throws Exception {

		dao.setReqStatus(status2, req_id);
	}
	
	public List<QuoVO> getByReqId(String req_id){
		return dao.findByReqid(req_id);
	}
	
	public List<QuoVO> getAll(){
		System.out.println("1");
		return dao.getAll();
	}
	
	public List<ReqVO> findYReq(){
		return dao.findYReq();
	}
	
	public ReqVO findByReqKey(String req_id){
		return dao.findByReqKey(req_id);
	}
	
	public List<ProdVO> getAllProd(){
		return dao.getAllProd();
	}
	
	public List<ComVO> getAllCom(){
		return dao.getCom();
	}
	
	public List<QuoVO> getByDate(Date begin_date, Date end_date){
		List<QuoVO> list = null;
		list = dao.findByDate(begin_date, end_date);
		return list;
	}

}
