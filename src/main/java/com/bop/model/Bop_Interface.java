package com.bop.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.bop_detail.model.Bop_detailVO;
import com.pur.model.PurVO;

public interface Bop_Interface {
	public void insert(BopVO bopVO);
	public void update(BopVO bopVO);
	public void delete(String bop_id);
	public void deleteDetail(String bop_id,String prod_id);
	public BopVO findByPrimaryKey(String bop_id);
	public List<Bop_detailVO> findDetailByPrimaryKey(String bop_id);
	public List<BopVO> getAll();
	public void setStatus(String status, String bop_id);
	public List<PurVO> selectOfY();
	public PurVO getThePur(String pur_id);
	public void  setStatus2(String status2, String pur_id);
	public List<BopVO> findByDate(Date begin_date, Date end_date);
	public List<BopVO> selectOfN();
	public List<BopVO> selectOfY2();
	public List<Object[]> getRatio(String bop_month);
	public List<BopVO> selectOfS();
	public String getMonthCost(String bop_month);
	public String getComName(String com_id);

}
