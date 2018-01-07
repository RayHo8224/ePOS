package com.requisition_detail.model;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.engine.query.ReturnMetadata;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.quotation_detail.model.QuoDetailVO;

import hibernate.util.HibernateUtil;

public class ReqDetailDAO implements ReqDetail_Interface{	

	private static final String GET_ONE_STMT = "FROM ReqDetailVO where req_id = ?";
	
	private HibernateTemplate hibernateTemplate;    
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) { 
        this.hibernateTemplate = hibernateTemplate;
    }
	@Override
	public void insert(ReqDetailVO reqDetailVO) {

	}
	@Override
	public void update(ReqDetailVO reqDetailVO) {

	}
	@Override
	public void delete(String req_id, String prod_name) {
		hibernateTemplate.bulkUpdate("DELETE ReqDetailVO WHERE req_id=? AND prod_name=?",new Object[]{req_id,prod_name});	
	}
	@Override
	public List<ReqDetailVO> findByReqId(String req_id) {
		List<ReqDetailVO> list = null;
		list=hibernateTemplate.find(GET_ONE_STMT,req_id);
			
		return list;
	}

	@Override
	public List<ReqDetailVO> getAll() {
		return null;
	}


}
