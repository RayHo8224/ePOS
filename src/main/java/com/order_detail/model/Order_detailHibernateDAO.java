package com.order_detail.model;

import java.util.LinkedList;
import java.util.List;

import com.order.model.OrderVO;

import org.hibernate.Query;
import org.hibernate.Session;
import hibernate.util.HibernateUtil;

public class Order_detailHibernateDAO implements Order_Detail_Interface{

	private static final String GET_ONE_STMT_ORDERID = "from Order_DetailVO where ord_id=?";

	@Override
	public List Select_order_detailALL(String ord_id) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<OrderVO> list = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ONE_STMT_ORDERID);
			
			query.setParameter(0, ord_id);	
			
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
		return list;
	}

	private static final String GET_ONE_STMT_ORDER_SINGLE = "from Order_DetailVO where ord_id=? and prod_id=?";

	@Override
	public Order_DetailVO Select_order_detail(String ord_id, String prod_id) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<Order_DetailVO> list = null;
		Order_DetailVO order_DetailVO = new Order_DetailVO();
		
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ONE_STMT_ORDER_SINGLE);
			query.setParameter(0, ord_id);
			query.setParameter(1, prod_id);
			
			list = query.list();
			order_DetailVO = list.get(0);
			
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
		return order_DetailVO;
	}

	@Override
	public void insert(Order_DetailVO order_detailVO, OrderVO orderVO) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			
			session.save(order_detailVO);
			session.saveOrUpdate(orderVO);
			
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update_Detail(Order_DetailVO order_detailVO, OrderVO orderVO) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			
			session.saveOrUpdate(order_detailVO);
			session.saveOrUpdate(orderVO);
			
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	private static final String DELETE_ORDER_DETAIL = "delete from Order_DetailVO where ord_id=? and prod_id=?";

	@Override
	public void delete(String order_id, String prod_id) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(DELETE_ORDER_DETAIL);
			query.setString(0, order_id);
			query.setParameter(1, prod_id);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
	}

	@Override
	public List<Order_DetailVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
