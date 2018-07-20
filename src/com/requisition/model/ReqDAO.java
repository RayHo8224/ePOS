package com.requisition.model;

import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.requisition_detail.model.ReqDetailVO;

import hibernate.util.HibernateUtil;

import com.order.model.OrderVO;
import com.product.model.ProdVO;
import com.requisition.model.ReqVO;

public class ReqDAO implements Req_Interface{
	
	private static final String GET_ALL_STMT = "from ReqVO order by req_id";
	private static final String GET_BY_DATE = "from ReqVO where key_date between ? and ?";
	private static final String SELECT_OF_N = "from ReqVO where status = 'N'";
	private static final String GET_ALL_PROD = "from ProdVO order by prod_id";
	
	private HibernateTemplate hibernateTemplate;
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Override
	public void insert(ReqVO reqVO,List<ReqDetailVO> list) {
		hibernateTemplate.save(reqVO);
	}
	
	@Override
	public void update(ReqVO reqVO) {
		hibernateTemplate.update(reqVO);
	}
	
	@Override
	public void delete(String req_id) {
		ReqVO reqVO = (ReqVO)hibernateTemplate.get(ReqVO.class, req_id);
		hibernateTemplate.delete(reqVO);
	}
	
	@Override
	public ReqVO findByPrimaryKey(String req_id) {
		ReqVO reqVO = null;
		
			reqVO = (ReqVO)hibernateTemplate.get(ReqVO.class, req_id);		
		return reqVO;
	}
	
	@Override
	public List<ReqVO> getAll() {
		List<ReqVO> list = null;
		list = (List<ReqVO>) hibernateTemplate.find(GET_ALL_STMT);
		return list;
	}
	@Override
	public void setStatus(String status, String req_id) throws Exception {
		hibernateTemplate.bulkUpdate("update ReqVO set status=? where req_id=?",new Object[]{status,req_id});
	}

	@Override
	public List<ReqVO> findByDate(Date beginDate, Date endDate) {
		// TODO Auto-generated method stub
		List<ReqVO> list = null;
		list= (List<ReqVO>) hibernateTemplate.find(GET_BY_DATE,new Object[]{beginDate,endDate});
			
		return list;
	}

	@Override
	public List<ReqVO> selectOfN() {
		// TODO Auto-generated method stub
		List<ReqVO> list = null;
		list = (List<ReqVO>) hibernateTemplate.find(SELECT_OF_N);
		return list;
	}

	@Override
	public List<ProdVO> getAllProd() {
		// TODO Auto-generated method stub
		List<ProdVO> list = null;
		list = (List<ProdVO>) hibernateTemplate.find(GET_ALL_PROD);
		return list;
	}
	

//	@Override
//	public Set<ReqDetailVO> getDetails(String req_id) {
//		Set<ReqDetailVO> set = findByPrimaryKey(req_id).getReq_dtls();
//		return set;
//	}
}
