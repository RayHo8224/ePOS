package com.shipments_detail.model;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import hibernate.util.HibernateUtil;


public class ShipdetailHibernateDAO implements ShipdetailDAO_interface {

	private static final String GET_SHIPDETAIL_ByShip_STMT = "from ShipdetailVO where ship_id=?"; 
	
	
	 @Override
	public void insert(ShipdetailVO shipdetailVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ShipdetailVO shipdetailVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String ship_id, String prod_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createSQLQuery("DELETE FROM　SHIPMENTS_DETAIL WHERE ship_id='"+ship_id+"' AND prod_id='"+prod_id+"'");	
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
	}

	@Override
	public List<ShipdetailVO> findByShipId(String ship_id) {
		List<ShipdetailVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_SHIPDETAIL_ByShip_STMT);
			query.setParameter(0, ship_id);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	@Override
	public List<ShipdetailVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
