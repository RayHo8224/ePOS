package com.product.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;

import com.company.model.ComVO;

import hibernate.util.HibernateUtil;


public class ProdHibernateDAO implements ProdDAO_interface {

	
	
	private static final String GET_ALL_STMT = "from ProdVO order by prod_id";
	

	@Override
	public void insert(ProdVO prodVO) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(prodVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(ProdVO prodVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(prodVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		}

	@Override
	public void delete(String prod_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			ProdVO prodVO = (ProdVO) session.get(ProdVO.class, prod_id);
			session.delete(prodVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}
	@Override
	public ProdVO findByPrimaryKey(String prod_id) {
		ProdVO prodVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			prodVO = (ProdVO) session.get(ProdVO.class, prod_id);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return prodVO;	
		}
	@Override
	public List<ProdVO> getAll() {
		List<ProdVO> list = null;
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
		return list;	}


	@Override
	public List<ProdVO> findByName(String prod_name) {
		
		List<ProdVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from ProdVO where prod_name like '%"+prod_name+"%'");
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
		}
	
	@Override
	public List<ProdVO> findByGroup(String prod_group) {
		
		List<ProdVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from ProdVO where prod_group like '%"+prod_group+"%'");
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
		}

	@Override
	public void update2(Integer prod_stock, String prod_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProdVO> selectByGroup() {
		// TODO Auto-generated method stub
		return null;
	}
}
