package com.shiftreport.model;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;


public class ShiftreDAO implements ShiftreDAO_interface {
	
	private static final String GET_ALL_STMT = "from ShiftreVO order by Date";


	private HibernateTemplate hibernateTemplate;    
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) { 
        this.hibernateTemplate = hibernateTemplate;
    }
	
	@Override
	public void insert(ShiftreVO shiftreVO) {	
			hibernateTemplate.save(shiftreVO);
	}

	@Override
	public void update(ShiftreVO shiftreVO) {
		hibernateTemplate.update(shiftreVO);
	}

	@Override
	public void delete(Date Date, String shift) {
		hibernateTemplate.bulkUpdate("delete ShiftreVO where Date='"+Date+"' and shift='"+shift+"'");	
		
	}

	@Override
	public ShiftreVO findByPrimaryKey(Date Date, String shift) {
		ShiftreVO shiftreVO = null;
		List<ShiftreVO> list = hibernateTemplate.find("from ShiftreVO where Date=? and shift=?",new Object[]{Date,shift});	
		System.out.println("2="+shift);
		System.out.println("2="+Date);
			for(ShiftreVO shiftreVO1 : list){
				shiftreVO=shiftreVO1;
			}
			
			return shiftreVO;	
	}

	@Override
	public List<ShiftreVO> getAll() {
		List<ShiftreVO> list = null;
		
		 list = hibernateTemplate.find(GET_ALL_STMT);
			
		return list;

	}

	@Override
	public List<ShiftreVO> findByDate(Date Date) {
		List<ShiftreVO> list = null;
		list=hibernateTemplate.find("from ShiftreVO where Date ='"+Date+"'");
			
		return list;
	}
	@Override
	public List<ShiftreVO> getSumJson(Date Date1,Date Date2,String shift) {
		List<ShiftreVO> list = new ArrayList<ShiftreVO>();
		
		 list = hibernateTemplate.find("from ShiftreVO where Date between ? and ? and shift = ?",Date1,Date2,shift);
			
		return list;

	}

}
