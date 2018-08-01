package com.returns.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import hibernate.util.HibernateUtil;

public class RtnItemsDAO_Hibernate implements RtnItems_Interface{
	private static final String GET_NAME ="FROM RtnItemsVO where prod_name = ?";
	private static final String GET_ID ="FROM RtnItemsVO where com_id = ?";

	@Override
	public void insert(RtnItemsVO rtnItemVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.save(rtnItemVO);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		
	}

	@Override
	public void update(RtnItemsVO rtnItemsVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.update(rtnItemsVO);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		
	}

	@Override
	public void delete(String prod_name) {
		RtnItemsVO rtnItemsVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			rtnItemsVO=(RtnItemsVO) session.get(RtnItemsVO.class, prod_name);
			session.delete(rtnItemsVO);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
		}
		
	}

	@Override
	public RtnItemsVO findByPrimaryKey(String prod_name) {
		RtnItemsVO rtnItemsVO =null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			rtnItemsVO=(RtnItemsVO) session.get(RtnItemsVO.class, prod_name);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return rtnItemsVO;
	}

	@Override
	public List<RtnItemsVO> findByName(String prod_name) {
		List<RtnItemsVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery(GET_NAME);
			query.setParameter(0, prod_name);
			list = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}

	@Override
	public List<RtnItemsVO> findById(String com_id) {
		List<RtnItemsVO> list =null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery(GET_ID);
			query.setParameter(0, com_id);
			list = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}
	
	private static final String GET_ALL_STMT = "FROM RtnItemsVO order by prod_name";

	@Override
	public List<RtnItemsVO> getAll() {
		List<RtnItemsVO> list =null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery(GET_ALL_STMT);
			list = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}


}
