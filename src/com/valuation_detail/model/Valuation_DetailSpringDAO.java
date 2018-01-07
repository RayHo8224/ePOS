package com.valuation_detail.model;

import java.util.LinkedList;
import java.util.List;

import com.order.model.OrderVO;
import com.order_detail.model.Order_DetailVO;
import com.valuation.model.ValuationVO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import hibernate.util.HibernateUtil;

public class Valuation_DetailSpringDAO implements Valuation_Detail_Interface {


	private HibernateTemplate hibernateTemplate;    
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) { 
    	
        this.hibernateTemplate = hibernateTemplate;
    }
	
    private static final String GET_ONE_STMT_VALUAYIONID = "from Valuation_DetailVO where vlt_id=?";
    
	@Override
	public List Select_valuation_detailALL(String vlt_id) throws Exception {
		List<Order_DetailVO> list = null;
		list=hibernateTemplate.find(GET_ONE_STMT_VALUAYIONID,vlt_id);
		return list;
	}

	private static final String GET_ONE_STMT_VALUATION_SINGLE = "from Valuation_DetailVO where vlt_id=? and prod_id=?";

	@Override
	public Valuation_DetailVO Select_valuation_detail(String vlt_id, String prod_id) throws Exception {

//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		List<Valuation_DetailVO> list = null;
//		Valuation_DetailVO valuation_DetailVO = new Valuation_DetailVO();
//
//		try {
//			session.beginTransaction();
//			Query query = session.createQuery(GET_ONE_STMT_VALUATION_SINGLE);
//			query.setParameter(0, vlt_id);
//			query.setParameter(1, prod_id);
//
//			list = query.list();
//			valuation_DetailVO = list.get(0);
//
//			session.getTransaction().commit();
//		} catch (RuntimeException ex) {
//			session.getTransaction().rollback();
//			throw ex;
//		}
//
//		return valuation_DetailVO;
		return null;
	}

	@Override
	public void insert(Valuation_DetailVO valuation_detailVO, ValuationVO valuationVO) throws Exception {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//
//		try {
//			session.beginTransaction();
//
//			session.save(valuation_detailVO);
//			session.saveOrUpdate(valuationVO);
//
//			session.getTransaction().commit();
//		} catch (RuntimeException ex) {
//			session.getTransaction().rollback();
//			throw ex;
//		}

	}

	@Override
	public ValuationVO updateVltlist(List<Valuation_DetailVO> valuation_detailVO_list, ValuationVO valuationVO)
			throws Exception {

		return null;
	}

	@Override
	public void update(Valuation_DetailVO valuation_DetailVO, ValuationVO valuationVO) throws Exception {

	}

	private static final String DELETE_VALUATION_DETAIL = "delete from Valuation_DetailVO where vlt_id=? and prod_id=?";
	
	@Override
	public void delete(String vlt_id, String prod_id) throws Exception {

		hibernateTemplate.bulkUpdate("DELETE Valuation_DetailVO WHERE vlt_id=? AND prod_id=?",new Object[]{vlt_id,prod_id});	
	}

}
