package com.requisition_detail.model;

import java.util.List;

public interface ReqDetail_Interface {
	public void insert(ReqDetailVO reqDetailVO);
    public void update(ReqDetailVO reqDetailVO);
    public void delete(String req_id,String prod_name);
    public List<ReqDetailVO> findByReqId(String req_id);
//    public List<ReqDetailVO> findByProdname(String prod_name);
    public List<ReqDetailVO> getAll();
//    public List<ReqDetailVO> getone(String req_id, String prod_name);
}

