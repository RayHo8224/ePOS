package com.discount.model;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.coupon.model.CouponVO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DiscountDAO implements DiscountDAO_interface{

	private static final String GET_ALL_STMT = "FROM DiscountVO order by dis_price";
	private static final String GROUP_PRICE_STMT = "SELECT dis_price FROM DiscountVO group by dis_price";
	private static final String GET_BYPRICE_STMT = "FROM DiscountVO  where dis_price = ?";
	
	private HibernateTemplate hibernateTemplate;    
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) { 
        this.hibernateTemplate = hibernateTemplate;
    }
	
	@Override
	public void insert(DiscountVO discountVO) {
		hibernateTemplate.saveOrUpdate(discountVO);		
	}

	@Override
	public void update(DiscountVO discountVO) {
		hibernateTemplate.update(discountVO);		
	}

	@Override
	public void delete(String dis_id) {
		DiscountVO discountVO = (DiscountVO) hibernateTemplate.get(DiscountVO.class, dis_id);
		hibernateTemplate.delete(discountVO);		
	}

	@Override
	public DiscountVO findByPrimaryKey(String dis_id) {
		DiscountVO discountVO = (DiscountVO) hibernateTemplate.get(DiscountVO.class, dis_id);
		return discountVO;
	}

	@Override
	public List<DiscountVO> getAll() {
		List<DiscountVO> list = null;
		list = (List<DiscountVO>) hibernateTemplate.find(GET_ALL_STMT);
		return list;
	}

	@Override
	public List<DiscountVO> GroupByPrice() {
		List<DiscountVO> list = new ArrayList<DiscountVO>();
		DiscountVO discountVO = null;
		List<Float> temp = null;
		temp = (List<Float>) hibernateTemplate.find(GROUP_PRICE_STMT);		
		Iterator<Float> iterator =	temp.iterator();	
		while(iterator.hasNext()) {
			discountVO = new DiscountVO();
			discountVO.setDis_price(iterator.next());			
			list.add(discountVO);
		}
		return list;
	}


	@Override
	public List<DiscountVO> findByPrice(float dis_price) {
		List<DiscountVO> list = null;
		list = (List<DiscountVO>) hibernateTemplate.find(GET_BYPRICE_STMT,dis_price);
		return list;
	}

}
