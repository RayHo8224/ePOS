package com.valuation.model;

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
import com.valuation_detail.model.Valuation_DetailVO;
import com.order.model.OrderVO;

import hibernate.util.HibernateUtil;

public class ValuationHibernateDAO implements Valuation_Interface {

	@Override
	public ValuationVO Select_vlt_id(String vlt_id) throws Exception {
		
		//單檔查詢
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				ValuationVO valuationVO = new ValuationVO();
				try {
					session.beginTransaction();
					valuationVO = (ValuationVO) session.get(ValuationVO.class, vlt_id);
					session.getTransaction().commit();
				} catch (RuntimeException ex) {
					session.getTransaction().rollback();
					throw ex;
				}
				return valuationVO;
	}

	private static final String GET_ONE_STMT_VALUATIONDATE = "from ValuationVO where vlt_date between ? and ?";
	
	@Override
	public List Select_vlt_date(Date s_vlt_date, Date e_vlt_date) throws Exception {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<ValuationVO> list = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ONE_STMT_VALUATIONDATE);
			
			query.setParameter(0, s_vlt_date);
			query.setParameter(1, e_vlt_date);			
			
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
		return list;
	}

	@Override
	public ValuationVO addVltList(ValuationVO valuationVO, List<Valuation_DetailVO> valuation_detailVO_list)
			throws Exception {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			//寫主檔
			session.save(valuationVO);
			//寫明細檔
			for(Valuation_DetailVO valuation_DetailVO : valuation_detailVO_list){
				session.save(valuation_DetailVO);
			}
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
		return valuationVO;
	}

	@Override
	public ValuationVO update(ValuationVO valuationVO, List<Valuation_DetailVO> valuation_detailVO_list)
			throws Exception {
		
		return null;
	}

	@Override
	public void delete(String vlt_id) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			ValuationVO valuationVO = (ValuationVO) session.get(ValuationVO.class, vlt_id);//先查出來那一筆,並提升至永續類別
			session.delete(valuationVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
	}

	private static final String GET_ALL_STMT = "from ValuationVO order by vlt_id";
	
	@Override
	public List<ValuationVO> getAll() throws Exception {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<ValuationVO> list = null;
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
	public void setStatus(String status, String vlt_id) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("update ValuationVO set status='"+status+"'where vlt_id='"+vlt_id+"'");
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
	}

	@Override
	public List<ValuationVO> getAllByN() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
}
