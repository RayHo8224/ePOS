package com.product.model;

import java.util.List;

public interface ProdDAO_interface {
	public void insert(ProdVO prodVO);

	public void update(ProdVO prodVO);

	public void delete(String prod_id);

	public ProdVO findByPrimaryKey(String prod_id);

	public List<ProdVO> getAll() ;

	public List<ProdVO> findByName(String prod_name);

	public List<ProdVO> findByGroup(String prod_group);
	
	public void update2(Integer prod_stock, String prod_id);

	List<ProdVO> selectByGroup();

}
