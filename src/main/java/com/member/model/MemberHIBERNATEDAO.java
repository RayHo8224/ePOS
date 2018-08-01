package com.member.model;

import java.sql.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import hibernate.util.HibernateUtil;

public class MemberHIBERNATEDAO implements MemberDAO_interface{
	private static final String GET_ALL_STMT = "FROM MemberVO order by mem_id";
	private static final String GET_IDS_STMT = "FROM MemberVO where mem_id between ? and ?";
	private static final String GET_K_DATES_STMT = "FROM MemberVO where key_date between ? and ?";
	

	@Override
	public void insert(MemberVO memberVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.save(memberVO);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		
	}

	@Override
	public void update(MemberVO memberVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		session.saveOrUpdate(memberVO);
		session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public void delete(String mem_id) {
		MemberVO memberVO =null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			memberVO = (MemberVO)session.get(MemberVO.class, mem_id);
			session.delete(memberVO);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		
	}

	@Override
	public MemberVO findByPrimaryKey(String mem_id) {
		MemberVO memberVO =null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			memberVO = (MemberVO)session.get(MemberVO.class, mem_id);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}		
		return memberVO;
	}

	@Override
	public List<MemberVO> getIds(String mem_id_1, String mem_id_2) {
		List<MemberVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		Query query = session.createQuery(GET_IDS_STMT);
		query.setParameter(0, mem_id_1);
		query.setParameter(1, mem_id_2);
		list = query.list();
		session.getTransaction().commit();	
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}

	@Override
	public List<MemberVO> getDates(Date key_date_1, Date key_date_2) {
		List<MemberVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		Query query = session.createQuery(GET_K_DATES_STMT);
		query.setParameter(0, key_date_1);
		query.setParameter(1, key_date_2);
		list = query.list();
		session.getTransaction().commit();	
		}catch(RuntimeException e){
			session.getTransaction().rollback();	
			throw e;
		}
		return list;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = null;
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
