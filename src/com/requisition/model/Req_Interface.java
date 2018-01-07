package com.requisition.model;

import java.sql.Date;
import java.util.List;

import com.product.model.ProdVO;
import com.requisition_detail.model.ReqDetailVO;

public interface Req_Interface {
	public void insert(ReqVO reqVO,List<ReqDetailVO> list);
    public void update(ReqVO reqVO);
    public void delete(String req_id);
    public ReqVO findByPrimaryKey(String req_id);
//    public Set<ReqDetailVO> getDetails(String req_id);
    public List<ReqVO> getAll();
	public void setStatus(String status,String req_id) throws Exception;
	public List<ReqVO> findByDate(Date beginDate, Date endDate);
	public List<ReqVO> selectOfN();
	public List<ProdVO> getAllProd();
}
