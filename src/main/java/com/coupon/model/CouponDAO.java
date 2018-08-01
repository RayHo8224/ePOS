package com.coupon.model;

import org.springframework.orm.hibernate5.HibernateTemplate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class CouponDAO implements CouponDAO_interface{
	
	private static final String GET_ALL_STMT = "FROM CouponVO order by cpon_id";
	private static final String GET_NameGroup_STMT = "SELECT cpon_name FROM CouponVO group by cpon_name";
	private static final String GET_Names_STMT = "FROM CouponVO where cpon_name = ?";
	private static final String GET_DollarGroup_STMT = "SELECT cpon_dollar FROM CouponVO group by cpon_dollar";
	private static final String GET_Dollar_STMT = "FROM CouponVO where cpon_dollar = ?";
	private static final String GET_Dates_STMT = "FROM CouponVO where release_date >= ? and cpon_period <= ? order by release_date";

	private HibernateTemplate hibernateTemplate;    
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) { 
        this.hibernateTemplate = hibernateTemplate;
    }
	
	@Override
	public void insert(CouponVO couponVO) {
		hibernateTemplate.saveOrUpdate(couponVO);		
	}

	@Override
	public void update(CouponVO couponVO) {
		hibernateTemplate.update(couponVO);
		
	}

	@Override
	public void delete(String cpon_id) {
		CouponVO couponVO = (CouponVO) hibernateTemplate.get(CouponVO.class, cpon_id);
		hibernateTemplate.delete(couponVO);
		
	}

	@Override
	public CouponVO findByPrimaryKey(String cpon_id) {
		CouponVO couponVO = (CouponVO) hibernateTemplate.get(CouponVO.class, cpon_id);
		return couponVO;
	}

	@Override
	public List<CouponVO> getAll() {
		List<CouponVO> list = null;
		list = (List<CouponVO>) hibernateTemplate.find(GET_ALL_STMT);
		return list;
	}
	
	@Override
	public List<CouponVO> GroupByNames() {
		List<CouponVO> list = new ArrayList<CouponVO>();
		CouponVO couponVO = null;
		List<String> temp = null;
		temp = (List<String>) hibernateTemplate.find(GET_NameGroup_STMT);		
		Iterator<String> iterator =	temp.iterator();	
		while(iterator.hasNext()) {
			couponVO = new CouponVO();
			couponVO.setCpon_name(iterator.next());			
			list.add(couponVO);
		}
		return list;
	}

	@Override
	public List<CouponVO> findByNames(String cpon_name) {
		List<CouponVO> list = null;
		list = (List<CouponVO>) hibernateTemplate.find(GET_Names_STMT, cpon_name);
		return list;
	}

	@Override
	public List<CouponVO> GroupByDollar() {
		List<CouponVO> list = new ArrayList<CouponVO>();
		CouponVO couponVO = null;
		List<Integer> temp = null;
		temp = (List<Integer>) hibernateTemplate.find(GET_DollarGroup_STMT);
		Iterator<Integer> iterator =	temp.iterator();	
		while(iterator.hasNext()) {
			couponVO = new CouponVO();
			couponVO.setCpon_dollar(iterator.next());			
			list.add(couponVO);
		}
		return list;
	}

	@Override
	public List<CouponVO> findByDollar(int cpon_dollar) {
		List<CouponVO> list = null;
		list = (List<CouponVO>) hibernateTemplate.find(GET_Dollar_STMT, cpon_dollar);		
		return list;		
	}

	@Override
	public List<CouponVO> findByDateRange(Date release_date, Date cpon_period) {
		List<CouponVO> list = null;
		list = (List<CouponVO>) hibernateTemplate.find(GET_Dates_STMT, new Date[]{release_date, cpon_period});		
		return list;	
	}

}
