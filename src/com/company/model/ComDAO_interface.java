package com.company.model;

import java.util.List;

public interface ComDAO_interface {

	public void insert(ComVO comVO);

	public void update(ComVO comVO);

	public void delete(String com_id);

	public ComVO findByPrimaryKey(String com_id);

	public List<ComVO> getAll() ;

	public List<ComVO> findByName(String com_name);
}
