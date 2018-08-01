package com.order.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.order_detail.model.Order_DetailVO;
import com.product.model.ProdVO;
import com.returns.model.RtnDetailVO;
import com.employee.model.EmpVO;
import com.order.model.OrderVO;

import hibernate.util.HibernateUtil;

public class OrderHibernateDAO implements Order_Interface {

	@Override
	public OrderVO Select_order_id(String ord_id) throws Exception {
		//單檔查詢
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		OrderVO orderVO = new OrderVO();
		try {
			session.beginTransaction();
			orderVO = (OrderVO) session.get(OrderVO.class, ord_id);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return orderVO;
		
	}

	private static final String GET_ONE_STMT_ORDERDATE = "from OrderVO where ord_date between ? and ?";

	@Override
	public List Select_ord_date(Date s_ord_date, Date e_ord_date) throws Exception {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<OrderVO> list = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ONE_STMT_ORDERDATE);
			
			query.setParameter(0, s_ord_date);
			query.setParameter(1, e_ord_date);			
			
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
		return list;
	}

	@Override
	public OrderVO addOrder(OrderVO orderVO, List<Order_DetailVO> order_detailVO_list) throws Exception {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();	
			//寫主檔
			session.save(orderVO);
			//寫明細檔
			/*for(Order_DetailVO order_DetailVO : order_detailVO_list){
				session.save(order_DetailVO);
			}*/
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
		return orderVO;
	}

	@Override
	public void update(OrderVO orderVO, Order_DetailVO order_detailVO) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			//傳入的VO未提升至永續層
			session.saveOrUpdate(orderVO);		
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
	}

	@Override
	public void delete(String order_id) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			OrderVO orderVO = (OrderVO) session.get(OrderVO.class, order_id);//先查出來那一筆,並提升至永續類別
			session.delete(orderVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	private static final String GET_ALL_STMT = "from OrderVO order by ord_id";

	@Override
	public List<OrderVO> getAll() throws Exception {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<OrderVO> list = null;
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

	private static final String GET_ONE_STMT_PRODID = "select prod_id,prod_name,sum(prod_quantity) as quantity from Order_DetailVO where prod_id= ? group by prod_id,prod_name";

	@Override
	public List GetProdNameCount(String prod_id) throws Exception {
		Map map = new HashMap();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<Map> list = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ONE_STMT_PRODID);
			query.setParameter(0, prod_id);
			list = query.list();
			
			map = list.get(0);
			
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
		return list;
	}

	@Override
	public void setStatus(String status, String ord_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("update OrderVO set status='"+status+"'where ord_id='"+ord_id+"'");
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
	}

	@Override
	public ProdVO getOneProdid(String prod_id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmpVO getOneMemid(String mem_id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double GetDayTotalPrice() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long GetDayTotalPeople() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxInvoiceId() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OrderVO getOneTopOrdId() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List Select_ord_date_shift(Date s_ord_date, String shift) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderVO> getOrdPrice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderVO> getAllWeather(String weather) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderVO> getAllWeather() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	

}
