package com.returns.model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public interface RtnList_Interface {
	public void insert(RtnListVO rtnListVO,List<RtnDetailVO> listDetail);
//	public void insertId(RtnDetailVO rtnDetailVO);
    public void update(RtnListVO rtnListVO);
    public void delete(String ret_id);
    public RtnListVO findByPrimaryKey(String ret_id);
    public List<RtnListVO> findByDate(Date ret_date);
    public List<RtnListVO> getfindById(String ret_id);
    public List<RtnListVO> findByCom(String com_name);
    public List<RtnListVO> getAll();
    public List<RtnDetailVO> findByDetail(String ret_id);

}
