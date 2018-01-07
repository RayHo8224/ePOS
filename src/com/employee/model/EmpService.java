package com.employee.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.company.model.ComDAO_interface;

public class EmpService {
	
	private EmpDAO_interface dao=null;
	
	public EmpService() {
//		dao=new EmpDAO();

		ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
		dao =(EmpDAO_interface) context.getBean("EmpDAO");

	}
	
	public EmpVO getOne(String emp_id) throws IOException, IOException{
		return dao.findByPrimaryKey(emp_id);
	}
	
	public  List getAll() throws IOException{
		return dao.getAll();
	}
	
	public void insertOne(EmpVO empVO){
		dao.insert(empVO);
	}
	
	public List getByName(String emp_name){
		return dao.findByName(emp_name);
	}
	
	public void update(EmpVO empVO){
		dao.update(empVO);
	}
	
	public void delete(String emp_id){
		dao.delete(emp_id);
		
	}
	
	public void setPassCode(String pass_code,String emp_id){
		dao.setPassCode(pass_code, emp_id);
	}
}
