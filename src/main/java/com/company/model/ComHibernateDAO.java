package com.company.model;


import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;

import hibernate.util.HibernateUtil;

public class ComHibernateDAO implements ComDAO_interface {
	
	private static final String GET_ALL_STMT = "from ComVO order by com_id";

	
	@Override
	public void insert(ComVO comVO) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(comVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	

	@Override
	public void update(ComVO comVO) {		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(comVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		}

	@Override
	public void delete(String com_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			ComVO comVO = (ComVO) session.get(ComVO.class, com_id);
			session.delete(comVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public ComVO findByPrimaryKey(String com_id) {
		ComVO comVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			comVO = (ComVO) session.get(ComVO.class, com_id);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return comVO;	
		}

	@Override
	public List<ComVO> getAll() {
		List<ComVO> list = null;
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
	public List<ComVO> findByName(String com_name) {

		List<ComVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from ComVO where com_name like '%"+com_name+"%'");
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
		}
	
}
