package com.discount.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import com.coupon.model.CouponVO;

import hibernate.util.HibernateUtil;

public class DiscountHIBERNATEDAO implements DiscountDAO_interface{
	private static final String GET_ALL_STMT = "FROM DiscountVO order by dis_price";
	private static final String GROUP_PRICE_STMT = "SELECT dis_price FROM DiscountVO group by dis_price";
	private static final String GET_BYPRICE_STMT = "FROM DiscountVO  where dis_price = ?";
	@Override
	public void insert(DiscountVO discountVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.save(discountVO);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}			
	}

	@Override
	public void update(DiscountVO discountVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.saveOrUpdate(discountVO);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}		
	}

	@Override
	public void delete(String dis_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			DiscountVO discVO = (DiscountVO)session.get(DiscountVO.class, dis_id);
			session.delete(discVO);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}		
	}

	@Override
	public DiscountVO findByPrimaryKey(String dis_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		DiscountVO discVO = null;
		try{
			session.beginTransaction();
			discVO = (DiscountVO)session.get(DiscountVO.class, dis_id);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return discVO;
	}

	@Override
	public List<DiscountVO> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<DiscountVO> list = null;
		try{
			session.beginTransaction();
			Query query = session.createQuery(GET_ALL_STMT);
			list = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}

	@Override
	public List<DiscountVO> GroupByPrice() {
		List<DiscountVO> list = new ArrayList<DiscountVO>();
		DiscountVO discVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GROUP_PRICE_STMT);			
			Iterator<Float> iterator =  query.iterate();
			while(iterator.hasNext()) {
				discVO = new DiscountVO();
				discVO.setDis_price(iterator.next());			
				list.add(discVO);
			}
			session.getTransaction().commit();			
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}

	@Override
	public List<DiscountVO> findByPrice(float dis_price) {
		List<DiscountVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_BYPRICE_STMT);
			query.setParameter(0, dis_price);
			list = query.list();
			session.getTransaction().commit();			
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}

}
