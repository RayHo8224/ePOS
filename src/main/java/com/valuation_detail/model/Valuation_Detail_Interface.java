package com.valuation_detail.model;

import java.util.LinkedList;
import java.util.List;

import com.order.model.OrderVO;
import com.order_detail.model.Order_DetailVO;
import com.valuation.model.ValuationVO;

public interface Valuation_Detail_Interface {
	
	public List Select_valuation_detailALL(String vlt_id) throws Exception;
	public Valuation_DetailVO Select_valuation_detail(String vlt_id,String prod_id) throws Exception;
	public ValuationVO updateVltlist(List<Valuation_DetailVO> valuation_detailVO_list,ValuationVO valuationVO) throws Exception;//主檔更新，明細刪除
	public void insert(Valuation_DetailVO valuation_detailVO,ValuationVO valuationVO) throws Exception;//insert該筆訂單的商品
	public void update(Valuation_DetailVO valuation_DetailVO,ValuationVO valuationVO) throws Exception;
	public void delete(String vlt_id,String prod_id) throws Exception;
	//public List<Valuation_DetailVO> getAll();
		
}
