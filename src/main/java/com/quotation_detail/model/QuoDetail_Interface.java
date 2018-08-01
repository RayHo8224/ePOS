package com.quotation_detail.model;

import java.util.List;

public interface QuoDetail_Interface {
	public void insert(List<QuoDetailVO> list);
    public void update(QuoDetailVO quoDetailVO);
    public void delete(String quo_id,String prod_name);
    public List<QuoDetailVO> findByQuoId(String quo_id);
//    public List<QuoDetailVO> findByProdname(String prod_name);
    public List<QuoDetailVO> getAll();
}

