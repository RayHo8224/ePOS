package com.returns.model;

import java.util.List;

public interface RtnDetail_Interface {
	public void insert(RtnDetailVO rtnDetailVO);
    public void update(RtnDetailVO rtnDetailVO);
    public void delete(String ret_id,String prod_name);
    public RtnDetailVO findByPrimaryKey(String ret_id,String prod_name);
    public List<RtnDetailVO> findById(String ret_id);
    public List<RtnDetailVO> findByName(String prod_name);
    public List<RtnDetailVO> getAll();

}

