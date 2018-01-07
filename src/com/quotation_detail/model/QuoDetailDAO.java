package com.quotation_detail.model;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.quotation.model.QuoVO;


public class QuoDetailDAO implements QuoDetail_Interface{

	private static final String GET_QUODETAIL_ByQuo_STMT = "from QuoDetailVO where quo_id=?"; 
	private static final String GET_PRODNAMEDETAIL_ByProdname_STMT = "from QuoDetailVO where prod_name=?";
	private static final String GET_ALL_STMT = "from QuoDetailVO order by quo_id";

	private HibernateTemplate hibernateTemplate;    
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) { 
        this.hibernateTemplate = hibernateTemplate;
    }
    
	@Override
	public void insert(List<QuoDetailVO> list) {

//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
//			session.beginTransaction();
//			session.save(quoVO);
//			session.getTransaction().commit();
//		} catch (RuntimeException ex) {
//			session.getTransaction().rollback();
//			throw ex;
//		}

	}
	
	@Override
	public void update(QuoDetailVO quoDetailVO) {

//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
//			session.beginTransaction();
//			session.update(quoVO);
//			session.getTransaction().commit();
//		} catch (RuntimeException ex) {
//			session.getTransaction().rollback();
//			throw ex;
//		}

	}

	@Override
	public void delete(String quo_id,String prod_name) {
		hibernateTemplate.bulkUpdate("DELETE QuoDetailVO WHERE quo_id=? AND prod_name=?",new Object[]{quo_id,prod_name});	
		
	}

	@Override
	public List<QuoDetailVO> findByQuoId(String quo_id) {
		List<QuoDetailVO> list = null;
		list=hibernateTemplate.find(GET_QUODETAIL_ByQuo_STMT,quo_id);
			
		return list;
	}

//	@Override
//	public List<QuoDetailVO> findByProdname(String prod_name) {
//		List<QuoDetailVO> list = null;
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
//			session.beginTransaction();
//			Query query = session.createQuery(GET_PRODNAMEDETAIL_ByProdname_STMT);
//			query.setParameter(0, prod_name);
//			list = query.list();
//			session.getTransaction().commit();
//		} catch (RuntimeException ex) {
//			session.getTransaction().rollback();
//			throw ex;
//		}
//		return list;
//	}
	
	@Override
	public List<QuoDetailVO> getAll() {
		List<QuoDetailVO> list = null;
		list = hibernateTemplate.find(GET_ALL_STMT);
	
	return list;
	}
}
