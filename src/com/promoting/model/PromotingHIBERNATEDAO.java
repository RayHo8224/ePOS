package com.promoting.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import hibernate.util.HibernateUtil;

public class PromotingHIBERNATEDAO implements PromotingDAO_interface{
	
	private static final String DELETE = "DELETE FROM PromotingVO where pro_prod_id = ? AND pro_begin=?";
	private static final String GET_ONE_STMT = "FROM PromotingVO where pro_prod_id = ? AND pro_begin=?";
	private static final String GET_ALL_STMT = "FROM PromotingVO order by pro_prod_id";
	//private static final String SEARCH = "SELECT prod_id FROM PRODUCT where prod_id=?";
	private static final String GET_DATES_STMT = "FROM PromotingVO where pro_begin >=? and pro_end <=? ";
	private static final String GET_NAMES_STMT = "FROM PromotingVO where pro_prod_id like ?";
	private static final String GET_IDS_STMT = "FROM PromotingVO where pro_prod_id between ? and ? ";
	private static final String GET_BYIDGROUP_STMT = "FROM PromotingVO where pro_prod_id = ?";
	private static final String GET_IDGROUP_STMT = "SELECT pro_prod_id FROM PromotingVO group by pro_prod_id";
	
	@Override
	public void insert(PromotingVO promotingVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		session.save(promotingVO);
		session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public void update(PromotingVO promotingVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		session.saveOrUpdate(promotingVO);
		session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		
	}

	@Override
	public void delete(String pro_prod_id, Date pro_begin) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		Query query = session.createQuery(DELETE);	
		query.setParameter(0, pro_prod_id);
		query.setParameter(1, pro_begin);
		session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		
	}

	@Override
	public PromotingVO findByPrimaryKey(String pro_prod_id, Date pro_begin) {
		PromotingVO PromVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		Query query = session.createQuery(GET_ONE_STMT);
		query.setParameter(0, pro_prod_id);
		query.setParameter(1, pro_begin);
		List<PromotingVO> list=query.list();
		for(PromotingVO PromVO_one : list){
			PromVO = PromVO_one;
		};
		
		session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return PromVO;
	}

	@Override
	public List<PromotingVO> getAll() {
		List<PromotingVO> list = null;
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

	@Override
	public List<PromotingVO> getDates(Date pro_begin, Date pro_end) {
		List<PromotingVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		Query query = session.createQuery(GET_DATES_STMT);
		query.setParameter(0, pro_begin);
		query.setParameter(1, pro_end);
		list = query.list();
		session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}

	@Override
	public List<PromotingVO> getNames(String pro_prod_name) {
		List<PromotingVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		Query query = session.createQuery(GET_NAMES_STMT);
		query.setParameter(0, "%"+pro_prod_name+"%");
		list = query.list();
		session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}

	@Override
	public List<PromotingVO> getIds(String pro_prod_id1, String pro_prod_id2) {
		List<PromotingVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		Query query = session.createQuery(GET_IDS_STMT);
		query.setParameter(0, pro_prod_id1);
		query.setParameter(1, pro_prod_id2);
		list = query.list();
		session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}

	@Override
	public List<PromotingVO> GroupByIDs() {
		List<PromotingVO> list = new ArrayList<PromotingVO>();
		PromotingVO PromVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_IDGROUP_STMT);
			Iterator<String> iterator =	query.iterate();
			while(iterator.hasNext()) {
				PromVO = new PromotingVO();
				PromVO.setPro_prod_id(iterator.next());;			
				list.add(PromVO);
			}						
			session.getTransaction().commit();			
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}

	@Override
	public List<PromotingVO> findByIDs(String pro_prod_id) {
		List<PromotingVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
		session.beginTransaction();
		Query query = session.createQuery(GET_BYIDGROUP_STMT);
		query.setParameter(0, pro_prod_id);
		list = query.list();
		session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}
}
