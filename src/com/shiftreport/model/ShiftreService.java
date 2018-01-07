package com.shiftreport.model;

import java.sql.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.employee.model.EmpDAO_interface;


public class ShiftreService {

	private ShiftreDAO_interface dao=null;
	
	public ShiftreService() {
//		dao=new ShiftreDAO();

		ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
		dao =(ShiftreDAO_interface) context.getBean("ShiftreDAO");
	}
	
	public ShiftreVO getOne(Date Date,String shift) {
		return dao.findByPrimaryKey(Date,shift);
	}
	
	public  List getAll(){
		return dao.getAll();
	}
	
	public void insertOne(ShiftreVO shiftreVO){
		dao.insert(shiftreVO);
	}
	
	public List getByDate(Date Date){
		return dao.findByDate(Date);
	}
	
	public void update(ShiftreVO shiftreVO){
		dao.update(shiftreVO);
	}
	
	public void delete(Date Date,String shift){
		dao.delete(Date,shift);		
	}
	public List getByJson(Date Date1,Date Date2,String shift){
		return dao.getSumJson(Date1,Date2,shift);
	}

}
