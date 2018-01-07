package com.returns.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import hibernate.util.HibernateUtil;

public class RtnDetailDAO_Hibernate implements RtnDetail_Interface{
	
	private static final String GET_ID ="FROM RtnDetailVO where ret_id = ?";
	private static final String GET_ALL_STMT = "FROM RtnDetailVO order by ret_id";
	private static final String GET_NAME ="FROM RtnDetailVO where prod_name = ?";

	@Override
	public void insert(RtnDetailVO rtnDetailVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.save(rtnDetailVO);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		
	}

	@Override
	public void update(RtnDetailVO rtnDetailVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.update(rtnDetailVO);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		
	}

	@Override
	public void delete(String ret_id, String prod_name) {
		RtnDetailVO rtnDetailVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			rtnDetailVO = new RtnDetailVO();
			RtnListVO rtnListVO = new RtnListVO();
			RtnItemsVO rtnItemsVO = new RtnItemsVO();
			rtnItemsVO.setProd_name(prod_name);
			rtnListVO.setRet_id(ret_id);
			rtnDetailVO.setRtnListVO(rtnListVO);
			rtnDetailVO.setRtnItemsVO(rtnItemsVO);
			
			rtnDetailVO=(RtnDetailVO) session.get(RtnDetailVO.class, rtnDetailVO);
//			Query query = session.createQuery("Delete From RETURN_DETAIL where ret_id='"+ret_id+"' AND prod_name='"+prod_name+"'");
			session.delete(rtnDetailVO);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
		}
		System.out.println();
		
	}

	@Override
	public RtnDetailVO findByPrimaryKey(String ret_id,String prod_name) {
		RtnDetailVO rtnDetailVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			rtnDetailVO = new RtnDetailVO();
			RtnListVO rtnListVO = new RtnListVO();
			RtnItemsVO rtnItemsVO = new RtnItemsVO();
			rtnItemsVO.setProd_name(prod_name);
			rtnListVO.setRet_id(ret_id);
			rtnDetailVO.setRtnListVO(rtnListVO);
			rtnDetailVO.setRtnItemsVO(rtnItemsVO);
			
			rtnDetailVO=(RtnDetailVO) session.get(RtnDetailVO.class, rtnDetailVO);
//			Query query = session.createQuery(GET_UPDATE);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		System.out.println(rtnDetailVO.getProd_quantity());
		return rtnDetailVO;
	}

	@Override
	public List<RtnDetailVO> findById(String ret_id) {
		List<RtnDetailVO> list = null;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery(GET_ID);
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
	public List<RtnDetailVO> findByName(String prod_name) {
		List<RtnDetailVO> list = null;
		RtnDetailVO rtnDetailVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery(GET_NAME);
			query.setParameter(0, prod_name);
			list=query.list();
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}

	@Override
	public List<RtnDetailVO> getAll() {
		List<RtnDetailVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery(GET_ALL_STMT);
			list=query.list();
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}
	
//	public static void main(String[] args) {
//		RtnDetailDAO dao = new RtnDetailDAO();
//		
//		List<RtnDetailVO> list = dao.getAll();
//		
//		for(RtnDetailVO d:list){
//			System.out.println(d.getRtnListVO().getRet_id());
//			System.out.println(d.getRtnItemsVO().getProd_name());
//			System.out.println(d.getRet_reason());
//			System.out.println(d.getProd_quantity());
//		}
//
//	}


	
}
