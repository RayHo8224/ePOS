package com.shipments.model;

import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.shipments_detail.model.ShipdetailVO;

import hibernate.util.HibernateUtil;

public class ShipHibernateDAO implements ShipDAO_interface {
	
	
	private static final String GET_ALL_STMT = "from ShipVO order by ship_id";

	private static final String GET_ONE_STMT_ByDate = "from ShipVO where ship_date between ? and ?";

	private static final String GET_ONE_STMT_ByOrderId = "from ShipVO where ord_id = ?";


	@Override
	public void insert(ShipVO shipVO, List<ShipdetailVO> list) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(shipVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}

	}			

	@Override
	public void update(ShipVO shipVO) {
	
	}

	@Override
	public void delete(String ship_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			ShipVO shipVO = (ShipVO) session.get(ShipVO.class, ship_id);
			session.delete(shipVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}


	@Override
	public ShipVO findByPrimaryKey(String ship_id) {
		ShipVO shipVO = null;

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			shipVO = (ShipVO) session.get(ShipVO.class, ship_id);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return shipVO;
	}
		

	@Override
	public List<ShipVO> getAll() {
	
		List<ShipVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ALL_STMT);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
		

	@Override
	public List<ShipVO> findByDate(Date dateBegin, Date dateEnd) {
		List<ShipVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ONE_STMT_ByDate);
			query.setParameter(0, dateBegin);
			query.setParameter(1, dateEnd);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
	
	@Override
	public List<ShipVO> findByOrderId(String ord_id) {
		List<ShipVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ONE_STMT_ByOrderId);
			query.setParameter(0, ord_id);

			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
	
}
