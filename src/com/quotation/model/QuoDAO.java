package com.quotation.model;

import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.company.model.ComVO;
import com.product.model.ProdVO;
import com.quotation.model.QuoVO;
import com.quotation_detail.model.QuoDetailVO;
import com.requisition.model.ReqVO;
import com.shipments.model.ShipVO;

import hibernate.util.HibernateUtil;


public class QuoDAO implements Quo_Interface{

	private static final String GET_ALL_STMT = "from QuoVO order by quo_id";

	private static final String GET_BY_DATE = "from QuoVO where key_date between ? and ?";
	
	private static final String GET_ONE_STMT_ByReqId = "from QuoVO where req_id = ?";
	
	private static final String GET_Y_REQ = "from ReqVO where status = 'Y'";
	
	private static final String GET_ONE_REQ = "from ReqVO where req_id = ?";
	
	private static final String SELECT_OF_N = "from QuoVO where status = 'N'";
	
	private static final String SELECT_OF_Y = "from QuoVO where status = 'Y'";
	
	private static final String GET_COM_ID = "from ComVO where com_name = ? ";
	
	private static final String GET_ALL_PROD = "from ProdVO order by prod_id";
	
	private static final String GET_ALL_COM ="from ComVO order by com_id";
	


	private HibernateTemplate hibernateTemplate;
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public void insert(QuoVO quoVO,List<QuoDetailVO> list) {
		hibernateTemplate.save(quoVO);
	}			

	@Override
	public void insertProduct(ProdVO prodVO) {
		// TODO Auto-generated method stub
		hibernateTemplate.save(prodVO);
		
	}
	
	@Override
	public void update(QuoVO quoVO) {

		hibernateTemplate.update(quoVO);
	}

	@Override
	public void delete(String quo_id) {
		QuoVO quoVO = (QuoVO) hibernateTemplate.get(QuoVO.class, quo_id);
		hibernateTemplate.delete(quoVO);
	}
	
	@Override
	public QuoVO findByPrimaryKey(String quo_id) {
		QuoVO quoVO =null;
		
			quoVO =(QuoVO) hibernateTemplate.get(QuoVO.class, quo_id);
		return quoVO;
	}
	
	@Override
	public List<QuoVO> findByReqid(String req_id) {
		List<QuoVO> list = null;
			list = hibernateTemplate.find(GET_ONE_STMT_ByReqId,req_id);
		return list;
	}

	@Override
	public List<QuoVO> getAll() {

		List<QuoVO> list = null;
			list = hibernateTemplate.find(GET_ALL_STMT);
		
		return list;
	}
	
	@Override
	public List<QuoVO> findByDate(Date begin_date, Date end_date) {
		List<QuoVO> list = null;
		list= hibernateTemplate.find(GET_BY_DATE,new Object[]{begin_date,end_date});
			
		return list;
	}
	
	@Override
	public void setStatus(String status, String quo_id) throws Exception {
		hibernateTemplate.bulkUpdate("update QuoVO set status=? where quo_id=?",new Object[]{status,quo_id});
	}

	@Override
	public List<ReqVO> findYReq() {
		// TODO Auto-generated method stub
		List<ReqVO> list = null;
		list = hibernateTemplate.find(GET_Y_REQ);
		return list;
	}

	@Override
	public ReqVO findByReqKey(String req_id) {
		// TODO Auto-generated method stub
		ReqVO reqVO = (ReqVO) hibernateTemplate.get(ReqVO.class, req_id);
		return reqVO;
	}

	@Override
	public List<QuoVO> selectOfN() {
		// TODO Auto-generated method stub
		List<QuoVO> list = null;
		list = hibernateTemplate.find(SELECT_OF_N);
		return list;
	}

	@Override
	public void setReqStatus(String status2, String req_id) {
		// TODO Auto-generated method stub
		hibernateTemplate.bulkUpdate("update ReqVO set status=? where req_id=?",new Object[]{status2,req_id});
		
	}

	@Override
	public List<QuoVO> selectOfY() {
		// TODO Auto-generated method stub
		List<QuoVO> list = null;
		list = hibernateTemplate.find(SELECT_OF_Y);
		return list;
	}

	@Override
	public String getComId(String com_name) {
		// TODO Auto-generated method stub
		String com_id = null;
		List<ComVO> list = hibernateTemplate.find(GET_COM_ID, com_name);
		com_id = list.get(0).getCom_id();
		return com_id;
	}

	@Override
	public List<ProdVO> getAllProd() {
		// TODO Auto-generated method stub
		List<ProdVO> list = null;
		list = hibernateTemplate.find(GET_ALL_PROD);
		return list;
	}

	@Override
	public List<ComVO> getCom() {
		// TODO Auto-generated method stub
		List<ComVO> list = null;
		list = hibernateTemplate.find(GET_ALL_COM);
		return list;
	}


}
