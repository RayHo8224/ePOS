package com.shiftreport.model;

import java.sql.Date;
import java.util.List;

public interface ShiftreDAO_interface {
	public void insert(ShiftreVO shiftreVO);

	public void update(ShiftreVO shiftreVO);

	public void delete(Date Date , String shift);

	public ShiftreVO findByPrimaryKey(Date Date , String shift);

	public List<ShiftreVO> getAll() ;

	public List<ShiftreVO> findByDate(Date Date);

	public List getSumJson(Date date1, Date date2, String shift);

	
}
