package com.promoting.model;

import java.sql.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.coupon.model.CouponDAO_interface;

public class PromotingService {

	private PromotingDAO_interface dao;
	
	public PromotingService(){
//		dao = new PromotingHIBERNATEDAO();
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
		dao =(PromotingDAO_interface) context.getBean("PromotingDAO");
	}
	public PromotingVO addPro(String pro_prod_id,String pro_prod_name,Date pro_begin,
			Date pro_end,String pro_neirong){
		PromotingVO pro = new PromotingVO();
		pro.setPro_prod_id(pro_prod_id);
		pro.setPro_prod_name(pro_prod_name);
		pro.setPro_begin(pro_begin);
		pro.setPro_end(pro_end);
		pro.setPro_neirong(pro_neirong);
		dao.insert(pro);
		return pro;
	}

	public PromotingVO updPro(String pro_prod_id,String pro_prod_name,Date pro_begin,
			Date pro_end,String pro_neirong){
		PromotingVO pro = new PromotingVO();
		pro.setPro_prod_id(pro_prod_id);
		pro.setPro_prod_name(pro_prod_name);
		pro.setPro_begin(pro_begin);
		pro.setPro_end(pro_end);
		pro.setPro_neirong(pro_neirong);
		dao.update(pro);
		return dao.findByPrimaryKey(pro_prod_id,pro_begin);
	}

	public void delPro(String pro_prod_id,Date pro_begin){
		dao.delete(pro_prod_id,pro_begin);
	}

	public PromotingVO getOnePro(String pro_prod_id,Date pro_begin){
		return dao.findByPrimaryKey(pro_prod_id,pro_begin);
	}
	
	public List<PromotingVO> getAll() {
		return dao.getAll();
	}
	
	public List<PromotingVO> getDates(Date pro_begin, Date pro_end) {
		return dao.getDates(pro_begin,pro_end);
	}
	
	public List<PromotingVO> getNames(String pro_prod_name) {
		return dao.getNames(pro_prod_name);
	}
	
	public List<PromotingVO> getIds(String pro_prod_id1, String pro_prod_id2) {
		return dao.getIds(pro_prod_id1,pro_prod_id2);
	}
	
	public List<PromotingVO> GroupByIDs() {
		return dao.GroupByIDs();
	}
	
	public List<PromotingVO> getGroupIds(String pro_prod_id) {
		return dao.findByIDs(pro_prod_id);
	}
	
}
