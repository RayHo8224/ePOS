package com.valuation.model;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import com.valuation_detail.model.Valuation_DetailVO;

public interface Valuation_Interface {

	
	public ValuationVO Select_vlt_id(String vlt_id) throws Exception;
	public List Select_vlt_date(Date s_vlt_date,Date e_vlt_date) throws Exception;
	public ValuationVO addVltList(ValuationVO valuationVO,List<Valuation_DetailVO> valuation_detailVO_list) throws Exception;
	public ValuationVO update(ValuationVO valuationVO,List<Valuation_DetailVO> valuation_detailVO_list) throws Exception;
	public void delete(String vlt_id) throws Exception;
	public List<ValuationVO> getAll() throws Exception;
	public void setStatus(String status,String vlt_id) throws Exception;
	public List<ValuationVO> getAllByN() throws Exception;
	
}
