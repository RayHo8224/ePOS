package com.quotation.model;

import java.sql.Date;
import java.util.List;

import com.company.model.ComVO;
import com.product.model.ProdVO;
import com.quotation_detail.model.QuoDetailVO;
import com.requisition.model.ReqVO;;

public interface Quo_Interface {
	public void insert(QuoVO quoVO,List<QuoDetailVO> list);
	public void insertProduct(ProdVO prodVO);
    public void update(QuoVO quoVO);
    public void delete(String quo_id);
    public QuoVO findByPrimaryKey(String quo_id);
//    public Set<QuoDetailVO> findDetailByPrimaryKey(String quo_id);    
    public List<QuoVO> findByReqid(String req_id);
    public List<QuoVO> getAll();
    public List<QuoVO> findByDate(Date begin_date, Date end_date );
	public void setStatus(String status,String quo_id) throws Exception;
	public List<ReqVO> findYReq();
	public ReqVO findByReqKey(String req_id);
	public List<QuoVO> selectOfN();
	public List<QuoVO> selectOfY();
	public void setReqStatus(String status2, String req_id);
	public String getComId(String com_name);
	public List<ProdVO> getAllProd();
	public List<ComVO> getCom();
}
