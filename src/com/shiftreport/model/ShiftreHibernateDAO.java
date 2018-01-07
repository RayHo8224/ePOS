package com.shiftreport.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
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

import com.company.model.ComJDBCDAO;
import com.company.model.ComVO;

import hibernate.util.HibernateUtil;

public class ShiftreHibernateDAO implements ShiftreDAO_interface {
	
	private static final String GET_ALL_STMT = "from ShiftreVO order by Date";

	
	@Override
	public void insert(ShiftreVO shiftreVO) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(shiftreVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}


	}

	@Override
	public void update(ShiftreVO shiftreVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(shiftreVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(Date Date, String shift) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("delete from ShiftreVO where Date=? and shift=?");	
			query.setParameter(0, Date);
			query.setParameter(1, shift);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
	}

	@Override
	public ShiftreVO findByPrimaryKey(Date Date, String shift) {
		ShiftreVO shiftreVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
	
			Query query = session.createQuery("from ShiftreVO where Date=? and shift=?");	
			query.setParameter(0, Date);
			query.setParameter(1, shift);
			
			List<ShiftreVO> list=query.list();
			for(ShiftreVO shiftreVO1 : list){
				shiftreVO=shiftreVO1;
			}
			
			session.getTransaction().commit();

		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return shiftreVO;	
	}

	@Override
	public List<ShiftreVO> getAll() {
		List<ShiftreVO> list = null;
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
	public List<ShiftreVO> findByDate(Date Date) {
		List<ShiftreVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from ShiftreVO where Date ='"+Date+"'");
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	@Override
	public List getSumJson(Date date1, Date date2, String shift) {
		// TODO Auto-generated method stub
		return null;
	}


}
