package com.coupon.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import hibernate.util.HibernateUtil;

public class CouponHIBERNATEDAO implements CouponDAO_interface {
	private static final String GET_ALL_STMT = "FROM CouponVO order by cpon_id";
	private static final String GET_NameGroup_STMT = "SELECT cpon_name FROM CouponVO group by cpon_name";
	private static final String GET_Names_STMT = "FROM CouponVO where cpon_name = ?";
	private static final String GET_DollarGroup_STMT = "SELECT cpon_dollar FROM CouponVO group by cpon_dollar";
	private static final String GET_Dollar_STMT = "FROM CouponVO where cpon_dollar = ?";
	private static final String GET_Dates_STMT = "FROM CouponVO where release_date >= ? and cpon_period <= ? order by release_date";

	@Override
	public void insert(CouponVO couponVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(couponVO);
			session.getTransaction().commit();

		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public void update(CouponVO couponVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(couponVO);
			session.getTransaction().commit();

		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}

	}

	@Override
	public void delete(String cpon_id) {
		CouponVO copVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			copVO = (CouponVO)session.get(CouponVO.class, cpon_id);
			session.delete(copVO);
			session.getTransaction().commit();

		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}

	}

	@Override
	public CouponVO findByPrimaryKey(String cpon_id) {
		CouponVO copVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			copVO = (CouponVO)session.get(CouponVO.class, cpon_id);
			session.getTransaction().commit();
			
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return copVO;
	}

	@Override
	public List<CouponVO> getAll() {
		List<CouponVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ALL_STMT);
			list = query.list();		
			session.getTransaction().commit();
			
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}

	@Override
	public List<CouponVO> findByNames(String cpon_name) {
		List<CouponVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_Names_STMT);
			query.setParameter(0, cpon_name);
			list = query.list();
			session.getTransaction().commit();			
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}

	@Override
	public List<CouponVO> GroupByNames() {
		List<CouponVO> list = new ArrayList<CouponVO>();
		CouponVO couponVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_NameGroup_STMT);
			Iterator<String> iterator =	query.iterate();
			while(iterator.hasNext()) {
				couponVO = new CouponVO();
				couponVO.setCpon_name(iterator.next());			
				list.add(couponVO);
			}						
			session.getTransaction().commit();			
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}
	
	@Override
	public List<CouponVO> findByDateRange(Date release_date, Date cpon_period) {
		List<CouponVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery(GET_Dates_STMT);
			query.setParameter(0, release_date);
			query.setParameter(1, cpon_period);
			list = query.list();		
			session.getTransaction().commit();			
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}

	@Override
	public List<CouponVO> findByDollar(int cpon_dollar) {
		List<CouponVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_Dollar_STMT);
			query.setParameter(0, cpon_dollar);
			list = query.list();
			session.getTransaction().commit();			
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}

	@Override
	public List<CouponVO> GroupByDollar() {

		List<CouponVO> list = new ArrayList<CouponVO>();
		CouponVO couponVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_DollarGroup_STMT);			
			Iterator<Integer> iterator =  query.iterate();
			while(iterator.hasNext()) {
				couponVO = new CouponVO();
				couponVO.setCpon_dollar(iterator.next());			
				list.add(couponVO);
			}
			session.getTransaction().commit();			
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}


}
