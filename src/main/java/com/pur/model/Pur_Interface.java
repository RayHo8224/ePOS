package com.pur.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.company.model.ComVO;
import com.product.model.ProdVO;
import com.pur_detail.model.Pur_detailVO;

public interface Pur_Interface {
	public void insert(PurVO purVO);
	public void update(PurVO purVO);
	public void delete(String pur_id);
	public void deleteDetail(String pur_id,String prod_id);
	public PurVO findByPrimaryKey(String pur_id);
	public List<Pur_detailVO> findDetailByPrimaryKey(String pur_id);
	public List<PurVO> getAll();
	public void  setStatus(String status, String pur_id);
	public List<ComVO> getCom();
	public List<ProdVO> getProd(String com_id);
	public List<ProdVO> getProdById(String prod_name);
	public List<PurVO> findByDate(Date begin_date, Date end_date);
	public List<PurVO> selectOfN();
	public List<ComVO> getOneCom(String com_name);
	
}
