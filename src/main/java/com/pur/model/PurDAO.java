package com.pur.model;


import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.company.model.ComVO;
import com.product.model.ProdVO;
import com.pur_detail.model.Pur_detailVO;

public class PurDAO implements Pur_Interface {
	
	private static final String GETALL = "from PurVO order by pur_id";
	
	private static final String GET_DETAIL_ALL = "from Pur_detailVO where pur_id=?";
	
	private static final String GET_ALL_COM = "from ComVO order by com_id";
	
	private static final String GET_ALL_PROD = "from ProdVO where com_id = ? order by prod_id";
	
	private static final String GET_BY_DATE = "from PurVO where key_date between ? and ?";
	
	private static final String SELECT_OF_N = "from PurVO where status = 'N'";
	
	private static final String GET_ONE_COM = "from ComVO where com_name = ?";
	
	private static final String GET_PROD = "from ProdVO where prod_name = ?";
	
	

	
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Override
	public void insert(PurVO purVO) {
		// TODO Auto-generated method stub
		hibernateTemplate.saveOrUpdate(purVO);
			
	}

	@Override
	public void update(PurVO purVO) {
		// TODO Auto-generated method stub
//		hibernateTemplate.clear();
		hibernateTemplate.saveOrUpdate(purVO);
//		hibernateTemplate.flush();

	}

	@Override
	public void delete(String pur_id) {
		PurVO purVO = (PurVO) hibernateTemplate.get(PurVO.class, pur_id);
		hibernateTemplate.clear();
		hibernateTemplate.delete(purVO);
	}

	@Override
	public void deleteDetail(String pur_id,String prod_id) {
		hibernateTemplate.bulkUpdate("DELETE Pur_detailVO WHERE pur_id=? AND prod_id=?",new Object[]{pur_id,prod_id});	
		
	}
	
	@Override
	public PurVO findByPrimaryKey(String pur_id) {
		// TODO Auto-generated method stub
		PurVO purVO = (PurVO) hibernateTemplate.get(PurVO.class, pur_id);
		return purVO;
	}

	@Override
	public List<Pur_detailVO> findDetailByPrimaryKey(String pur_id) {
		// TODO Auto-generated method stub
//		Set<Pur_detailVO> set = findByPrimaryKey(pur_id).getPurs();
		List<Pur_detailVO> set = (List<Pur_detailVO>) hibernateTemplate.find(GET_DETAIL_ALL,pur_id);
		return set;
	}

	@Override
	public List<PurVO> getAll() {
		// TODO Auto-generated method stub
		List<PurVO> list = null;
		list = (List<PurVO>) hibernateTemplate.find(GETALL);
		return list;
	}
	
	@Override
	public void setStatus(String status, String pur_id){
		hibernateTemplate.bulkUpdate("update PurVO set status=? where pur_id=?",new Object[]{status,pur_id});
	}

	@Override
	public List<ComVO> getCom() {
		// TODO Auto-generated method stub
		List<ComVO> list = null;
		list = (List<ComVO>) hibernateTemplate.find(GET_ALL_COM);
		return list;
	}

	@Override
	public List<ProdVO> getProd(String com_id) {
		// TODO Auto-generated method stub
		List<ProdVO> list = null;
		list = (List<ProdVO>) hibernateTemplate.find(GET_ALL_PROD, com_id);
		return list;
	}

	
	
	@Override
	public List<ProdVO> getProdById(String prod_name) {
		// TODO Auto-generated method stub
		List<ProdVO> list = null;
		list = (List<ProdVO>) hibernateTemplate.find(GET_PROD, prod_name);
		return list;
	}

	@Override
	public List<PurVO> findByDate(Date begin_date, Date end_date) {
		// TODO Auto-generated method stub
		List<PurVO> list = null;
		list = (List<PurVO>) hibernateTemplate.find(GET_BY_DATE, new Object[]{begin_date,end_date});
		return list;
	}

	@Override
	public List<PurVO> selectOfN() {
		// TODO Auto-generated method stub
		List<PurVO> list=null;
		list = (List<PurVO>) hibernateTemplate.find(SELECT_OF_N);
		return list;
	}

	@Override
	public List<ComVO> getOneCom(String com_name) {
		// TODO Auto-generated method stub
		List<ComVO> list = null;
		list = (List<ComVO>) hibernateTemplate.find(GET_ONE_COM, com_name);
		return list;
	}


}
