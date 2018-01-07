package com.coupon.model;

import java.sql.Date;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.discount.model.DiscountDAO_interface;

public class CouponService {

	private CouponDAO_interface dao;
	
	public CouponService(){
//		dao = new CouponHIBERNATEDAO();
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
		dao =(CouponDAO_interface) context.getBean("CouponDAO");
		
	}
	public CouponVO addCpon(String cpon_name,Date release_date,
			Date cpon_period,Integer cpon_dollar,String status){
		CouponVO cou = new CouponVO();
//		cou.setCpon_id(cpon_id);
		cou.setCpon_name(cpon_name);
		cou.setRelease_date(release_date);
		cou.setCpon_period(cpon_period);
		cou.setCpon_dollar(cpon_dollar);
		cou.setStatus(status);
		dao.insert(cou);
		return cou;
	}

	public CouponVO updCpon(String cpon_id,String cpon_name,Date release_date,
			Date cpon_period,Integer cpon_dollar,String status){
		CouponVO cou = new CouponVO();
		cou.setCpon_id(cpon_id);
		cou.setCpon_name(cpon_name);
		cou.setRelease_date(release_date);
		cou.setCpon_period(cpon_period);
		cou.setCpon_dollar(cpon_dollar);
		cou.setStatus(status);
		dao.update(cou);
		return dao.findByPrimaryKey(cpon_id);
	}

	public void delCpon(String cpon_id){
		dao.delete(cpon_id);
	}

	public CouponVO getOneCpon(String cpon_id){
		return dao.findByPrimaryKey(cpon_id);
	}
	
	public List<CouponVO> getAll() {
		return dao.getAll();
	}

	public List<CouponVO> getDollars(int cpon_dollar) {		
		return dao.findByDollar(cpon_dollar);
	}
	
	public List<CouponVO> getGroupDol() {
		return dao.GroupByDollar();
	}
	
	public List<CouponVO> getNames(String cpon_name) {		
		return dao.findByNames(cpon_name);
	}	
	
	public List<CouponVO> getGroupNam() {
		return dao.GroupByNames();
	}

	public List<CouponVO> getDates(Date release_date,Date cpon_period){
		return dao.findByDateRange(release_date, cpon_period);
	};
}
