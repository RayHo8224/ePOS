package com.order_detail.model;

import java.util.LinkedList;
import java.util.List;

import com.order.model.OrderVO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import hibernate.util.HibernateUtil;

public class Order_detailSpringDAO implements Order_Detail_Interface{

	private HibernateTemplate hibernateTemplate;    
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) { 
    	
        this.hibernateTemplate = hibernateTemplate;
    }
	
    private static final String GET_ONE_STMT_ORDERID = "from Order_DetailVO where ord_id=?";
    
	@Override
	public List Select_order_detailALL(String ord_id) throws Exception {
		
		List<Order_DetailVO> list = null;
		list=hibernateTemplate.find(GET_ONE_STMT_ORDERID,ord_id);
			
		return list;
	}

	private static final String GET_ONE_STMT_ORDER_SINGLE = "from Order_DetailVO where ord_id=? and prod_id=?";

	@Override
	public Order_DetailVO Select_order_detail(String ord_id, String prod_id) throws Exception {
		
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		List<Order_DetailVO> list = null;
//		Order_DetailVO order_DetailVO = new Order_DetailVO();
//		
//		try {
//			session.beginTransaction();
//			Query query = session.createQuery(GET_ONE_STMT_ORDER_SINGLE);
//			query.setParameter(0, ord_id);
//			query.setParameter(1, prod_id);
//			
//			list = query.list();
//			order_DetailVO = list.get(0);
//			
//			session.getTransaction().commit();
//		} catch (RuntimeException ex) {
//			session.getTransaction().rollback();
//			throw ex;
//		}
//		
//		return order_DetailVO;
		return null;
	}

	@Override
	public void insert(Order_DetailVO order_detailVO, OrderVO orderVO) throws Exception {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		
//		try {
//			session.beginTransaction();
//			
//			session.save(order_detailVO);
//			session.saveOrUpdate(orderVO);
//			
//			session.getTransaction().commit();
//		} catch (RuntimeException ex) {
//			session.getTransaction().rollback();
//			throw ex;
//		}
	}

	@Override
	public void update_Detail(Order_DetailVO order_detailVO, OrderVO orderVO) throws Exception {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		
//		try {
//			session.beginTransaction();
//			
//			session.saveOrUpdate(order_detailVO);
//			session.saveOrUpdate(orderVO);
//			
//			session.getTransaction().commit();
//		} catch (RuntimeException ex) {
//			session.getTransaction().rollback();
//			throw ex;
//		}
	}

	//private static final String DELETE_ORDER_DETAIL = "delete from Order_DetailVO where ord_id=? and prod_id=?";

	@Override
	public void delete(String order_id, String prod_id) throws Exception {
		
		hibernateTemplate.bulkUpdate("DELETE Order_DetailVO WHERE ord_id=? AND prod_id=?",new Object[]{order_id,prod_id});	
	}

	private static final String GET_ALL_STMT = "from Order_DetailVO";

	@Override
	public List<Order_DetailVO> getAll() {

		List<Order_DetailVO> list = null;
		list = hibernateTemplate.find(GET_ALL_STMT);

		return list;
	}
}
