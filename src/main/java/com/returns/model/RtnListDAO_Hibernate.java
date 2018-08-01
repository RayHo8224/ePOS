package com.returns.model;

import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import hibernate.util.HibernateUtil;

public class RtnListDAO_Hibernate implements RtnList_Interface{
	
	private static final String GET_ALL_LIST = "FROM RtnListVO order by ret_id";
	private static final String FIND_ID = "FROM RtnListVO where ret_id = ?";
	private static final String FIND_COM = "FROM RtnListVO where com_name = ?";
	private static final String GET_DETAIL = "FROM RtnDetailVO where ret_id = ?";
	private static final String FIND_DATE ="FROM RtnListVO where ret_date = ?";
	
	@Override
	public void insert(RtnListVO rtnListVO, List<RtnDetailVO> listDetail) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.save(rtnListVO);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		
	}

	@Override
	public void update(RtnListVO rtnListVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.update(rtnListVO);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		
	}

	@Override
	public void delete(String ret_id) {
		RtnListVO rtnListVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			rtnListVO=(RtnListVO) session.get(RtnListVO.class, ret_id);
			session.delete(rtnListVO);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
		}
		
	}

	@Override
	public RtnListVO findByPrimaryKey(String ret_id) {
		RtnListVO rtnListVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			rtnListVO=(RtnListVO) session.get(RtnListVO.class, ret_id);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return rtnListVO;
	}

	@Override
	public List<RtnListVO> findByDate(Date ret_date) {
		List<RtnListVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery(FIND_DATE);
			query.setParameter(0, ret_date);
			list=query.list();
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}

	@Override
	public List<RtnListVO> getfindById(String ret_id) {
		List<RtnListVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery(FIND_ID);
			query.setParameter(0, ret_id);
			list=query.list();
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}

	@Override
	public List<RtnListVO> findByCom(String com_name) {
		List<RtnListVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery(FIND_COM);
			query.setParameter(0, com_name);
			list=query.list();
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}

	@Override
	public List<RtnListVO> getAll() {
		List<RtnListVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery(GET_ALL_LIST);
			list=query.list();
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}

	@Override
	public List<RtnDetailVO> findByDetail(String ret_id) {
		List<RtnDetailVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery(GET_DETAIL);
			query.setParameter(0, ret_id);
			list=query.list();
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}

}
