package com.returns.model;

import java.util.List;

public interface RtnItems_Interface {
	public void insert(RtnItemsVO rtnItemsVO);
    public void update(RtnItemsVO rtnItemsVO);
    public void delete(String prod_name);
    public RtnItemsVO findByPrimaryKey(String prod_name);
    public List<RtnItemsVO> findByName(String prod_name);
    public List<RtnItemsVO> findById(String com_id);
    public List<RtnItemsVO> getAll();

}
