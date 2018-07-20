package com.valuation.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.order_detail.model.Order_DetailVO;
import com.valuation_detail.model.Valuation_DetailVO;
import com.order.model.OrderVO;

import hibernate.util.HibernateUtil;

public class ValuationSpringDAO implements Valuation_Interface {

	private HibernateTemplate hibernateTemplate;    
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) { 
    	
        this.hibernateTemplate = hibernateTemplate;
    }
	
    
	
	@Override
	public ValuationVO Select_vlt_id(String vlt_id) throws Exception {
		
		ValuationVO valuationVO = null;
		valuationVO = (ValuationVO) hibernateTemplate.get(ValuationVO.class,vlt_id);
		return valuationVO;
	}

	private static final String GET_ONE_STMT_VALUATIONDATE = "from ValuationVO where vlt_date between ? and ?";
	
	@Override
	public List Select_vlt_date(Date s_vlt_date, Date e_vlt_date) throws Exception {
		
		List<OrderVO> list = null;
		list= (List<OrderVO>) hibernateTemplate.find(GET_ONE_STMT_VALUATIONDATE,new Object[]{s_vlt_date,e_vlt_date});
			
		return list;
	}

	@Override
	public ValuationVO addVltList(ValuationVO valuationVO, List<Valuation_DetailVO> valuation_detailVO_list)
			throws Exception {
		
		hibernateTemplate.save(valuationVO);
		
		return valuationVO;
	}

	@Override
	public ValuationVO update(ValuationVO valuationVO, List<Valuation_DetailVO> valuation_detailVO_list)
			throws Exception {
		
		return null;
	}

	@Override
	public void delete(String vlt_id) throws Exception {
		
		ValuationVO valuationVO = (ValuationVO) hibernateTemplate.get(ValuationVO.class, vlt_id);
		hibernateTemplate.delete(valuationVO);
		
	}

	private static final String GET_ALL_STMT = "from ValuationVO order by vlt_id";
	
	@Override
	public List<ValuationVO> getAll() throws Exception {
		
		List<ValuationVO> list = null;
		list= (List<ValuationVO>) hibernateTemplate.find(GET_ALL_STMT);
		
		return list;
	}
	
	private static final String GET_ALL_STMT_BY_N = "from ValuationVO where status = 'N' order by vlt_id ";
	
	@Override
	public List<ValuationVO> getAllByN() throws Exception {
		
		List<ValuationVO> list = null;
		list= (List<ValuationVO>) hibernateTemplate.find(GET_ALL_STMT_BY_N);
		
		return list;
	}
	
	

	@Override
	public void setStatus(String status, String vlt_id) throws Exception {
//		hibernateTemplate.bulkUpdate("update ValuationVO set status='"+status+"'where vlt_id='"+vlt_id+"'");
		hibernateTemplate.bulkUpdate("update ValuationVO set status=? where vlt_id=?",new Object[]{status,vlt_id});
	}

	
	
}
