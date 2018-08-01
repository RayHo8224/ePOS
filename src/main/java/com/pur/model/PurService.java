package com.pur.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.model.ComVO;
import com.product.model.ProdVO;
import com.pur_detail.model.Pur_detailVO;

public class PurService {
	
	private Pur_Interface dao;
	
	public PurService() {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
		dao =(Pur_Interface) context.getBean("purDAO");
	}
	
	public List<PurVO> selectOfN(){
		List<PurVO> list = dao.selectOfN();
		return list;
	}
	
	public List<PurVO> findByDate(Date begin_date, Date end_date){
		List<PurVO> list = dao.findByDate(begin_date, end_date);
		return list;
	}
	
	public PurVO insert(PurVO purVO) {
		dao.insert(purVO);
		return purVO;
	}
	
	public PurVO update(PurVO purVO) {
		dao.update(purVO);
		return purVO;
	}
	
	public void deletePur(String pur_id) {
		dao.delete(pur_id);
	}
	
	public void deleteDetail(String pur_id,String prod_id) {
		dao.deleteDetail(pur_id,prod_id);
	}	
	
	public PurVO getOnePur(String pur_id) {
		PurVO purVO = dao.findByPrimaryKey(pur_id);
		return purVO;
	}
	
	public void setStatus(String status, String pur_id){

		dao.setStatus(status, pur_id);
	}
	
	public List<Pur_detailVO> getPurDetail(String pur_id) {
		List<Pur_detailVO> set = dao.findDetailByPrimaryKey(pur_id);
		return set;
	}
	
	public List<PurVO> getAll() {
		List<PurVO> list = dao.getAll();
		return list;
	}
	
	public List<ComVO> getCom(){
		List<ComVO> list = dao.getCom();
		return list;
	}
	
	public List<ProdVO> getProd(String com_id){
		List<ProdVO> list = dao.getProd(com_id);
		return list;
	}
	
	public List<ComVO> getOneCom(String com_name){
		List<ComVO> list = dao.getOneCom(com_name);
		return list;
	}
	
	public ProdVO getOneProd(String prod_name){
		List<ProdVO> list = dao.getProdById(prod_name);
		ProdVO prodVO = list.get(0);
		return prodVO;
	}

}








